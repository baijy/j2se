package com.jianyu.time;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToTimestamp {
	public static void main(String[] args) {
		StringToTimestamp("2018-01-11 10:01:01");

		TimestampToString(1515636061000L);
	}

	public static void StringToTimestamp(String tsStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(tsStr);
/*			System.out.println(ts.getDate());
			System.out.println(ts.getDay());
			System.out.println(ts.getHours());
			System.out.println(ts.getMinutes());
			System.out.println(ts.getMonth());
			System.out.println(ts.getNanos());
			System.out.println(ts.getSeconds());
			System.out.println(ts.getTime());
			System.out.println(ts.getTimezoneOffset());
			System.out.println(ts.getYear());*/
			System.out.println(ts.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void TimestampToString(long mill) {
		java.util.Date dt = new Date(mill);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDateTime = sdf.format(dt);
		System.out.println(sDateTime);
	}
}
