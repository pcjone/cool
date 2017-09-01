package com.cool.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("phoneTest")
public class PhoneTest {
	@XStreamAlias("phone")
	private String phone;
	@XStreamAlias("cellPhone")
	private String cellPhone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	
}
