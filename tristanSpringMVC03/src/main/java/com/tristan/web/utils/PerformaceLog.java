package com.tristan.web.utils;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PerformaceLog implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		String className = invocation.getMethod().getDeclaringClass().getName();
		String methodName = invocation.getMethod().getName();

		long begin = System.currentTimeMillis();
		Object result = invocation.proceed();
		long end = System.currentTimeMillis(); // 测试结束时间
		System.out.println("(" + className + "." + methodName + ") " + (end - begin) + " ms"); // 打印使用时间

		return result;

	}

}
