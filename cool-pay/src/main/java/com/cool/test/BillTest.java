package com.cool.test;

import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.cool.alipay.apis.AlipayBillService;
import com.cool.alipay.apis.impl.AlipayBillServiceImpl;
import com.cool.alipay.constants.AlipayConstants;
import com.cool.alipay.req.BillContent;
import com.cool.alipay.req.DefaultAlipayClientReq;
import com.cool.util.DateUtil;

public class BillTest {
	
	public static String app_id_1 = "2016060301477280";
	
	public static String private_key_1 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANYk  +hRtfrxXVD0BFMzlWK0VxarmTdCh6vWpHo28hNes2TRoqnyukb2UWBjQui2JZUj31u5ObvO9Qrug+6p/osCVXR00XU  +Ffpwk6MJ82W9/iia1RR4dfHLBB1P7Fe366stBrqZXZQNSSv9hzejKJGnXX26x2CpFEjYNR43MnuubAgMBAAECgYBQ3  /AurNeza8vdqj3f0DI4VEOt8oKq66vgEXn/j5YgGiCCAZF5uzhBf/fxj0rxDbXZ4/ghUi/nD7BXSkohr0iYf  +LK1YgJ+7zWyAZULjw  +ZT4aMIaHAvVdRKD8p9wYFLrwh40KAMX5YNqdpT3wbnFYJcQXK9b2a6i3SZV88Hf06QJBAPi/zK  +CLfFi8LvWTOl5wEXrd9LWEuZv0F0x2kxK4Oew6am6ONslB2Swx1KZNiZENv66ZKtmzYdRhqLhihgpG28CQQDcYvOn8  NifCDrMDbr7WQRdH03u099Qcqp1JTzbamAhn5TX47KBmPanrGBLkm36r+FaPX7xfzAtVOwAfQMNw0yVAkBJPS  +m/U1JpTf8FjzPAWn7LDk7eoxWDNXsmqELlYBit1QVBR94Zo6mtuX5C  +NFoRAQMBEz5jDdhsN9caqXMoNBAkBduxQH7C4lTRm8qWZJ  +i0Z8fNyTNggXfRTSFgetZpXEoj6XNQRvF3DKFP4MDnoMZ91gswXp9F0pjaNap2+374VAkEAhVk9O2pU5RIhVikTqJz  CAz5PI09npD1t0EOA44WU4K3jnjISDBRoZgP6Cf1PpOCmBMgyWHns5cJ1XkuMDijrQw==";
	
	public static String app_id_2 = "2016080201694692";
	
	public static String private_key_2 = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM5xTkYWbXufzswHqfoyRIOox59F+aYN1lxKcvAvHglgO/fp/mFZOKRGPXfBxjB2kL9n5Xj6WWyO7kq/Y5pXyC8PhPRcWQ4nocIqVQrKJGGDYd1hf+O61c8n6i0gb206L70RkBBwqJVkga7t5fVyvno/YhJAMpF+jwm1c9UDOWSLAgMBAAECgYEAg5XrK3duL4dMO/aCnKxfyX/SRa5Hk1VslgUKQBf4UEeb0ci/UPOay/LNiUDk/80DNE2YEcWZLD3qm5Bkf2OGf3tywNgtaO5kfUhJGyhG9QCtpfT/o8ddt9+H9AumxxUAv51zDnGpxThkwGYJFwhNQUVaae0o8gkO5Z5rnWFi0MkCQQDtJjhhoLmz/14fow1jkpevag1uVswJFOYAxQZoYFO2JAasocMXHmfG+il+nJFIIZOnPrDrV5KdI6MwjEveeIi9AkEA3to7MTrxZnSubE4v0PtMdMv1wgN3PdhG131gshjgRBjr4zOdU5svYqw7O9xRfBy4+7nasBhjapEH0/JUOBMq5wJAfG5oePlPVx2v8mOoe82IH3dC8tlCF6XKhS7cDNo2MkS258+8wJ2okCPkr9VQ+Yr8oW1ogSOi/yaa0rdae7G89QJBAIK368Si2HrKso1hB3dEEMLp6IwXUFh8hoFcT93n7ZTEMYmh+wTRoGf2swAX6cwtZOxzRi3JAHe//qF6IaCKzb0CQBj9YJUefka5Jl9fYPVnRD6COk2dRvUybEPnuVgvP5lMB8A06/cEo3g2Msjmgzz5HnRNqAgsWz6kzMmJ/nDSmHU=";
	
	public static String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	
	public static void main(String[] args) {
		String bill_date = "2017-08-30";//DateUtil.getYesterday();
		BillContent billContent = new BillContent(AlipayConstants.BillType.trade.value,bill_date);
		DefaultAlipayClientReq req = new DefaultAlipayClientReq();
		req.setApp_id(app_id_2);
		req.setFormat("json");
		req.setCharset("GBK");
		req.setPrivate_key(private_key_2);
		//req.setSign_type("RSA2");
		req.setUrl(AlipayConstants.ALIPAY_REQUEST_URL);
		req.setAlipay_public_key(public_key);
		AlipayBillService billService = new AlipayBillServiceImpl();
		AlipayDataDataserviceBillDownloadurlQueryResponse message = billService.billRequest(req, billContent);
		System.out.println(message.getCode());
		System.out.println(message.getBillDownloadUrl());
	}

}
