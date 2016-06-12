package com.game.jsonresolve;

import org.json.JSONObject;

/**
 * 解析最新更新列表Json
 */
public class JsonResolveNewList extends Thread{
	private String joggle;
	private String urlPath;
	private GetHttpService getHttpService;
	private JsonHomePageData mData;

	public JsonResolveNewList(String joggle, String urlPath) {
		this.joggle = joggle;
		this.urlPath = urlPath;
	}

	@Override
	public void run() {
		String gameMarketStr = getHttpService.requestHttp(joggle, urlPath);
		try {
			// 获取整个Json
			JSONObject jsonObject = new JSONObject(gameMarketStr);
			String errorCode = jsonObject.getString("errorCode");
			JSONObject result = jsonObject.getJSONObject("result");
			String gameIdStr = result.getString("gameId");
			String gameImageUrl = result.getString("gameImageUrl");
			String gameName = result.getString("gameName");
			String updateTime = result.getString("updateTime");
			String gameSize = result.getString("gameSize");
			String gameDetail = result.getString("gameDetail");
			int gameId = Integer.parseInt(gameIdStr);
			
			if (errorCode.equals("0")) {
				mData.setErrorCode(true);
			} else {
				mData.setErrorCode(false);
			}
			
			// 添加数据
			mData.setGameId(gameId);
			mData.setGameImageUrl(gameImageUrl);
			mData.setGameName(gameName);
			mData.setUpdateTime(updateTime);
			mData.setGameSize(gameSize);
			mData.setGameDetail(gameDetail);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
