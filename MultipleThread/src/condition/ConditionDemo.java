package condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConditionDemo {
	final Lock lock = new ReentrantLock();// ������
	final Condition notFull = lock.newCondition();// д�߳�����
	final Condition notEmpty = lock.newCondition();// ���߳�����

	final Object[] items = new Object[10];// �������
	int putptr/* д���� */, takeptr/* ������ */, count/* �����д��ڵ����ݸ��� */;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length)
				// �����������
				notFull.await();// ����д�߳�
			items[putptr] = x;// ��ֵ
			if (++putptr == items.length)
				putptr = 0;// ���д����д�����е����һ��λ���ˣ���ô��Ϊ0
			++count;// ����++
			notEmpty.signal();// ���Ѷ��߳�
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				// �������Ϊ��
				notEmpty.await();// �������߳�
			Object x = items[takeptr];// ȡֵ
			if (++takeptr == items.length)
				takeptr = 0;// ����������������е����һ��λ���ˣ���ô��Ϊ0
			--count;// ����--
			notFull.signal();// ����д�߳�
			return x;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final ConditionDemo bb = new ConditionDemo();
		new Thread(){
			@Override
			public void run() {
				try {
					putX();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void putX() throws InterruptedException {
				for (int i = 0; i <100; i++) {
					bb.put(String.valueOf(i));
				}
			}
		}.start();
		
		
		
		
		
		
	}
}
