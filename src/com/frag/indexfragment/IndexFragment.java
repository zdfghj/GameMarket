package com.frag.indexfragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.game.gamemarket.R;
import com.game.jsonresolve.JsonHomePageData;

public class IndexFragment extends Fragment {

	private View view;

	private ViewPager adViewPager;
	private LinearLayout pagerLayout;
	private List<View> pageViews;
	private ImageView[] imageViews;
	private ImageView imageView;
	private AdPageAdapter adapter;
	private AtomicInteger atomicInteger = new AtomicInteger(0);
	private boolean isContinue = true;

	private MyBaseAdapter mBaseAdapter = null;
	private List<JsonHomePageData> dataBaseList;
	private ListView newGameList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.index_fragment, container, false);
		initViewPager();
		return view;
	}

	private void initViewPager() {

		// 绑定ListView控件
		newGameList = (ListView) view.findViewById(R.id.new_game_list);
		// 从布局文件中获取ViewPager父容器
		pagerLayout = (LinearLayout) view.findViewById(R.id.view_pager_content);
		// 创建ViewPager
		adViewPager = new ViewPager(getActivity());

		// 获取屏幕像素相关信息
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

		// 根据屏幕信息设置ViewPager广告容器的宽高
		adViewPager.setLayoutParams(new LayoutParams(dm.widthPixels,
				dm.heightPixels * 2 / 5));

		// 将ViewPager容器设置到布局文件父容器中
		pagerLayout.addView(adViewPager);

		// 初始化Viewpager的数据
		initPageAdapter();

		// 初始化下标的小圆点
		initCirclePoint();

		// 初始化ListView的数据
		initData();

		mBaseAdapter = new MyBaseAdapter(getActivity(), dataBaseList);
		newGameList.setAdapter(mBaseAdapter);

		adViewPager.setAdapter(adapter);
		adViewPager.setOnPageChangeListener(new AdPageChangeListener());
//		adViewPager.setCurrentItem((imageViews.length)*100);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (isContinue) {
						viewHandler.sendEmptyMessage(atomicInteger.get());
						atomicOption();
					}
				}
			}
		}).start();

	}

	/**
	 * 暂时由自定义数据代替 需要Web获取数据填入
	 */
	private void initData() {
		dataBaseList = new ArrayList<JsonHomePageData>();
		for (int i = 0; i < 20; i++) {
			JsonHomePageData jhpd = new JsonHomePageData();
			jhpd.setGameName("火影忍者");
			jhpd.setGameSize("50MB");
			jhpd.setUpdateTime("2016-05-24");
			jhpd.setGameDetail("游戏简介");
			dataBaseList.add(jhpd);
		}
	}

	private void atomicOption() {
		atomicInteger.incrementAndGet();
		if (atomicInteger.get() > imageViews.length - 1) {
			atomicInteger.getAndAdd(-6);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
	}

	/*
	 * 每隔固定时间切换广告栏图片
	 */
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			adViewPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}
	};

	private void initPageAdapter() {
		pageViews = new ArrayList<View>();

		ImageView img1 = new ImageView(getActivity());
		img1.setBackgroundResource(R.drawable.tupian);
		pageViews.add(img1);

		ImageView img2 = new ImageView(getActivity());
		img2.setBackgroundResource(R.drawable.tupian);
		pageViews.add(img2);

		ImageView img3 = new ImageView(getActivity());
		img3.setBackgroundResource(R.drawable.tupian);
		pageViews.add(img3);

		ImageView img4 = new ImageView(getActivity());
		img4.setBackgroundResource(R.drawable.tupian);
		pageViews.add(img4);

		ImageView img5 = new ImageView(getActivity());
		img5.setBackgroundResource(R.drawable.tupian);
		pageViews.add(img5);

		ImageView img6 = new ImageView(getActivity());
		img6.setBackgroundResource(R.drawable.tupian);
		pageViews.add(img6);

		adapter = new AdPageAdapter(pageViews);
	}

	/**
	 * 设置下方原点的宽高
	 */
	private void initCirclePoint() {
		ViewGroup group = (ViewGroup) view.findViewById(R.id.viewGroup);
		imageViews = new ImageView[pageViews.size()];
		LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		ll.setMargins(5, 0, 5, 0);
		// 广告栏的小圆点图标
		for (int i = 0; i < pageViews.size(); i++) {
			// 创建一个ImageView, 并设置宽高. 将该对象放入到数组中
			imageView = new ImageView(getActivity());		
			imageView.setLayoutParams(ll);
			imageViews[i] = imageView;
			// 初始值, 默认第0个选中
			if (i == 0) {
				imageViews[i].setBackgroundResource(R.drawable.point_focused);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.point_unfocused);
			}
			// 将小圆点放入到布局中
			group.addView(imageViews[i]);
		}
	}

	/**
	 * ViewPager 页面改变监听器
	 */
	private final class AdPageChangeListener implements OnPageChangeListener {

		/**
		 * 页面滚动状态发生改变的时候触发
		 */
		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		/**
		 * 页面滚动的时候触发
		 */
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		/**
		 * 页面选中的时候触发
		 */
		@Override
		public void onPageSelected(int arg0) {
			// 获取当前显示的页面是哪个页面
			atomicInteger.getAndSet(arg0);
			// 重新设置原点布局集合
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0]
						.setBackgroundResource(R.drawable.point_focused);
				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.point_unfocused);
				}
			}
		}
	}
	
	/**
	 * ViewPager适配器
	 */
	private final class AdPageAdapter extends PagerAdapter {
		private List<View> views = null;

		/**
		 * 初始化数据源, 即View数组
		 */
		public AdPageAdapter(List<View> views) {
			this.views = views;
		}

		/**
		 * 从ViewPager中删除集合中对应索引的View对象
		 */
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(views.get(position));
		}

		/**
		 * 获取ViewPager的个数
		 */
		@Override
		public int getCount() {
			// 可设置成最大
//			return Integer.MAX_VALUE;
			return views.size();
		}

		/**
		 * 从View集合中获取对应索引的元素, 并添加到ViewPager中
		 */
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(views.get(position), 0);
			return views.get(position);
		}

		/**
		 * 是否将显示的ViewPager页面与instantiateItem返回的对象进行关联 这个方法是必须实现的
		 */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}
}
