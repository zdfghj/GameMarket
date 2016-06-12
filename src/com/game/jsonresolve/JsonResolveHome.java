package com.game.jsonresolve;

import org.json.JSONObject;

/**
 * 解析首页广告Json
 */
public class JsonResolveHome extends Thread {

	private String joggle;
	private String urlPath;
	private GetHttpService getHttpService;
	private JsonHomePageData mData;

	public JsonResolveHome(String joggle, String urlPath) {
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
			String gameAdIdStr = result.getString("gameAdId");
			String gameTypeImageUrl = result.getString("gameTypeImageUrl");
			int gameAdId = Integer.parseInt(gameAdIdStr);
			
			if (errorCode.equals("0")) {
				mData.setErrorCode(true);
			} else {
				mData.setErrorCode(false);
			}
			
			mData.setGameAdId(gameAdId);
			mData.setStartImageUrl(gameTypeImageUrl);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
