package com.cool.wei.handler;

import java.util.Map;

import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;


@Service("logHandler")
public class LogHandler implements WxMpMessageHandler {

	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
			Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager)
			throws WxErrorException {
		System.out.println(wxMpXmlMessage.toString());
	    return null;
	}

}
