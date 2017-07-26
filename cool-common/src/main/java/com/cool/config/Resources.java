package com.cool.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 加载配置
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@PropertySource(value = { "classpath:config/sftp.properties", "classpath:config/email.properties",
		"classpath:i18n/messages*.properties", "classpath:config/sms.properties" })
public final class Resources {
	/** SSH服务器配置 */
	public static final ResourceBundle SFTP = ResourceBundle.getBundle("config/sftp");
	/** 邮箱服务器配置 */
	public static final ResourceBundle EMAIL = ResourceBundle.getBundle("config/email");
	/** 国际化信息 */
	private static final Map<String, ResourceBundle> MESSAGES = new HashMap<String, ResourceBundle>();
	/** 短信信息 */
	private static final Map<String, ResourceBundle> SMSINFO = new HashMap<String, ResourceBundle>();

	/** 国际化信息 */
	public static String getMessage(String key, Object... params) {
		Locale locale = LocaleContextHolder.getLocale();
		ResourceBundle message = MESSAGES.get(locale.getLanguage());
		if (message == null) {
			synchronized (MESSAGES) {
				message = MESSAGES.get(locale.getLanguage());
				if (message == null) {
					message = ResourceBundle.getBundle("i18n/messages", locale);
					MESSAGES.put(locale.getLanguage(), message);
				}
			}
		}
		if (params != null) {
			return String.format(message.getString(key), params);
		}
		return message.getString(key);
	}

	/** 短信信息 */
	public static String getSmsInfo(String key) {
		Locale locale = LocaleContextHolder.getLocale();
		ResourceBundle message = SMSINFO.get(locale.getLanguage());
		if (message == null) {
			synchronized (SMSINFO) {
				message = SMSINFO.get(locale.getLanguage());
				if (message == null) {
					message = ResourceBundle.getBundle("config/sms", locale);
					SMSINFO.put(locale.getLanguage(), message);
				}
			}
		}
		return message.getString(key);
	}

	/** 清除国际化信息 */
	public static void flushMessage() {
		MESSAGES.clear();
	}
}
