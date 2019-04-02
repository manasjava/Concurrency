package com.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Producer Consumer Problem
 * @author manasranjan.dhal
 *
 */
public class ProducerConsumerProblem {

	public static void main(String[] args) {
		List<Integer> list=new ArrayList<>();
		Thread t1=new Thread(new Producer(list));
		Thread t2=new Thread(new Consumer(list));
		
		t1.start();
		t2.start();
		
	}

}

class Producer implements Runnable{

	List<Integer> list;
	
	public Producer(List<Integer> list) {
		this.list=list;
	}

	@Override
	public void run() {
		for (int i=0;i<10;i++) {
			try {
				produce(i);
			}catch(InterruptedException e) {
				
			}
		}
		
	}

	private void produce(int i) throws InterruptedException {
		
		synchronized(list) {
			while(!list.isEmpty()) {
				System.out.println("Producer Waiting for consumer to consume");
				list.wait();
			}
		}
		
		synchronized(list) {
			System.out.println("Produced : "+i);
			list.add(i);
			list.notify();
		}
	}
	
}

class Consumer implements Runnable{
	
	List<Integer> list;

	public Consumer(List<Integer> list) {
		this.list=list;
	}

	@Override
	public void run() {
		for (int i=0;i<10;i++) {
			try {
				consume();
			}catch(Exception e) {
				
			}
		}
	}

	private void consume() throws InterruptedException{
		
		synchronized(list) {
			while (list.isEmpty()) {
				System.out.println("Consumer Waiting for producer to produce ");
				list.wait();
			}
		}
		
		synchronized(list) {
			System.out.println("Consumed : "+list.remove(0));
			list.notify();
		}
	}
	
}