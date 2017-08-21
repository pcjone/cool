package com.cool.wei.handler;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cool.constants.HandlerConstant;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
/**
 * 
* @ClassName: OtherHandler 
* @Description: 普通消息处理
* @author panlei
* @date 2017年8月9日 上午9:54:57 
*
 */
@Service
public class OtherHandler implements WxMpMessageHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		WxMpXmlOutMessage message = null;
		switch(wxMessage.getMsgType()) {
			case HandlerConstant.MSG_TYPE_TEXT:
				break;
			case HandlerConstant.MSG_TYPE_IMAGE:
				break;
			case HandlerConstant.MSG_TYPE_VIDEO:
				break;
			case HandlerConstant.MSG_TYPE_VOICE:
				break;
			case HandlerConstant.MSG_TYPE_SHORT_VIDEO:
				break;
			case HandlerConstant.MSG_TYPE_LINK:
				break;
			case HandlerConstant.MSG_TYPE_LOCATION:
				break;
			default:
				message = null;
		}
		return message;
	}

}
