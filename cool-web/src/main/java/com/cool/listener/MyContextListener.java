package com.cool.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cool.wei.handler.LogHandler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 
 * @ClassName: MyContextListener
 * @Description: 监听ServletContext对象
 * @author panlei 446756738@qq.com
 * @date 2017年6月29日 下午3:14:46
 *
 */
public class MyContextListener implements ServletContextListener {
	private Logger logger = Logger.getLogger(MyContextListener.class);
	
	// 获取spring注入的bean对象
	private WebApplicationContext springContext;
	// 获取上下文
	private ServletContext context;
	// 微信日志处理
	private WxMpMessageHandler logHandler = new LogHandler();

	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		springContext = WebApplicationContextUtils.getWebApplicationContext(context);
		if (springContext != null) {
			WxMpService wxMpService = (WxMpService) springContext.getBean("wxMpService");
			WxMpMessageRouter wxMpMessageRouter = (WxMpMessageRouter) springContext.getBean("wxMpMessageRouter");
			// 初始化消息处理
			wxMpMessageRouter.rule().handler(logHandler).end();
			try {
				wxMpService.getAccessToken();
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
			System.out.println("初始化微信基础配置。。。完成");
			logger.info("log>>>初始化微信基础配置。。。完成");
		} else {
			logger.info("no beans has initialized");
			System.out.println("初始化微信基础配置。。。失败");
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		logger.info("-----end---------");
	}

}
