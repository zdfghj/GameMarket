package com.game.jsonresolve;

/**
 * 存放Json 分类解析出来的数据
 */
public class JsonClassifyData {
	// 游戏类别名字
	private String gameTypeName;
	// 游戏类别ID
	private int gameTypeId;
	// 游戏类别图标
	private String gameTypeImageUrl;

	public String getGameTypeName() {
		return gameTypeName;
	}

	public void setGameTypeName(String gameTypeName) {
		this.gameTypeName = gameTypeName;
	}

	public int getGameTypeId() {
		return gameTypeId;
	}

	public void setGameTypeId(int gameTypeId) {
		this.gameTypeId = gameTypeId;
	}

	public String getGameTypeImageUrl() {
		return gameTypeImageUrl;
	}

	public void setGameTypeImageUrl(String gameTypeImageUrl) {
		this.gameTypeImageUrl = gameTypeImageUrl;
	}

}
