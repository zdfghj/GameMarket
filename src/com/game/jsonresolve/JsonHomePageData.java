package com.game.jsonresolve;

/**
 * 启动画面以及首页
 */
public class JsonHomePageData {
	// 判断请求数据是否成功
	private boolean errorCode;
	// 启屏广告图片链接
	private String startImageUrl;

	// 游戏ID
	private int gameAdId;
	// 首页广告图片链接
	private String gameTypeImageUrl;

	/**
	 * 最新更新游戏列表
	 */
	// 游戏ID
	private int gameId;
	// 图片链接
	private String gameImageUrl;
	// 游戏名字
	private String gameName;
	// 更新时间
	private String updateTime;
	// 游戏大小
	private String gameSize;
	// 游戏详情
	private String gameDetail;

	public int getGameAdId() {
		return gameAdId;
	}

	public void setGameAdId(int gameAdId) {
		this.gameAdId = gameAdId;
	}

	public boolean isErrorCode() {
		return errorCode;
	}

	public void setErrorCode(boolean errorCode) {
		this.errorCode = errorCode;
	}

	public String getStartImageUrl() {
		return startImageUrl;
	}

	public void setStartImageUrl(String startImageUrl) {
		this.startImageUrl = startImageUrl;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameTypeImageUrl() {
		return gameTypeImageUrl;
	}

	public void setGameTypeImageUrl(String gameTypeImageUrl) {
		this.gameTypeImageUrl = gameTypeImageUrl;
	}

	public String getGameImageUrl() {
		return gameImageUrl;
	}

	public void setGameImageUrl(String gameImageUrl) {
		this.gameImageUrl = gameImageUrl;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getGameSize() {
		return gameSize;
	}

	public void setGameSize(String gameSize) {
		this.gameSize = gameSize;
	}

	public String getGameDetail() {
		return gameDetail;
	}

	public void setGameDetail(String gameDetail) {
		this.gameDetail = gameDetail;
	}

}
