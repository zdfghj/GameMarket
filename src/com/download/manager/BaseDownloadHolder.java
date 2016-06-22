package com.download.manager;

public abstract class BaseDownloadHolder {
	/**
	 * 下载实体
	 */
	protected DownloadInfo downloadInfo;

	public BaseDownloadHolder() {

	}

	public BaseDownloadHolder(DownloadInfo downloadInfo) {
		this.downloadInfo = downloadInfo;
	}

	/**
	 * listview等视图复用时更新要显示的下载对象
	 * 
	 * @param downloadInfo
	 *            当前要展示在页面上的下载对象
	 */
	public void update(DownloadInfo downloadInfo) {
		this.downloadInfo = downloadInfo;
		refresh();
	}

	public DownloadInfo getDownloadInfo() {
		return downloadInfo;
	}

	public void setDownloadInfo(DownloadInfo downloadInfo) {
		this.downloadInfo = downloadInfo;
	}

	/**
	 * 利用downloadInfo刷新holder中的页面数据
	 */
	public abstract void refresh();
}
