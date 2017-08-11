package com.cool.model.expand;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class MenuTree implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	private String id;
	private Boolean isHeader;
	private Boolean isOpen;
	private String text;
	private String icon;
	private String targetType;
	private String url;
	private List<MenuTree> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIsHeader() {
		return isHeader;
	}
	public void setIsHeader(Boolean isHeader) {
		this.isHeader = isHeader;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
	
	
	public static void main(String[] args) {
		List<MenuTree> menu = new ArrayList<>();
		MenuTree tree = new MenuTree();
		tree.setId("1");
		tree.setText("我的工作台");
		tree.setIsHeader(true);
		menu.add(tree);
		tree = new MenuTree();
		tree.setId("2");
		tree.setText("子几");
		tree.setIsOpen(true);
		tree.setIcon("3456789");
		menu.add(tree);
		System.out.println(JSON.toJSONString(menu));
	}
}
