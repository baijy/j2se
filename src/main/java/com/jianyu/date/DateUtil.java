package com.jianyu.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	// yyyyMMdd
	public static String getDate(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(new Date());
	}
}
