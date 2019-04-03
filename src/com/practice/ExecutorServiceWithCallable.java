package com.practice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceWithCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService service=Executors.newFixedThreadPool(4);
		
		for (int i=0;i<10;i++) {
			Future<Integer> fs= service.submit(new ThreadCallDemo(i));
			System.out.println(fs.get());
		}
		service.shutdown();
		
		Thread.sleep(1000);
		System.out.println("--------------------------------------");
		ExecutorService service1=Executors.newCachedThreadPool();
		
		for (int i=0;i<10;i++) {
			Future<Integer> fs= service1.submit(new ThreadCallDemo(i));
			System.out.println(fs.get());
		}
		service1.shutdown();
		
		Thread.sleep(1000);
		System.out.println("--------------------------------------");
		ExecutorService service2=Executors.newSingleThreadExecutor();
		
		for (int i=0;i<10;i++) {
			Future<Integer> fs= service2.submit(new ThreadCallDemo(i));
			System.out.println(fs.get());
		}
		service2.shutdown();
	}

}


class ThreadCallDemo implements Callable {

	int taskId;
	public ThreadCallDemo(int i) {
		this.taskId=i;
	}

	@Override
	public Integer call() throws Exception {
		System.out.print("Running by : "+Thread.currentThread().getName()+" task is :");
		return taskId;
	}
	
}