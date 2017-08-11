package com.cool.model;

import com.cool.base.BaseModel;
/**
 * 
* @ClassName: WxNews 
* @Description: 微信图文消息
* @author panlei
* @date 2017年8月9日 上午10:20:52 
*
 */
public class WxNews extends BaseModel{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	
	private String title;
	
	private String description;
	
	private String picUrl;
	
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
