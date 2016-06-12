package com.frag.indexfragment;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.gamemarket.R;
import com.game.jsonresolve.JsonHomePageData;

/**
 * 最新游戏界面适配器
 */
public class MyBaseAdapter extends BaseAdapter {

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
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.new_game_item, null);
			
			holder.newGameIcon = (ImageView) convertView.findViewById(R.id.new_game_icon);
			holder.newGameName = (TextView) convertView.findViewById(R.id.new_game_name);
			holder.newGameSize = (TextView) convertView.findViewById(R.id.new_game_size);
			holder.newGameUpdate = (TextView) convertView.findViewById(R.id.new_game_update_time);
			holder.newGameDetails = (TextView) convertView.findViewById(R.id.new_game_details);
			holder.newGameDown = (Button) convertView.findViewById(R.id.new_game_download);
			
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		JsonHomePageData newListData = getItem(position);
		
		/**
		 * 根据Json解析出来的数据 填入适配器
		 * 缺少图片 和 按钮点击
		 */		
		holder.newGameName.setText(newListData.getGameName());
		holder.newGameSize.setText(newListData.getGameSize());
		holder.newGameUpdate.setText(newListData.getUpdateTime());
		holder.newGameDetails.setText(newListData.getGameDetail());
		
		return convertView;
	}
}
