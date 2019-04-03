package com.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author manasranjan.dhal
 *
 */
public class ExecutorServiceWithRunnable {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService service=Executors.newFixedThreadPool(4);
		for (int i=0;i< 10;i++) {
			service.execute(new ThreadDemo(i));
		}
		service.shutdown();
		
		Thread.sleep(1000);
		
		/**
	     * newCachedThreadPool Creates a thread pool that creates new threads as needed, but
	     * will reuse previously constructed threads when they are
	     * available.  These pools will typically improve the performance
	     * of programs that execute many short-lived asynchronous tasks.
	     * Calls to {@code execute} will reuse previously constructed
	     * threads if available. If no existing thread is available, a new
	     * thread will be created and added to the pool. Threads that have
	     * not been used for sixty seconds are terminated and removed from
	     * the cache.
	     * min=0 and max=214748364
	     **/
		
		System.out.println("----------------------------------");
		ExecutorService service1=Executors.newCachedThreadPool();
		for (int i=0;i< 10;i++) {
			service1.execute(new ThreadDemo(i));
		}
		service1.shutdown();
		
		
		Thread.sleep(1000);
		System.out.println("----------------------------------");
		ExecutorService service2=Executors.newSingleThreadExecutor();
		for (int i=0;i< 10;i++) {
			service2.execute(new ThreadDemo(i));
		}
		service2.shutdown();
		
	}

}

class ThreadDemo implements Runnable{

	int taskId;
	
	public ThreadDemo(int i) {
		this.taskId=i;
	}

	@Override
	public void run() {
		System.out.println("Running by : "+Thread.currentThread().getName()+" task is :"+taskId);
	}
	
}
