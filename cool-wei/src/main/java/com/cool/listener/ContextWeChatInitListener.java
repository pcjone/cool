package com.cool.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;

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
			//日志
			WxMpMessageHandler logHandler = (WxMpMessageHandler)springContext.getBean("logHandler");
			//关注事件
			WxMpMessageHandler subscribeHandler = (WxMpMessageHandler)springContext.getBean("subscribeHandler");
			//关注更新
			WxMpMessageHandler updateHandler = (WxMpMessageHandler)springContext.getBean("updateHandler");
			//取消关注事件
			WxMpMessageHandler unSubscribeHandler = (WxMpMessageHandler)springContext.getBean("unSubscribeHandler");
			//event事件统一处理
			WxMpMessageHandler eventHandler = (WxMpMessageHandler)springContext.getBean("eventHandler");
			
			WxMpMessageHandler otherHandler = (WxMpMessageHandler)springContext.getBean("otherHandler");
			// 初始化消息处理(由细到粗)
			wxMpMessageRouter.rule().handler(updateHandler).next()
							.rule().async(false).event(WxConsts.EVT_SUBSCRIBE).handler(subscribeHandler).end()
							.rule().async(false).event(WxConsts.EVT_UNSUBSCRIBE).handler(unSubscribeHandler).end()
							.rule().async(false).msgType("event").handler(eventHandler).end()
							.rule().async(false).handler(otherHandler).end()
							.rule().handler(logHandler).end();
			logger.info("log>>>初始化微信基础配置。。。完成");
		} else {
			logger.info("no beans has initialized");
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		logger.info("-----end---------");
	}

}
