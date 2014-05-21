package basic;
public class DaemonThread extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(1000);
				Thread.currentThread().interrupt();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("test");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		DaemonThread test=new DaemonThread();
		//test.setDaemon(true);//Of course.if set daemon thread. the child thread will exit
		test.start();
		Thread.sleep(3000);
		System.out.println("return");
		
		return;
	}
}
