package SyncAtomicLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
	public static int[] i = {0};
	
	public static byte[] b = new byte[1];

	public static void main(String[] args) throws InterruptedException {

		TT3 t = new TT3(i);
		for (int k = 0; k < 10; k++) {
			new Thread(t).start();
		}
		
		Thread.sleep(5*1000);
		System.out.println(i[0]);

	}

}

class TT3 implements Runnable {

	private int[] i;
	private final Lock lock = new ReentrantLock();
	
	public TT3(int[] x) {
		this.i = x;
	}

	@Override
	public void run() {
		addOne();
	}

	private void addOne() {
		for (int j = 0; j < 100; j++) {

			// business logic
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}

			lock.lock();
			try{
				i[0]++;
			}finally{
				lock.unlock();
			}
			

		}
		
	}
}