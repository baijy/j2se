package com.jianyu.reflect;

/**
 * Person对象
 * 
 * @author jianyu.bai
 *
 */
public class Person {
	private String name;
	private int age;
	private static String country = "Zh-ch";

	Person() {
	}

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void show() {
		System.out.println("Name:" + name + ", Age:" + age);
	}

	public void setPerson(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public static void showCt(){
		System.out.println(country);
	}
}
