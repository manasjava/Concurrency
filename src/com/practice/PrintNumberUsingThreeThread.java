package com.practice;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Print 0 to 9 using three thread sequentially
 * 
 * @author manasranjan.dhal
 *
 */
public class PrintNumberUsingThreeThread {

	public static void main(String[] args) {
		AtomicInteger num=new AtomicInteger(0);
		
		Thread t1=new Thread(new ThreadNumDemo(num,0));
		Thread t2=new Thread(new ThreadNumDemo(num,1));
		Thread t3=new Thread(new ThreadNumDemo(num,2));
		
		t1.start();
		t2.start();
		t3.start();
	}

}


class ThreadNumDemo implements Runnable{
	
	AtomicInteger num;
	int threadPosition;

	public ThreadNumDemo(AtomicInteger num, int threadPosition) {
		this.num=num;
		this.threadPosition=threadPosition;
	}

	@Override
	public void run() {
		
		while(num.get() < 10) {
			synchronized(num) {
				if (num.get() % 3 == threadPosition) {
					if (num.get() < 10) {
						System.out.println(Thread.currentThread().getName()+" : "+num.getAndIncrement());
					}
				}
			}
		}
		
		
	}
	
}