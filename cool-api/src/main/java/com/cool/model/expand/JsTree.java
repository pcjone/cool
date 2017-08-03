package com.cool.model.expand;

import java.util.List;
/**
 * 
* @ClassName: JsTree 
* @Description: 角色管理树
* @author panlei
* @date 2017年8月1日 下午5:26:39 
*
 */
public class JsTree {
	private Long id;
	private String text;
	private String icon;
	private List<JsTree> children; 
	private State State;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<JsTree> getChildren() {
		return children;
	}
	public void setChildren(List<JsTree> children) {
		this.children = children;
	}
	public State getState() {
		return State;
	}
	public void setState(State state) {
		State = state;
	}
	
}