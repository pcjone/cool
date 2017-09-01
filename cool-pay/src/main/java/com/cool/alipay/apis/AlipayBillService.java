package com.cool.alipay.apis;

import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.cool.alipay.req.BillContent;
import com.cool.alipay.req.DefaultAlipayClientReq;

public interface AlipayBillService {
	public AlipayDataDataserviceBillDownloadurlQueryResponse billRequest(DefaultAlipayClientReq req,BillContent billContent);
}
