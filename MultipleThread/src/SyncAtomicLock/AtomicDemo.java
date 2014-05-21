package SyncAtomicLock;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
	public static AtomicInteger i = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {

		TT2 t = new TT2(i);
		for (int k = 0; k < 10; k++) {
			new Thread(t).start();
		}
		
		Thread.sleep(5*1000);
		
		System.out.println(i.get());
	}

}

class TT2 implements Runnable {

	private AtomicInteger i;

	public TT2(AtomicInteger x) {
		this.i = x;
	}

	@Override
	public void run() {
		addOne();
	}

	//synchronized
	private void addOne() {
		for (int j = 0; j < 100; j++) {

			// business logic
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}

			// synchronized (this) {
			//synchronized (SynchronizedDemo.b) {
				i.addAndGet(1);
			//}

		}
		
	}
}