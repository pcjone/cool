package com.cool.wxpay.req;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.cool.wxpay.RandomStringGenerator;

public class WxBillReq {
	private String appid;
	private String mch_id;
	private String sub_appid;
	private String sub_mch_id;
	private String device_info;
	private String nonce_str;
	private String sign;
	private String sign_type;
	private String bill_date;
	private String bill_type;
	private String tar_type;	
	
	public WxBillReq() {
		super();
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		this.sign_type = "MD5";
		this.bill_type = "ALL";
	}
	
	public WxBillReq(String appid, String mch_id, String device_info, String nonce_str, String sign, String sign_type,
			String bill_date, String bill_type, String tar_type) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.device_info = device_info;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.sign_type = sign_type;
		this.bill_date = bill_date;
		this.bill_type = bill_type;
		this.tar_type = tar_type;
	}

	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public String getBill_type() {
		return bill_type;
	}
	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}
	public String getTar_type() {
		return tar_type;
	}
	public void setTar_type(String tar_type) {
		this.tar_type = tar_type;
	}
	
	public String getSub_appid() {
		return sub_appid;
	}

	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	
}
