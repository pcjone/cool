package com.cool.wxpay;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

public class SignsUtil {
	
	public static String mapSort(Object obj) {
		String sign = "";
		String json = JSON.toJSONString(obj);
		Map map = JSON.parseObject(json);
		Collection<String> keyset= map.keySet();   		  
		List list=new ArrayList<String>(keyset);  		  
		Collections.sort(list); 
		for(int i=0;i<list.size();i++){
			sign += list.get(i)+"="+map.get(list.get(i))+"&";
		}
		return sign;
	}
	
	public static String signature(Object sign,String key) {		
		String stringSignTemp = mapSort(sign)+"key="+key;
		return MD5.MD5Encode(stringSignTemp).toUpperCase();
	}
	
}
