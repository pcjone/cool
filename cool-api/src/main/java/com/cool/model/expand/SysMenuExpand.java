package com.cool.model.expand;

import java.util.List;

import com.cool.model.SysMenu;

public class SysMenuExpand extends SysMenu{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1059613784387071214L;
	
	private boolean hasChild;
	
	private List<SysMenuExpand> childSysMenu;

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public List<SysMenuExpand> getChildSysMenu() {
		return childSysMenu;
	}

	public void setChildSysMenu(List<SysMenuExpand> childSysMenu) {
		this.childSysMenu = childSysMenu;
	}

}
