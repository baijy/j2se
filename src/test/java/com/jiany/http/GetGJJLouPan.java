package com.jiany.http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jianyu.date.DateUtil;
import com.jianyu.http.FormHttp;

public class GetGJJLouPan {
	// 访问页面： http://gjjzx.changsha.gov.cn/info/iList.jsp?tm_id=62
	private final static String REQ_URL ="http://gjjzx.changsha.gov.cn/searchInterface.jsp"; // 列表URL
	
	/**
	 * 测试方法
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Map<String,String> params = new HashMap<String, String>();
		params.put("method", "2");
		params.put("depcode", "");
		params.put("proname", "");
		params.put("corpname", "");
		// 获取列表
		String ret = FormHttp.request(REQ_URL, params);
		
		// 转换为JSON，获得JSON数组
		JSONObject retJson = JSON.parseObject(ret);
		JSONArray proArr = (JSONArray)JSON.parse(retJson.get("result").toString());
		
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		StringBuffer sb4 = new StringBuffer();
		StringBuffer sb5 = new StringBuffer();
		
		// 再获取每一条详情
		for(Object json : proArr){
			JSONObject singleJson = JSON.parseObject(json.toString());
			String proname = singleJson.get("proname").toString().replace("[[Text:", "").replace("]]", ""); // 楼盘名称
			String corpname = singleJson.get("corpname").toString().replace("[[Text:", "").replace("]]", ""); //公司名称
			String pername = "";
			if( null != singleJson.get("pername")){
				pername = singleJson.get("pername").toString().replace("[[Text:", "").replace("]]", ""); // 联系人
			}
			
			String perphone = "";
			if( null != singleJson.get("perphone")){
				perphone = singleJson.get("perphone").toString().replace("[[Text:", "").replace("]]", ""); // 咨询电话
			}
			
			String address = "";
			if( null != singleJson.get("address")){
				address = singleJson.get("address").toString().replace("[[Text:", "").replace("]]", ""); // 楼盘地址
			}
			
			String depname = "";
			if( null != singleJson.get("depname")){
				depname = singleJson.get("depname").toString().replace("[[Text:", "").replace("]]", ""); // 管理部门
			}
			
			if( depname.contains("芙蓉区管理部")){
				sb1.append("【楼盘名称】"+proname+"\n"); // 业务类型
				sb1.append("【开发商名称】"+corpname+"\n"); // 标题
				sb1.append("【联系人】"+pername+"\n");
				sb1.append("【咨询电话】"+perphone+"\n");
				sb1.append("【楼盘地址】"+address+"\n");
				sb1.append("【管理部门】"+depname+"\n");
				sb1.append("----------------------\n");
			}
			if( depname.contains("天心区管理部")){
				sb2.append("【楼盘名称】"+proname+"\n");
				sb2.append("【开发商名称】"+corpname+"\n");
				sb2.append("【联系人】"+pername+"\n");
				sb2.append("【咨询电话】"+perphone+"\n");
				sb2.append("【楼盘地址】"+address+"\n");
				sb2.append("【管理部门】"+depname+"\n");
				sb2.append("----------------------\n");
			}
			if( depname.contains("岳麓区管理部")){
				sb3.append("【楼盘名称】"+proname+"\n");
				sb3.append("【开发商名称】"+corpname+"\n");
				sb3.append("【联系人】"+pername+"\n");
				sb3.append("【咨询电话】"+perphone+"\n");
				sb3.append("【楼盘地址】"+address+"\n");
				sb3.append("【管理部门】"+depname+"\n");
				sb3.append("----------------------\n");
			}if( depname.contains("开福区管理部")){
				sb4.append("【楼盘名称】"+proname+"\n");
				sb4.append("【开发商名称】"+corpname+"\n");
				sb4.append("【联系人】"+pername+"\n");
				sb4.append("【咨询电话】"+perphone+"\n");
				sb4.append("【楼盘地址】"+address+"\n");
				sb4.append("【管理部门】"+depname+"\n");
				sb4.append("----------------------\n");
			}if( depname.contains("雨花区管理部")){
				sb5.append("【楼盘名称】"+proname+"\n");
				sb5.append("【开发商名称】"+corpname+"\n");
				sb5.append("【联系人】"+pername+"\n");
				sb5.append("【咨询电话】"+perphone+"\n");
				sb5.append("【楼盘地址】"+address+"\n");
				sb5.append("【管理部门】"+depname+"\n");
				sb5.append("----------------------\n");
			}
		}
		
		writeFile(sb1.toString(),"1");
		writeFile(sb2.toString(),"2");
		writeFile(sb3.toString(),"3");
		writeFile(sb4.toString(),"4");
		writeFile(sb5.toString(),"5");
	}
	
	/**
	 * 字符串内容写入到文件
	 * @param str
	 * @throws IOException
	 */
	private static void writeFile(String str,String type) throws IOException{
        byte[] bs = str.getBytes();
        FileOutputStream fos = null;
        String dateStr = DateUtil.getDate("yyyyMMdd");
        if(type.equals("1")){
        	fos  =new FileOutputStream("E:/芙蓉区管理部公积金楼盘" + dateStr + ".txt",true);
        }else if(type.equals("2")){
        	fos  =new FileOutputStream("E:/天心区管理部公积金楼盘" + dateStr + ".txt",true);
        }else if(type.equals("3")){
        	fos  =new FileOutputStream("E:/岳麓区管理部公积金楼盘" + dateStr + ".txt",true);
        }else if(type.equals("4")){
        	fos  =new FileOutputStream("E:/开福区管理部公积金楼盘" + dateStr + ".txt",true);
        }else if(type.equals("5")){
        	fos  =new FileOutputStream("E:/雨花区管理部公积金楼盘" + dateStr + ".txt",true);
        }
        fos.write(bs);
        fos.close();
	}
}
