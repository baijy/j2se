package com.jianyu.collection;

import java.util.ArrayList;
import java.util.List;

public class SubListTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();

		for (int i = 1; i <= 1999; i++) {
			list.add(String.valueOf(i));
		}

		List<List<String>> newArrays = getAverRange(list,10);

		for (List<String> part : newArrays) {
			System.out.println(part);
		}
	}
	
	/**
	 * 把一个数组按每份N个划分，返回一个大的数组
	 * @param list 数组
	 * @param aver 每份包含多少个
	 * @return 新的数组
	 */
	public static <T> List<List<T>> getAverRange( List<T> list, int aver ){
		List<List<T>> newArrays = new ArrayList<List<T>>();
		int start = 0;
		int end = 0;
		for (int i = 0; i <= list.size() / aver; i++) {
			List<T> partList = new ArrayList<T>();
			end = start + aver;
			if (start >= list.size()) {
				break;
			}
			if (end >= list.size()) {
				end = list.size() ;
			}
			partList = list.subList(start, end);
			newArrays.add(partList);
			start = end;
		}
		return newArrays;
	}
}
