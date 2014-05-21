package basic;

public class DeadLock implements Runnable {
	public int i = 1;// 用于判断占用的情况
	// 设为静态的原因，是因为只有两台打印机
	static Object print1 = new Object();// 第一台打印机对象
	static Object print2 = new Object();// 第二台打印机对象

	public void run() {
		// 第一种情况是先占用第一台打印机，并等待第二台打印机
		if (i == 0) {
			synchronized (print1) {
				System.out.println("print1被占用等待printl2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				synchronized (print2) {
					System.out.println("print2被获取，任务完成");
				}

			}

		}
		// 第二种情况，占用第二台打印机，并等待第一台打印机
		if (i == 1) {
			synchronized (print2) {
				System.out.println("print2被占用等待print1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				synchronized (print1) {
					System.out.println("print1被获取，任务完成");
					;
				}

			}

		}
	}

	public static void main(String[] args) {
		DeadLock t1 = new DeadLock();
		DeadLock t2 = new DeadLock();
		t1.i = 0;
		t2.i = 1;
		Thread th1 = new Thread(t1);
		Thread th2 = new Thread(t2);
		th1.start();
		th2.start();

	}

}