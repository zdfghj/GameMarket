package com.game.jsonresolve;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetHttpService {
	/**
	 * 目前还没有提供接口和链接网址
	 */
	// 获取Http中的数据
	public String requestHttp(String joggle, String urlPath) {
		BufferedReader reader = null;
		// 返回结果
		String result = null;
		StringBuffer strBuffer = new StringBuffer();

		try {
			URL url = new URL(urlPath);
			// 连接地址接口
			HttpsURLConnection connection = (HttpsURLConnection) url
					.openConnection();
			// 设置请求方式
			connection.setRequestMethod("GET");
			// 设置接口 以及对应的MD5值
			connection.setRequestProperty(joggle, "");
			// 连接
			connection.connect();

			/**
			 * 开始读取字段
			 */
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				strBuffer.append(strRead);
				strBuffer.append("\r\n");
			}
			reader.close();
			result = strBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
