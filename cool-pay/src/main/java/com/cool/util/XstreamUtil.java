package com.cool.util;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @ClassName: XstreamUtil
 * @Description: xml工具类
 * @author panlei
 * @date 2017年8月31日 下午4:34:33
 *
 */
public class XstreamUtil {
	/**
	 * 
	 * @Title: ObjectToXml @Description: 将bean转换为xml @param @param
	 * object @param @return @return String @throws
	 */
	public static String ObjectToXml(Object object) {
		XStream xStream = new XStream();
		// xstream使用注解转换
		xStream.processAnnotations(object.getClass());
		return xStream.toXML(object);
	}
	
	/**
	 * 
	* @Title: XmlToObject 
	* @Description: 将xml转换为bean
	* @param @param xml
	* @param @param <T> 泛型
	* @param @return     
	* @return T    
	* @throws
	 */
	public static <T> T XmlToObject(String xml, Class<T> cls) {
		XStream xstream = new XStream(new DomDriver());
		// xstream使用注解转换
		xstream.processAnnotations(cls);
		return (T) xstream.fromXML(xml);
	}
	
	/*
	 * 测试
	 */
	public static void main(String[] args) {
		List<PhoneTest> list = new ArrayList<PhoneTest>();
		UserTest test = new UserTest();
		test.setName("panlei");
		test.setValue("test");
		test.setPhone("151....");
		PhoneTest a = new PhoneTest();
		a.setCellPhone("123");
		a.setPhone("234");
		list.add(a);
		PhoneTest b = new PhoneTest();
		b.setCellPhone("098");
		b.setPhone("765");
		list.add(b);
		//test.setList(list);
		String xml = XstreamUtil.ObjectToXml(test);
		System.out.println(xml);
		XStream xstream = new XStream(new DomDriver());
		UserTest obj = (UserTest)xstream.fromXML(xml);
		//UserTest obj = XmlToObject(xml,UserTest.class);
		System.out.println(obj.getName());
		System.out.println(obj.getPhone());
		System.out.println(obj.getValue());
		//System.out.println(obj.getList().get(0).getPhone());
	}
	
	
}


