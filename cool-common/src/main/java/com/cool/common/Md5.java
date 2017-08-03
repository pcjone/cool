package com.cool.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	/**利用MD5进行加密
	 * @throws IOException */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, IOException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        Base64 base64en = new Base64();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
    
    /**判断用户密码是否正确
     *newpasswd  用户输入的密码
     *oldpasswd  正确密码
     * @throws IOException */
    public static boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, IOException{
        if(EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
    
    public static void main(String[] args) {
    		try {
				System.out.println(EncoderByMd5("123456"));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
}
