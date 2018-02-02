package com.jianyu.thread.threadlocal;

/**
 * ThreadLocal测试类
 * 
 * @author BaiJianyu
 * @date 20180128
 *
 */
public class SequenceNumber {
	// 匿名内部类，有get set 方法
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
		// 初始值
		public Integer initialValue() {
			return 0;
		}
	};

	// 获取下一个序列值
	public int getNextNum() {
		seqNum.set(seqNum.get() + 1);
		return seqNum.get();
	}

	public static void main(String[] args) {
		SequenceNumber sn = new SequenceNumber();

		TestClient t1 = new TestClient(sn);
		TestClient t2 = new TestClient(sn);
		TestClient t3 = new TestClient(sn);

		t1.start();
		t2.start();
		t3.start();

		// 结果是互相不影响
		// ThreadLocal为每个线程提供了单独的副本
	}

	private static class TestClient extends Thread {
		private SequenceNumber sn;

		public TestClient(SequenceNumber sn) {
			this.sn = sn;
		}

		public void run() {
			for (int i = 0; i < 3; i++) {
				System.out
						.println("thread[" + Thread.currentThread().getName() + "]" + sn + "[" + sn.getNextNum() + "]");
			}
		}
	}
}
