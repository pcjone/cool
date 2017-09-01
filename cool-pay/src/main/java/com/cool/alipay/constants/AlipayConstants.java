package com.cool.alipay.constants;
/**
 * 
* @ClassName: AlipayUrlConstants 
* @Description: 支付宝请求地址
* @author panlei
* @date 2017年8月29日 下午3:03:13 
*
 */
public class AlipayConstants {
	
	public static final String ALIPAY_REQUEST_URL = "https://openapi.alipay.com/gateway.do";
	
	public static final String ALIPAY_TRADE_PAY = "alipay.trade.pay";
	
	public static final String ALIPAY_BILL_DOWNLOAD = "alipay.data.dataservice.bill.downloadurl.query";
	
	public enum BillType{
		trade(1,"trade"),
		signcustomer(2,"signcustomer");
		
		public int key;
		public String value;
		
		private BillType(int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public static BillType get(int key) {
			BillType[] values = BillType.values();
			for (BillType object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
		
	}
}
