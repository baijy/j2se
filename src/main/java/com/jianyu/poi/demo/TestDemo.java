package com.jianyu.poi.demo;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public class TestDemo {
	public static void main(String[] args) throws Exception {
		method2();
		method1();

	}

	public static void method1() throws Exception {
		ExcelDataFormatter edf = new ExcelDataFormatter();
		Map<String, String> map = new HashMap<String, String>();
		map.put("真", "true");
		map.put("假", "false");
		edf.set("locked", map);

		List<User> xx = new ExcelUtils<User>(new User()).readFromFile(edf, new File("D:\\x20180201-1.xlsx"));
		JSONArray json = JSONArray.fromObject(xx);
		System.out.println(json);

	}

	public static void method2() throws Exception {
		List<User> list = new ArrayList<User>();
		User u = new User();
		u.setAge("18");
		u.setName("李艳红");
		u.setXx(123.23D);
		u.setYy(new Date());
		u.setLocked(false);
		u.setDb(new BigDecimal(123));
		list.add(u);

		u = new User();
		u.setAge("50");
		u.setName("马匀");
		u.setXx(123.23D);
		u.setYy(new Date());
		u.setLocked(true);
		u.setDb(new BigDecimal(234));
		list.add(u);

		u = new User();
		u.setAge("34");
		u.setName("刘东强");
		u.setXx(123.23D);
		u.setYy(new Date());
		u.setLocked(false);
		u.setDb(new BigDecimal(2344));
		list.add(u);

		u = new User();
		u.setAge("60");
		u.setName("李歌声");
		u.setXx(123.23D);
		u.setYy(new Date());
		u.setLocked(true);
		u.setDb(new BigDecimal(908));
		list.add(u);

		ExcelDataFormatter edf = new ExcelDataFormatter();
		Map<String, String> map = new HashMap<String, String>();
		map.put("true", "真");
		map.put("false", "假");
		edf.set("locked", map);

		ExcelUtils.writeToFile(list, edf, "D:\\x20180201-1.xlsx");
	}
}
