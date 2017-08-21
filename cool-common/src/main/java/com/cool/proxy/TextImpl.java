package com.cool.proxy;

public class TextImpl implements TestProxy{

	@Override
	public void add(int a, int b) {
		System.out.println(a+b);
	}

	@Override
	public void check(int a, int b) {
		System.out.println(a==b);
	}

}
