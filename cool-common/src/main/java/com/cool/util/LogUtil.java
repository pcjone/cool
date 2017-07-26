/**
 * Acestek.com.cn Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.cool.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 总体说明
 *
 * <p>具体说明</p>
 *
 * @author ddxu
 * @version $Id: LogUtil.java,v 0.1 2016年7月11日 下午5:55:59 Exp $
 */
public final class LogUtil {
	private static final ThreadLocal<HttpServletRequest> tlRequest = new ThreadLocal<HttpServletRequest>();

	public static void putRequest(HttpServletRequest request) {
		tlRequest.set(request);
	}

	public static HttpServletRequest getRequest() {
		return tlRequest.get();
	}

	public static void removeRequest() {
		tlRequest.remove();
	}

	public static void putParam(Object... args) {
		HttpServletRequest request = tlRequest.get();
		request.setAttribute(tlRequest.toString(), args);
	}

	public static Object[] getParam() {
		HttpServletRequest request = tlRequest.get();
		return (Object[]) request.getAttribute(tlRequest.toString());
	}
}
