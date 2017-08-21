package com.cool.proxy;

import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		TestProxy impl = new TextImpl();
		TestProxyHandler handler = new TestProxyHandler(impl);
		TestProxy proxy = (TestProxy) Proxy.newProxyInstance(impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), handler);
		proxy.add(1, 5);
		
	}

}
