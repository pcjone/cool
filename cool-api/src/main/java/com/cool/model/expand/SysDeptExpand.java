package com.cool.model.expand;

import java.util.List;

import com.cool.model.SysDept;

public class SysDeptExpand extends SysDept{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7444103810559915308L;
	
	private boolean hasChild;
	
	private List<SysDeptExpand> childSysDept;

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public List<SysDeptExpand> getChildSysDept() {
		return childSysDept;
	}

	public void setChildSysDept(List<SysDeptExpand> childSysDept) {
		this.childSysDept = childSysDept;
	}
}
