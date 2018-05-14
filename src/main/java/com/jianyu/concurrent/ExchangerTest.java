package com.jianyu.concurrent;

import java.util.concurrent.Exchanger;

/**
 * 交换机 Exchanger
 * Exchanger用于进行线程间的数据交换
 * 
 * @author BaiJianyu <br>
 * @date 2018年5月14日下午5:48:20 <br>
 *       Better late than never. <br>
 */
public class ExchangerTest {
	public static void main(String[] args) {
		Exchanger exchanger = new Exchanger();

		ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");
		ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");
		ExchangerRunnable exchangerRunnable3 = new ExchangerRunnable(exchanger, "C");
		ExchangerRunnable exchangerRunnable4 = new ExchangerRunnable(exchanger, "D");

		new Thread(exchangerRunnable1).start();
		new Thread(exchangerRunnable2).start();
		new Thread(exchangerRunnable3).start();
		new Thread(exchangerRunnable4).start();
	}
}
