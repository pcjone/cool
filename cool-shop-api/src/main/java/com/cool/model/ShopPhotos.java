package com.cool.model;

import com.cool.base.BaseModel;

import java.util.Date;

public class ShopPhotos extends BaseModel{

	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	private String path;	
	    
	
	    
	/**
	*
	*/
	private String tableName;	
	    
	
	    
	
	public String getPath() {
		return this.path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	public String getTableName() {
		return this.tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	

}