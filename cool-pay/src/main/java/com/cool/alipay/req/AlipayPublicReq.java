package com.cool.alipay.req;
/**
 * 
* @ClassName: AlipayPublicReq 
* @Description: 支付宝公共请求
* @author panlei
* @date 2017年8月29日 下午3:01:58 
*
 */
public class AlipayPublicReq {
	/*
	 * 支付宝分配给开发者的应用ID 
	 */
	private String app_id;
	/*
	 * 接口名称 
	 */
	private String method;
	/*
	 * 非必填/仅支持JSON 
	 */
	private String format;
	/*
	 * 请求使用的编码格式
	 */
	private String charset;
	/*
	 * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
	 */
	private String sign_type;
	/*
	 * 商户请求参数的签名串
	 */
	private String sign;
	/*
	 * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss" 
	 */
	private String timestamp;
	/*
	 * 调用的接口版本，固定为：1.0 
	 */
	private String version;
	/*
	 * 非必填/应用授权
	 */
	private String app_auth_token;
	/*
	 * 请求参数的集合
	 */
	private String biz_content;
	public AlipayPublicReq(String app_id, String method, String format, String charset, String sign_type, String sign,
			String timestamp, String version, String app_auth_token, String biz_content) {
		super();
		this.app_id = app_id;
		this.method = method;
		this.format = format;
		this.charset = charset;
		this.sign_type = sign_type;
		this.sign = sign;
		this.timestamp = timestamp;
		this.version = version;
		this.app_auth_token = app_auth_token;
		this.biz_content = biz_content;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
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
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getApp_auth_token() {
		return app_auth_token;
	}
	public void setApp_auth_token(String app_auth_token) {
		this.app_auth_token = app_auth_token;
	}
	public String getBiz_content() {
		return biz_content;
	}
	public void setBiz_content(String biz_content) {
		this.biz_content = biz_content;
	}
	
	@Override
	public String toString() {
		return "AlipayBillReq [app_id=" + app_id + ", method=" + method + ", format=" + format + ", charset=" + charset
				+ ", sign_type=" + sign_type + ", sign=" + sign + ", timestamp=" + timestamp + ", version=" + version
				+ ", app_auth_token=" + app_auth_token + ", biz_content=" + biz_content + "]";
	}
	
}
