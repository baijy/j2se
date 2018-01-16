package com.jianyu.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射测试类
 * 
 * @author jianyu.bai
 *
 */
public class ReflectTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		// 获取构造器，非public构造器只能用Declared来获取
		Constructor<Person> cons = Person.class.getDeclaredConstructor(String.class, int.class);

		Person someOne = cons.newInstance("小明", 22);
		someOne.show();

		// 同样要用Declared，否则获取不到属性
		Field f = Person.class.getDeclaredField("name");
		f.setAccessible(true);
		// set方法是对对象的这个属性赋值
		f.set(someOne, "小明新");
		someOne.show();

		Field[] flds = Person.class.getDeclaredFields();
		for (Field fld : flds) {
			if (!fld.isAccessible()) {
				fld.setAccessible(true);
			}
			if (fld.getName().equals("name")) {
				fld.set(someOne, "小明又");
			}
		}
		someOne.show();

		Method method = Person.class.getMethod("setPerson", String.class, int.class);
		method.invoke(someOne, "小红", 18);
		someOne.show();

		Method sMethod = Person.class.getMethod("showCt");
		sMethod.invoke(null);
		
		
	}
}
