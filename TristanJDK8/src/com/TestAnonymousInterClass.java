package com;

public class TestAnonymousInterClass {
	public static void main(String args[]) {
		TestAnonymousInterClass test = new TestAnonymousInterClass();
		test.show();
		
	}

	// 在这个方法中构造了一个匿名内部类
	private void show() {
		Out anonyInter = new Out() {// 获取匿名内部类实例
			@Override
			void show() {// 重写父类的方法
				System.out.println("this is Anonymous InterClass showing.");
			}
		};
		anonyInter.show();// 调用其方法

		Out out = new Out();
		out.show();// 调用其方法
	}
}

// 这是一个已经存在的类，匿名内部类通过重写其方法，将会获得另外的实现
class Out {
	void show() {
		System.out.println("this is Out showing.");
	}
}