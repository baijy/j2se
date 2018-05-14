package com.jianyu.concurrent;

import java.util.concurrent.Exchanger;

/**
 * Exchanger用于进行线程间的数据交换
 * 
 * @author BaiJianyu <br>
 * @date 2018年5月14日下午5:52:57 <br>
 * Better late than never. <br>
 */
public class ExchangerRunnable implements Runnable {

	Exchanger exchanger = null;
	Object object = null;

	public ExchangerRunnable(Exchanger exchanger, Object object) {
		this.exchanger = exchanger;
		this.object = object;
	}

	@Override
	public void run() {
		try {
			Object previous = this.object;
			
			this.object = this.exchanger.exchange(this.object);

			System.out.println(Thread.currentThread().getName() + " 交换前数据： " + previous + " ＝＝＞　交换后数据 " + this.object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
