package com.cool.model.expand;

import java.util.List;

public class TreeView {
	//文本
	private String text;
	//节点左边的图标
	private String icon;
	//结合全局enableLinks选项为列表树节点指定URL
	private String href;
	//指定列表树的节点是否可选择
	private boolean selectable;
	private String color;
	
	private Long value;
	
	private List<TreeView> nodes;

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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<TreeView> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeView> nodes) {
		this.nodes = nodes;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
}