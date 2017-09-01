package com.cool.alipay.apis.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.cool.alipay.apis.AlipayBillService;
import com.cool.alipay.req.BillContent;
import com.cool.alipay.req.DefaultAlipayClientReq;

public class AlipayBillServiceImpl implements AlipayBillService {

	@Override
	public AlipayDataDataserviceBillDownloadurlQueryResponse billRequest(DefaultAlipayClientReq req, BillContent billContent) {
		AlipayClient alipayClient = new DefaultAlipayClient(req.getUrl(),req.getApp_id(),req.getPrivate_key(),req.getFormat(),req.getCharset(),req.getAlipay_public_key());
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		request.setBizContent(billContent.toJson(billContent));
		request.putOtherTextParam("", "");
		AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
		try {
			response = alipayClient.execute(request);			
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return response;	
	}

}
