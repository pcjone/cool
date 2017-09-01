package com.cool.wxpay.apis.impl;

import java.io.File;
import java.util.Map;

import com.github.binarywang.wxpay.bean.request.WxEntPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayAuthcode2OpenidRequest;
import com.github.binarywang.wxpay.bean.request.WxPayMicropayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayOrderReverseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayReportRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.request.WxPayShorturlRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxEntPayQueryResult;
import com.github.binarywang.wxpay.bean.result.WxEntPayResult;
import com.github.binarywang.wxpay.bean.result.WxPayBillResult;
import com.github.binarywang.wxpay.bean.result.WxPayMicropayResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderReverseResult;
import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;

public class WxpayBillServiceImpl implements WxPayService {

	@Override
	public WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxPayOrderCloseResult closeOrder(String outTradeNo) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPayInfo(WxPayUnifiedOrderRequest request) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxPayConfig getConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConfig(WxPayConfig config) {
		// TODO Auto-generated method stub

	}

	@Override
	public WxPayRefundResult refund(WxPayRefundRequest request) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxPayRefundQueryResult refundQuery(String transactionId, String outTradeNo, String outRefundNo,
			String refundId) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxPayOrderNotifyResult getOrderNotifyResult(String xmlData) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxPaySendRedpackResult sendRedpack(WxPaySendRedpackRequest request) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxPayRedpackQueryResult queryRedpack(String mchBillNo) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxEntPayResult entPay(WxEntPayRequest request) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxEntPayQueryResult queryEntPay(String partnerTradeNo) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] createScanPayQrcodeMode1(String productId, File logoFile, Integer sideLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createScanPayQrcodeMode1(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] createScanPayQrcodeMode2(String codeUrl, File logoFile, Integer sideLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void report(WxPayReportRequest request) throws WxPayException {
		// TODO Auto-generated method stub

	}

	@Override
	public WxPayBillResult downloadBill(String billDate, String billType, String tarType, String deviceInfo)
			throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxPayMicropayResult micropay(WxPayMicropayRequest request) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxPayOrderReverseResult reverseOrder(WxPayOrderReverseRequest request) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String shorturl(WxPayShorturlRequest request) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String shorturl(String longUrl) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String authcode2Openid(WxPayAuthcode2OpenidRequest request) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String authcode2Openid(String authCode) throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSandboxSignKey() throws WxPayException {
		// TODO Auto-generated method stub
		return null;
	}

}
