package com.frag.classifyfragment;

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
import com.game.jsonresolve.JsonClassifyData;
import com.game.jsonresolve.JsonHomePageData;

/**
 * 最新游戏界面适配器
 */
public class ClassifyAdapter extends BaseAdapter {

	private List<JsonClassifyData> list;
	private Context mContext;

	public ClassifyAdapter(Context mContext, List<JsonClassifyData> list) {
		this.mContext = mContext;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public JsonClassifyData getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public class ViewHolder {
		// 游戏类别图标
		public ImageView classifyIcon;
		// 游戏类别名字
		public TextView classifyName;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.classify_list_item, null);
			
			holder.classifyIcon = (ImageView) convertView.findViewById(R.id.classify_icon);
			holder.classifyName = (TextView) convertView.findViewById(R.id.classify_name);
			
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		JsonClassifyData classifyData = getItem(position);
		
		/**
		 * 根据Json解析出来的数据 填入适配器
		 */		
		
		holder.classifyName.setText(classifyData.getGameTypeName());
		
		return convertView;
	}
}
