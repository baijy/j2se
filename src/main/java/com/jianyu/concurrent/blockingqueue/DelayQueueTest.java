package com.jianyu.concurrent.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列 DelayQueue
 * 
 * @author BaiJianyu <br>
 * @date 2018年5月14日下午4:36:27 <br>
 * Better late than never. <br>
 */
public class DelayQueueTest {
	public static void main(String[] args) throws InterruptedException {
		DelayQueue<MyDelayedQueue> queue = new DelayQueue<MyDelayedQueue>();
		System.out.print("add添加一个元素："+queue.add(new MyDelayedQueue("aaa")));
		System.out.println(queue);
		System.out.print("【推荐】offer添加一个元素："+queue.offer(new MyDelayedQueue("bbb")));
		System.out.println(queue);
		System.out.print("【阻塞等待】put添加一个元素：");
		queue.put(new MyDelayedQueue("ccc"));
		queue.put(new MyDelayedQueue("ddd"));
		queue.put(new MyDelayedQueue("eee"));
		System.out.println(queue);
		System.out.print("remove移除指定元素："+queue.remove(new MyDelayedQueue("ccc")));
		System.out.println(queue);
		System.out.print("remove移除不存在的元素："+queue.remove(new MyDelayedQueue("zzz")));
		System.out.println(queue);
		
		System.out.print("poll获取并移除队列头部的元素："+queue.poll());
		System.out.println(queue);
		System.out.print("【阻塞等待】take获取并移除队列头部的元素："+queue.take());
		System.out.println(queue);
		System.out.println("----");
		System.out.print("peek获取队列头部的元素："+queue.peek());
		System.out.println(queue);
	}
}

/**
 * 实现了Delayed接口的类
 * 
 * @author BaiJianyu <br>
 * @date 2018年5月14日下午4:36:15 <br>
 * Better late than never. <br>
 */
class MyDelayedQueue implements Delayed {
	private String queueName ;
	public MyDelayedQueue(String queueName){
		this.queueName = queueName;
	}
	
	@Override
	public String toString(){
		return this.queueName;
	}
	
	@Override
	public int compareTo(Delayed o) {
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return 0;
	}
	
}