package com.frag.indexfragment;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

import com.download.manager.BaseDownloadHolder;
import com.download.manager.DownloadManager;
import com.download.manager.DownloadService;
import com.game.gamemarket.MainActivity;
import com.game.gamemarket.R;
import com.game.jsonresolve.JsonHomePageData;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 最新游戏界面适配器
 */
public class MyBaseAdapter extends BaseAdapter {
	private DownloadManager downloadManager;

	private List<JsonHomePageData> list;
	private Context mContext;

	public MyBaseAdapter(Context mContext, List<JsonHomePageData> list) {
		this.mContext = mContext;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public JsonHomePageData getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public class ViewHolder {
		// 游戏图标
		public ImageView newGameIcon;
		// 游戏名字
		public TextView newGameName;
		// 游戏大小
		public TextView newGameSize;
		// 游戏更新时间
		public TextView newGameUpdate;
		// 游戏简介
		public TextView newGameDetails;
		// 下载按钮
		public Button newGameDown;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		downloadManager = DownloadService.getDownloadManager(mContext.getApplicationContext());
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.new_game_item, null);

			holder.newGameIcon = (ImageView) convertView.findViewById(R.id.new_game_icon);
			holder.newGameName = (TextView) convertView.findViewById(R.id.new_game_name);
			holder.newGameSize = (TextView) convertView.findViewById(R.id.new_game_size);
			holder.newGameUpdate = (TextView) convertView.findViewById(R.id.new_game_update_time);
			holder.newGameDetails = (TextView) convertView.findViewById(R.id.new_game_details);
			holder.newGameDown = (Button) convertView.findViewById(R.id.new_game_download);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		JsonHomePageData newListData = getItem(position);

		/**
		 * 根据Json解析出来的数据 填入适配器 缺少图片 和 按钮点击
		 */
		holder.newGameName.setText(newListData.getGameName());
		holder.newGameSize.setText(newListData.getGameSize());
		holder.newGameUpdate.setText(newListData.getUpdateTime());
		holder.newGameDetails.setText(newListData.getGameDetail());
		holder.newGameDown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String target = Environment.getExternalStorageDirectory().getPath() + "/WinRAR_x64_SC.exe";
				System.out.println(target);
				try {
					String url = "http://3.xp510.com:801/xp2011/WinRAR_x64_SC.exe";
					downloadManager.addNewDownload(url, "手机助手", target, true, true, new DownloadRequestCallBack());
					System.out.println("12312312");
				} catch (DbException e) {
					e.printStackTrace();
				}
			}
		});

		return convertView;
	}

	public class DownloadRequestCallBack extends RequestCallBack<File> {

		@SuppressWarnings("unchecked")
		private void refreshListItem() {
			if (userTag == null)
				return;
			// 获得弱引用的holder
			WeakReference<BaseDownloadHolder> tag = (WeakReference<BaseDownloadHolder>) userTag;
			BaseDownloadHolder holder = tag.get();
			if (holder != null) {
				holder.refresh();
			}
		}

		@Override
		public void onStart() {
			Toast.makeText(mContext, "开始下载", 1).show();
			refreshListItem();
		}

		@Override
		public void onLoading(long total, long current, boolean isUploading) {
			refreshListItem();
		}

		@Override
		public void onSuccess(ResponseInfo<File> responseInfo) {
			refreshListItem();
		}

		@Override
		public void onFailure(HttpException error, String msg) {
			Toast.makeText(mContext, "下载失败", 1).show();
			refreshListItem();
		}

		@Override
		public void onCancelled() {
			refreshListItem();
		}

		/**
		 * 保存listview等holder对象，请使用WeakReference（）进行弱引用， 示例：setUserTag(new
		 * WeakReference<holdler>(holder))，否则无法正常回调数据。
		 * 
		 * @param userTag
		 */
		@Override
		public void setUserTag(Object userTag) {
			super.setUserTag(userTag);
		}

	}
}
