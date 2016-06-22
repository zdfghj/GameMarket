package com.game.gamemarket;

import java.util.ArrayList;
import java.util.List;

import com.frag.classifyfragment.ClassifyFragment;
import com.frag.indexfragment.IndexFragment;
import com.frag.managefragment.ManageFragment;
import com.frag.rankfragment.RankFragment;
import com.game.search.SearchGameInfo;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 整体主框架
 */
public class MainActivity extends FragmentActivity implements OnClickListener {

	// 跟随标题一起移动的
	private ImageView moveBarImg;
	// 导航的四个按钮
	private Button indexBtn;
	private Button classifyBtn;
	private Button rankBtn;
	private Button manageBtn;

	// 标题搜索栏按钮
	private TextView searchGameTitle;
	// 二维码扫描按钮
	private ImageButton quickMarkBtn;
	// 下载管理界面按钮
	private ImageButton dowmloadManagerBtn;

	// 作为页面容器的ViewPager
	private ViewPager mViewPager;

	// Fragment页面的集合
	private List<Fragment> fragmentList;

	// 四个Fragment界面
	private IndexFragment indexFragment;
	private ClassifyFragment classifyFragment;
	private RankFragment rankFragment;
	private ManageFragment manageFragment;

	private MyPagerAdapter myPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		// 设置Titlebar
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

		// 初始化数据
		init();
	}

	private void init() {
		indexBtn = (Button) findViewById(R.id.btn_index);
		classifyBtn = (Button) findViewById(R.id.btn_classify);
		rankBtn = (Button) findViewById(R.id.btn_rank);
		manageBtn = (Button) findViewById(R.id.btn_manage);

		searchGameTitle = (TextView) findViewById(R.id.title_search_text);
		quickMarkBtn = (ImageButton) findViewById(R.id.title_scan);
		dowmloadManagerBtn = (ImageButton) findViewById(R.id.title_download);

		// 初始化移动下标图片
		InitImageView();

		indexBtn.setOnClickListener(this);
		classifyBtn.setOnClickListener(this);
		rankBtn.setOnClickListener(this);
		manageBtn.setOnClickListener(this);
		searchGameTitle.setOnClickListener(this);
		quickMarkBtn.setOnClickListener(this);
		dowmloadManagerBtn.setOnClickListener(this);

		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		fragmentList = new ArrayList<Fragment>();
		indexFragment = new IndexFragment();
		classifyFragment = new ClassifyFragment();
		rankFragment = new RankFragment();
		manageFragment = new ManageFragment();

		fragmentList.add(indexFragment);
		fragmentList.add(classifyFragment);
		fragmentList.add(rankFragment);
		fragmentList.add(manageFragment);

		myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList);
		mViewPager.setAdapter(myPagerAdapter);
		// 给ViewPager设置滑动改变监听
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_index:
			changeView(0);
			break;
		case R.id.btn_classify:
			changeView(1);
			break;
		case R.id.btn_rank:
			changeView(2);
			break;
		case R.id.btn_manage:
			changeView(3);
			break;
		case R.id.title_search_text:// 进入搜索界面
			intent = new Intent(this, SearchGameInfo.class);
			startActivity(intent);
			break;
		case R.id.title_scan:// 进入二维码搜索

			break;
		case R.id.title_download:// 进入下载管理
			intent = new Intent(this, DownloadList.class);
			startActivity(intent);
			break;
		}
	}

	// 手动设置ViewPager要显示的视图
	private void changeView(int desTab) {
		mViewPager.setCurrentItem(desTab, true);
	}

	private int offset = 0;// 动画图片偏移量
	private int bmpW;// 动画图片宽度
	private int currIndex = 0;// 当前页卡编号

	/**
	 * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
	 */
	private void InitImageView() {
		moveBarImg = (ImageView) findViewById(R.id.move_bar);
		// 获取图片宽度
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.switch_btn_on).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 4 - bmpW) / 2;// 计算偏移量
		// Matrix matrix = new Matrix();
		// matrix.postTranslate(offset, 0);
		// System.out.println(offset + "=====================================");
		//
		// System.out.println(matrix);
		// moveBarImg.setImageMatrix(matrix);// 设置动画初始位置
		moveBarImg.setX(offset);
		// Animation animation = new TranslateAnimation(offset, offset, 0, 0);
		// moveBarImg.startAnimation(animation);

	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

		public void onPageScrollStateChanged(int arg0) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageSelected(int arg0) {
			System.out.println("==============" + arg0 + "==============");
			Animation animation = new TranslateAnimation(one * currIndex, one * arg0, 0, 0);// 显然这个比较简洁，只有一行代码。
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			moveBarImg.startAnimation(animation);
		}
	}
}
