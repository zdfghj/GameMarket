package com.download.manager;

import java.io.File;
import java.lang.ref.WeakReference;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

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
