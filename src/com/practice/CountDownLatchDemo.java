package com.practice;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch Demo with 4 worker thred
 * @author manasranjan.dhal
 *
 */
public class CountDownLatchDemo {

	public static void main(String[] args) {
		
		CountDownLatch latch=new CountDownLatch(4);
		
		Worker w1=new Worker(500,latch,"Worker-1");
		Worker w2=new Worker(500,latch,"Worker-2");
		Worker w3=new Worker(500,latch,"Worker-3");
		Worker w4=new Worker(500,latch,"Worker-4");
		
		new Thread(w1).start();
		new Thread(w2).start();
		new Thread(w3).start();
		new Thread(w4).start();
		
		try {
			latch.await();
			System.out.println("All 4 task completed ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}


class Worker implements Runnable{
	
	int delay;
	CountDownLatch latch;
	String name;
	
	Worker(int delay,CountDownLatch latch,String name) {
		this.delay=delay;
		this.latch=latch;
		this.name=name;
	}
	

	@Override
	public void run() {
		System.out.println(name +" Reached and waiting for others");
		try {
			Thread.sleep(delay);
			System.out.println(name+" completed processing");
			latch.countDown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}