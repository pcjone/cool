package com.cool.model;

import com.cool.base.BaseModel;

import java.util.Date;

public class ${table_name} extends BaseModel{

	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = 1L;

<#if model_column?exists>
<#list model_column as model>
	/**
	*${model.columnComment!}
	*/
	<#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
	private String ${model.changeColumnName?uncap_first};	
	</#if>
	    
	<#if (model.columnType = 'BIGINT' || model.columnType = 'INT' || model.columnType = 'SMALLINT')>
	private Long ${model.changeColumnName?uncap_first};	
	</#if>
	
	<#if (model.columnType = 'TINYINT')>
	private boolean ${model.changeColumnName?uncap_first};	
	</#if>
	    
	<#if model.columnType = 'TIMESTAMP' || model.columnType = 'DATE' || model.columnType = 'DATETIME'>
	private Date ${model.changeColumnName?uncap_first};
	</#if>
</#list>
</#if>
	
<#if model_column?exists>
<#list model_column as model>
	<#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
	public String get${model.changeColumnName}() {
		return this.${model.changeColumnName?uncap_first};
	}
	
	public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
		this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
	}
	
	</#if>
	<#if model.columnType = 'TIMESTAMP' || model.columnType = 'DATE' || model.columnType = 'DATETIME'>
	public Date get${model.changeColumnName}() {
		return this.${model.changeColumnName?uncap_first};
	}
		
	public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
		this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
	}		
	</#if>
	
	<#if model.columnType = 'BIGINT' || model.columnType = 'INT' || model.columnType = 'SMALLINT'>
	public Long get${model.changeColumnName}() {
		return this.${model.changeColumnName?uncap_first};
	}
		
	public void set${model.changeColumnName}(Long ${model.changeColumnName?uncap_first}) {
		this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
	}		
	</#if>
	
	<#if (model.columnType = 'TINYINT')>
	public boolean get${model.changeColumnName}() {
		return this.${model.changeColumnName?uncap_first};
	}
		
	public void set${model.changeColumnName}(boolean ${model.changeColumnName?uncap_first}) {
		this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
	}	
	</#if>
</#list>
</#if>

}