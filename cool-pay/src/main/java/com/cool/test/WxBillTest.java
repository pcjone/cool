package com.cool.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cool.support.https.HttpRequestUtil;
import com.cool.wxpay.SignsUtil;
import com.cool.wxpay.req.WxBillReq;

public class WxBillTest {
	
	private static final Logger logger= LoggerFactory.getLogger(WxBillTest.class);
	
	public static String appid = "wx0776baa076cd275f";
	public static String key = "93bed090ad1745a184ac0f58144c2b93";
	public static String mch_id = "1264794501";
	
	public static String sub_mch_id = "1485427762";
	//1485427762
	public static String sub_mch_id1 = "1471400602";
	
	public static String sub_mch_id2 = "1304814601";
	
	public static String sub_mch_id3 = "1471400602";
	//1230000109
	//wxd678efh567hg6787
	public static void main(String[] args) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, IOException {
		WxBillReq req = new WxBillReq();
		req.setAppid(appid);
		req.setMch_id(mch_id);
		req.setSub_mch_id(sub_mch_id1);
		req.setBill_date("20170830");
		String sign = SignsUtil.signature(req, key);
		req.setSign(sign);
		String result = HttpRequestUtil.sendPost("https://api.mch.weixin.qq.com/pay/downloadbill", req);
		System.out.println(result);
		
	}
	
	
}
