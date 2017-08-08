package com.cool.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cool.wei.handler.TextHandler;
import com.cool.wei.handler.LogHandler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 
 * @ClassName: ContextWeChatInitListener
 * @Description: 监听ServletContext对象
 * @author panlei 446756738@qq.com
 * @date 2017年6月29日 下午3:14:46
 *
 */
public class ContextWeChatInitListener implements ServletContextListener {
	private Logger logger = Logger.getLogger(ContextWeChatInitListener.class);
	
	// 获取spring注入的bean对象
	private WebApplicationContext springContext;
	// 获取上下文
	private ServletContext context;

	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		springContext = WebApplicationContextUtils.getWebApplicationContext(context);
		if (springContext != null) {
			WxMpMessageRouter wxMpMessageRouter = (WxMpMessageRouter) springContext.getBean("wxMpMessageRouter");
			
			WxMpMessageHandler logHandler = (WxMpMessageHandler)springContext.getBean("logHandler");
			
			WxMpMessageHandler textHandler = (WxMpMessageHandler)springContext.getBean("textHandler");
			if(logHandler == null || textHandler == null) {
				logger.info("log>>>logHandler == null || textHandler == null");
			}
			// 初始化消息处理
			wxMpMessageRouter.rule().handler(logHandler).next()
							.rule().async(false).content("哈哈").handler(textHandler).end();
			logger.info("log>>>初始化微信基础配置。。。完成");
		} else {
			logger.info("no beans has initialized");
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		logger.info("-----end---------");
	}

}
