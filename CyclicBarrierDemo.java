package com.practice;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier barrier1=new CyclicBarrier(4);
		CyclicBarrier barrier2=new CyclicBarrier(4);
		
		CWorker p1=new CWorker(1000, barrier1,barrier2,"Party-1");
		CWorker p2=new CWorker(2000, barrier1,barrier2,"Party-2");
		CWorker p3=new CWorker(3000, barrier1,barrier2,"Party-3");
		CWorker p4=new CWorker(4000, barrier1,barrier2,"Party-4");
		
		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(p3).start();
		new Thread(p4).start();
	}

}

class CWorker implements Runnable{
	
	int delay;
	CyclicBarrier barrier1;
	CyclicBarrier barrier2;
	String name;
	
	
	CWorker(int delay,CyclicBarrier barrier1,CyclicBarrier barrier2,String name) {
		this.delay=delay;
		this.barrier1=barrier1;
		this.barrier2=barrier2;
		this.name=name;
	}
	

	@Override
	public void run() {
		System.out.println(name +" Reached and waiting for others");
		try {
			Thread.sleep(delay);
			barrier1.await();
			Thread.sleep(delay);
			System.out.println(name +" Crossed barrier1");
			barrier2.await();
			Thread.sleep(delay);
			System.out.println(name+" Crossed Barrier2");
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
