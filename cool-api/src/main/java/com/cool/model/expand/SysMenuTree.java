package com.cool.model.expand;

import java.util.List;

import com.cool.model.SysMenu;

public class SysMenuTree extends SysMenu{
	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = -1940519218232470956L;
	
	private boolean hasChild;
	
	private List<SysMenuTree> childSysMenuTree;

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public List<SysMenuTree> getChildSysMenuTree() {
		return childSysMenuTree;
	}

	public void setChildSysMenuTree(List<SysMenuTree> childSysMenuTree) {
		this.childSysMenuTree = childSysMenuTree;
	}

}
