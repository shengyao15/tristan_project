package SyncAtomicLock;

public class SynchronizedDemo {
	// �β� ʵ��  int �޸ĺ� ��ʵ��û��Ӱ��
	// ����  �޸ĺ������ʵ��Ҳ�ᱻ�޸�
	public static int[] i = {0};
	
	public static byte[] b = new byte[1];

	public static void main(String[] args) throws InterruptedException {

		TT t = new TT(i);
		for (int k = 0; k < 10; k++) {
			new Thread(t).start();
		}
		
		Thread.sleep(15*1000);
		System.out.println(i[0]);

	}

}

class TT implements Runnable {

	private int[] i;

	public TT(int[] x) {
		this.i = x;
	}

	@Override
	public void run() {
		addOne();
	}

	synchronized
	private void addOne() {
		for (int j = 0; j < 100; j++) {

			// business logic
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}

			// synchronized (this) {
			//synchronized (SynchronizedDemo.b) {
				i[0]++;
			//}

		}
		
	}
}