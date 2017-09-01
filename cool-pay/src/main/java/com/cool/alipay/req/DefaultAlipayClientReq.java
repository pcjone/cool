package com.cool.alipay.req;
/**
 * 
* @ClassName: DefaultAlipayClientReq 
* @Description: DefaultAlipayClient请求参数
* @author panlei
* @date 2017年8月29日 下午3:02:21 
*
 */
public class DefaultAlipayClientReq {
	private String url;
	private String app_id;
	private String private_key;
	private String format;
	private String charset;
	private String alipay_public_key;
	private String sign_type;
	
	public DefaultAlipayClientReq() {
		super();
	}
	
	public DefaultAlipayClientReq(String url, String app_id, String private_key, String format, String charset,
			String alipay_public_key, String sign_type) {
		super();
		this.url = url;
		this.app_id = app_id;
		this.private_key = private_key;
		this.format = format;
		this.charset = charset;
		this.alipay_public_key = alipay_public_key;
		this.sign_type = sign_type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getPrivate_key() {
		return private_key;
	}
	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getAlipay_public_key() {
		return alipay_public_key;
	}
	public void setAlipay_public_key(String alipay_public_key) {
		this.alipay_public_key = alipay_public_key;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
	
}
