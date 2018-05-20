package com.jiany.http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jianyu.date.DateUtil;
import com.jianyu.http.FormHttp;

public class GetZhuJianWeiQuestion {
	// 访问页面： http://www.csfdc.gov.cn/zmhd/zrxx/index.html
	private final static String REQ_URL ="http://www.csfdc.gov.cn/zmhd/zrxx/index"; // 列表URL
	
	/**
	 * 测试方法
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		StringBuffer sb = new StringBuffer();
		// 先取10页，别取多了
		for (int i = 0; i < 10; i++) {
			String index = "_"+String.valueOf(i);
			if(0==i){
				index ="";
			}
			String ret = FormHttp.get(REQ_URL+ index +".html");
			
			// 获取前后匹配的标签
			int start = ret.indexOf("<tr><td><p><a href=\"./");
			int end =  ret.indexOf("</tbody></table>");
			String content = ret.substring(start+22,end);
			String[] idStr = content.split("</td></tr>");
			// 第一个URL的特殊处理，加上头部
			idStr[0]  = "<tr><td><p><a href=\"./" + idStr[0];
			List<String> list = new ArrayList<String>();
			for(String str : idStr){
				int s = str.indexOf("<a href=\"./");
				int e =  str.indexOf("\" target=\"_blank\"");
				String c ="";
				if( -1 != s && -1 != e){
					c = str.substring(s+11,e);
				}
				if( c.length()> 20){
					list.add("http://www.csfdc.gov.cn/zmhd/zrxx/"+c);
				}
			}
			
			for(String urlStr : list){
				String detail = FormHttp.get(urlStr);
				String title = getDetailTitle(detail);
				String ques = getQuesContent(detail);
				String reply = getReplyTitle(detail);
				sb.append("【咨询标题】"+title+"\n\r");
				sb.append("【咨询内容】"+ques+"\n\r");
				sb.append("【回复内容】"+reply+"\n\r");
				sb.append("--- ---\n\r");
			}
		}
		
		writeFile(sb.toString());
	}
	
	// 从详情页提取标题
	private static String getDetailTitle(String detail) {
		int title_s = detail.indexOf("<td colspan=\"3\" style=\"width:85%;text-align:left;\">")+51;
		String current = detail.substring(title_s);
		int title_e = title_s + current.indexOf("</td>");
		String title = detail.substring(title_s, title_e);
		
		return dealWithHtml(title);
	}
	
	// 从详情页提取问题内容
	private static String getQuesContent(String ques) {
		int ques_s = ques.indexOf("class=\"td_content\"><p>")+22;
		String current = ques.substring(ques_s);
		int ques_e = ques_s + current.indexOf("</td>");
		String ret = ques.substring(ques_s, ques_e);
		
		return dealWithHtml(ret);
	}
	
	// 从详情页提取回复
	private static String getReplyTitle(String detail) {
		int reply_s = detail.indexOf("回复内容：</td>")+10;
		String current = detail.substring(reply_s);
		int reply_e = reply_s + current.indexOf("</p></td>");
		String reply = detail.substring(reply_s, reply_e);
		return dealWithHtml(reply);
	}

	private static String dealWithHtml(String content) {
		// <p>段落替换为换行 
		// content = content.replaceAll("<p .*?>", "\r\n"); 
		// <br><br/>替换为换行 
		content = content.replaceAll("<br\\s*/?>", "\r\n"); 
		// 去掉其它的<>之间的东西 
		content = content.replaceAll("\\<.*?>", ""); 

		content = content.replaceAll("&nbsp;", ""); 
		
		return content; 
	}
	/**
	 * 字符串内容写入到文件
	 * @param str
	 * @throws IOException
	 */
	private static void writeFile(String str ) throws IOException{
        byte[] bs = str.getBytes();
        FileOutputStream fos = null;
        String dateStr = DateUtil.getDate("yyyyMMdd");
        fos  =new FileOutputStream("E:/长沙住建委咨询问题" + dateStr + ".txt",true);
        
        fos.write(bs);
        fos.close();
	}
}
