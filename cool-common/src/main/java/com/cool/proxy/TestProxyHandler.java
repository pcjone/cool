package com.cool.proxy;

import java.lang.reflect.Method;

import java.lang.reflect.InvocationHandler;

public class TestProxyHandler implements InvocationHandler{
	
	private Object obj;
	
	public TestProxyHandler(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		System.out.println("before");
		for(Object c : args) {
			System.out.println(c.toString());
		}
		Object ret = method.invoke(obj, args);//执行被代理的方法
		System.out.println("after");
		return ret;
	}

}
