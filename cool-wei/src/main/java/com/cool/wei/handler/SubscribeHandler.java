package com.cool.wei.handler;

import java.util.Date;
import java.util.Map;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.stereotype.Service;

@Service
public class SubscribeHandler implements WxMpMessageHandler{

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {		
		WxMpXmlOutTextMessage message = WxMpXmlOutMessage.TEXT().content("欢迎关注").fromUser(wxMessage.getToUser())
				.toUser(wxMessage.getFromUser()).build();
		return message;
	}
}
