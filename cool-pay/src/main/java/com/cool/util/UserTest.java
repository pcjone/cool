package com.cool.util;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("user")
public class UserTest{
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("value")
	private String value;
	@XStreamAlias("phone")
	private String phone;
	@XStreamAlias("list")
	private List<PhoneTest> list;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<PhoneTest> getList() {
		return list;
	}
	public void setList(List<PhoneTest> list) {
		this.list = list;
	}

}
