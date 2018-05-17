package com.jianyu.jvm;

/**
 * .class 装入内存
 * Class.forName() 装入内存，静态初始化
 * .getClass() 装入内存，静态初始化，非静态初始化
 */
public class TestLoadClass {
    public static void main(String[] args) {
        try {
            //测试.class
            Class testTypeClass = TestClassType.class;
            System.out.println(" TestClassType.class ---" + testTypeClass);
            System.out.println("\n\n");

            //测试Class.forName()
            Class testTypeForName = Class.forName("com.jianyu.jvm.TestClassType");
            System.out.println("Class.forName() ---" + testTypeForName);
            System.out.println("\n\n");

            //测试Object.getClass()
            TestClassType testTypeGetClass = new TestClassType();
            System.out.println("getClass() ---" + testTypeGetClass.getClass());
            System.out.println("\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class TestClassType {
    //构造函数
    public TestClassType() {
        System.out.println("----构造函数---");
    }

    //静态的参数初始化
    static {
        System.out.println("---静态的参数初始化---");
    }

    //非静态的参数初始化
    {
        System.out.println("----非静态的参数初始化---");
    }
}
