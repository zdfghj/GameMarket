package com.game.jsonresolve;

import org.json.JSONObject;

/**
 * 解析开始界面Json
 */
public class JsonResolveStart extends Thread{
	
	private String joggle;
	private String urlPath;
	private GetHttpService getHttpService;
	private JsonHomePageData mData;
	
	public JsonResolveStart(String joggle, String urlPath){
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
			String startImageUrl = result.getString("startImageUrl");
			
			if (errorCode.equals("0")) {
				mData.setErrorCode(true);
			}else {
				mData.setErrorCode(false);
			}
			
			mData.setStartImageUrl(startImageUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
}
