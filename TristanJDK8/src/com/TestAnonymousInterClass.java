package com;

public class TestAnonymousInterClass {
	public static void main(String args[]) {
		TestAnonymousInterClass test = new TestAnonymousInterClass();
		test.show();
		
	}

	// ����������й�����һ�������ڲ���
	private void show() {
		Out anonyInter = new Out() {// ��ȡ�����ڲ���ʵ��
			@Override
			void show() {// ��д����ķ���
				System.out.println("this is Anonymous InterClass showing.");
			}
		};
		anonyInter.show();// �����䷽��

		Out out = new Out();
		out.show();// �����䷽��
	}
}

// ����һ���Ѿ����ڵ��࣬�����ڲ���ͨ����д�䷽���������������ʵ��
class Out {
	void show() {
		System.out.println("this is Out showing.");
	}
}