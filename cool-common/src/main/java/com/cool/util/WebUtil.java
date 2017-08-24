package com.cool.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.util.WebUtils;

import com.cool.Constants;
import com.cool.session.UserSession;


/**
 * Web层辅助类
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:19:28
 */
public final class WebUtil {
	private WebUtil() {
	}

	/**
	 * 获取指定Cookie的值
	 * 
	 * @param request cookie集合
	 * @param cookieName cookie名字
	 * @param defaultValue 缺省值
	 * @return
	 */
	public static final String getCookieValue(HttpServletRequest request, String cookieName,
			String defaultValue) {
		Cookie cookie = WebUtils.getCookie(request, cookieName);
		if (cookie == null) {
			return defaultValue;
		}
		return cookie.getValue();
	}

	/** 保存当前用户 */
	public static final void saveCurrentUser(Object user) {
		setSession(Constants.CURRENT_USER, user);
	}

	/** 获取当前用户 */
	public static final String getCurrentUser() {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			try {
				Session session = currentUser.getSession();
				if (null != session) {
					return (String)session.getAttribute(Constants.CURRENT_USER);
				}
			} catch (InvalidSessionException e) {
				
			}
		}
		return null;
	}

	/** 获取当前用户信息 */
	public static final UserSession getCurrentUserSession() {
		try {
			return (UserSession) CacheUtil.getRedisHelper().getNoExpiry(getCurrentUser().toString());
		} catch (Exception e) {
			return null;
		}
	}

	/** 保存当前用户信息 */
	public static final void saveCurrentUserSession(Object userSession) {
		setSession(Constants.CURRENT_USER, userSession);
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	public static final void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

	/** 移除当前用户 */
	public static final void removeCurrentUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.CURRENT_USER);
	}

	/**
	 * 获得国际化信息
	 * 
	 * @param key 键
	 * @param request
	 * @return
	 */
	public static final String getApplicationResource(String key, HttpServletRequest request) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources",
				request.getLocale());
		return resourceBundle.getString(key);
	}

	/**
	 * 获得参数Map
	 * 
	 * @param request
	 * @return
	 */
	public static final Map<String, Object> getParameterMap(HttpServletRequest request) {
		Map<String, Object> params =  WebUtils.getParametersStartingWith(request, null);
		Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Object> entry = it.next();
			String key=entry.getKey();
			if(!StringUtil.checkNotNull(params.get(key).toString())){
				it.remove();
			}
		}
		return params;
	}

	/** 获取客户端IP */
	public static final String getHost(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ("127.0.0.1".equals(ip)) {
			InetAddress inet = null;
			try { // 根据网卡取本机配置的IP
				inet = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			ip = inet.getHostAddress();
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}
}
