package com.jiany.http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jianyu.http.FormHttp;

public class GetGJJQuestion {
	// 访问页面： http://gjjzx.changsha.gov.cn/appeal/list.jsp?model_id=1
	private final static String LIST_URL ="https://www.csgjj.com.cn:8001/mail!query.do"; // 列表URL
	private final static String DETAIL_URL ="https://www.csgjj.com.cn:8001/mail!searchMail.do"; //详情URL
	
	/**
	 * 测试方法
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int allPage = 1; // 先跑少几页以免被封IP
		
		// 遍历获取每页的所有列表
		for( int i = 0; i< allPage; i++){
			int pageSize = i*50;
			Map<String,String> params = new HashMap<String, String>();
			constructFormMap(pageSize, params);
			String ret = FormHttp.request(LIST_URL, params);
			
			// 转换为JSON，获得JSON数组
			JSONObject retJson = JSON.parseObject(ret);
			JSONObject lists = JSON.parseObject(retJson.get("lists").toString());
			JSONObject dataList = JSON.parseObject(lists.get("dataList").toString());		
			JSONArray idArr = (JSONArray)JSON.parse(dataList.get("list").toString());
			
			// 再获取每一条详情
			for(Object json : idArr){
				JSONObject singleJson = JSON.parseObject(json.toString());
				String detailId = singleJson.get("lxbh").toString(); // ID，请求详细的时候用到
				String jtzxyw =""; // 业务类型，有可能返回空
				if( null != singleJson.get("jtzxyw")){
					jtzxyw = singleJson.get("jtzxyw").toString();
				}
				String xjbt = singleJson.get("xjbt").toString(); // 标题
				String xjnr = singleJson.get("xjnr").toString(); // 咨询内容
				
				// 获取详细页的内容
				String detail = FormHttp.request(DETAIL_URL+"?lxbh="+detailId, null);
				// 获取前后匹配的标签
				int start = detail.indexOf("<div style=\"height:200px;overflow:auto;line-height:25px;text-align: left;padding: 5px;\">");
				int end =  detail.indexOf("</div></td>");
				String content = detail.substring(start+88,end);
				
				StringBuffer sb = new StringBuffer();
				
				sb.append("【业务类型】"+jtzxyw+"\n"); // 业务类型
				sb.append("【标题】"+xjbt+"\n"); // 标题
				sb.append("【咨询内容】"+xjnr+"\n");
				sb.append("【回复内容】"+content+"\n");
				sb.append("----------------------\n");
				writeFile(sb.toString());
			}
		}	
	}
	
	/**
	 * 构建请求参数
	 * @param pageSize
	 * @param parames
	 */
	private static void constructFormMap(int pageSize, Map<String,String> parames) {
		parames.put("dto['lxbh']", "");
		parames.put("dto['cxm']", "");
		parames.put("dto['xjbt']", "");
		parames.put("dto['xjnr']", "");
		parames.put("gridInfo['dataList_limit']", "50");
		parames.put("gridInfo['dataList_start']", String.valueOf(pageSize));
		parames.put("gridInfo['dataList_limit']", "50");
		parames.put("gridInfo['dataList_start']", String.valueOf(pageSize));
		parames.put("gridInfo['dataList_selected']", "");
		parames.put("gridInfo['dataList_selected']", "");
		parames.put("gridInfo['dataList_modified']", "");
		parames.put("gridInfo['dataList_removed']", "");
		parames.put("gridInfo['dataList_added']", "");
		parames.put("dto['__pager']", "1");
	}
	
	/**
	 * 字符串内容写入到文件
	 * @param str
	 * @throws IOException
	 */
	private static void writeFile(String str) throws IOException{
        byte[] bs = str.getBytes();
        FileOutputStream fos  =new FileOutputStream("E:/长沙公积金咨询问题.txt",true);
        fos.write(bs);
        fos.close();
	}
}
