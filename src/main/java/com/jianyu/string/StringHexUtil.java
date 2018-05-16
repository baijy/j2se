package com.jianyu.string;
/**
 * 字符串与16进制的相互转换
 * @author BaiJianyu <br>
 * @date 2018年5月16日下午3:11:04 <br>
 * Better late than never. <br>
 */
public class StringHexUtil {
	public static void main(String[] args) {
		System.out.println(str2HexStr("银行卡类型错误或不符合"));
		
		String failureDetails = "[0xe9][0x93][0xb6][0xe8][0xa1][0x8c][0xe5][0x8d][0xa1][0xe7][0xb1][0xbb][0xe5][0x9e][0x8b][0xe9][0x94][0x99][0xe8][0xaf][0xaf][0xe6][0x88][0x96][0xe4][0xb8][0x8d][0xe7][0xac][0xa6][0xe5][0x90][0x88]";
		String result = failureDetails.replace("[", "").replace("]", "").replace("0x", "").toUpperCase();
		System.out.println(hexStr2Str(result));
	}
	
	/**
	 * 字符串转16进制
	 * @param str
	 * @return
	 */
	public static String str2HexStr(String str) {
	    char[] chars = "0123456789ABCDEF".toCharArray();
	    StringBuilder sb = new StringBuilder("");
	    byte[] bs = str.getBytes();
	    int bit;
	    for (int i = 0; i < bs.length; i++) {
	        bit = (bs[i] & 0x0f0) >> 4;
	        sb.append(chars[bit]);
	        bit = bs[i] & 0x0f;
	        sb.append(chars[bit]);
	    }
	    return sb.toString().trim();
	}
	
	/**
	 * 16进制转字符串
	 * @param hexStr
	 * @return
	 */
	public static String hexStr2Str(String hexStr) {
	    String str = "0123456789ABCDEF";
	    char[] hexs = hexStr.toCharArray();
	    byte[] bytes = new byte[hexStr.length() / 2];
	    int n;
	    for (int i = 0; i < bytes.length; i++) {
	        n = str.indexOf(hexs[2 * i]) * 16;
	        n += str.indexOf(hexs[2 * i + 1]);
	        bytes[i] = (byte) (n & 0xff);
	    }
	    return new String(bytes);
	}
}
