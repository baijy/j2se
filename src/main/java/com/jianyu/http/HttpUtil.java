package com.jianyu.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import net.sf.json.JSONObject;

public class HttpUtil {
	private final static String CHAR_CODE = "UTF-8";
	private final static String METHOD_POST = "POST";
	private final static String REQ_ADDR = "http://baidu.com:8180/ShieldPlatform/bankCardBinController/bankCardBinInfo.do";

	/**
	 * 
	 * @param reqUrl
	 *            接口地址
	 * @param json
	 *            请求参数，JSON字符串
	 * @return
	 * @throws IOException
	 */
	public static String callInterface(String reqUrl, String json) throws IOException {
		URL url;
		String result;
		JSONObject jsonObject = null;
		OutputStream os = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		try {
			url = new URL(reqUrl);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod(METHOD_POST);
			http.setRequestProperty("Content-Type", "application/json");
			http.setConnectTimeout(30000);
			http.setDoInput(true);
			http.setDoOutput(true);
			http.connect();
			os = http.getOutputStream();
			os.write(json.getBytes(CHAR_CODE));// 传入参数
			is = http.getInputStream();
			int size = is.available();
			byte[] b = new byte[size];
			int len;
			out = new ByteArrayOutputStream();
			while ((len = is.read(b)) != -1) {
				out.write(b, 0, len);
			}
			result = out.toString(CHAR_CODE);
			jsonObject = JSONObject.fromObject(result);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (is != null)
					is.close();
				if (os != null)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}

	public static void main(String[] args) throws IOException {
		DemoBean bean = new DemoBean();
		bean.setIdcard("210681200001017530");
		bean.setName("张三");

		JSONObject obj = JSONObject.fromObject(bean);
		String json = obj.toString();
		String result = callInterface(REQ_ADDR, json);
		System.out.println(result);
	}
}
