package com.cool.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import com.cool.util.LogUtil;
import com.cool.util.WebUtil;


/**
 * 国际化信息设置(基于SESSION)
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:45
 */
public class LocaleInterceptor extends BaseInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		HttpSession session = request.getSession();
		//将request放入LogUtil内的ThreadLocal线程
		LogUtil.putRequest(request);
		// 设置客户端语言
		Locale locale = (Locale) session.getAttribute("LOCALE");
		if (locale == null) {
			String language = request.getParameter("locale");
			if (StringUtils.isNotBlank(language)) {
				locale = new Locale(language);
				session.setAttribute("LOCALE", locale);
			} else {
				locale = request.getLocale();
			}
		}
		session.setAttribute("HOST", WebUtil.getHost(request));
		LocaleContextHolder.setLocale(locale);
		return super.preHandle(request, response, handler);
	}

	/**
	 * 清除LogUitl中的request。
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception  
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)  
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		LogUtil.removeRequest();
	}
}
