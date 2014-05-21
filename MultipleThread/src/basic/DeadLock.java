package basic;

public class DeadLock implements Runnable {
	public int i = 1;// �����ж�ռ�õ����
	// ��Ϊ��̬��ԭ������Ϊֻ����̨��ӡ��
	static Object print1 = new Object();// ��һ̨��ӡ������
	static Object print2 = new Object();// �ڶ�̨��ӡ������

	public void run() {
		// ��һ���������ռ�õ�һ̨��ӡ�������ȴ��ڶ�̨��ӡ��
		if (i == 0) {
			synchronized (print1) {
				System.out.println("print1��ռ�õȴ�printl2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				synchronized (print2) {
					System.out.println("print2����ȡ���������");
				}

			}

		}
		// �ڶ��������ռ�õڶ�̨��ӡ�������ȴ���һ̨��ӡ��
		if (i == 1) {
			synchronized (print2) {
				System.out.println("print2��ռ�õȴ�print1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				synchronized (print1) {
					System.out.println("print1����ȡ���������");
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