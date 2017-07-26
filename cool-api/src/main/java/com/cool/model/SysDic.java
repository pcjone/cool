package com.cool.model;

import com.cool.base.BaseModel;

/**
 * 
 * @ClassName: SysDic
 * @Description: 数据字典
 * @author panlei
 * @date 2017年7月19日 上午10:11:36
 *
 */
public class SysDic extends BaseModel {

	/**
	 * @Fields serialVersionUID : 序列化
	 */
	private static final long serialVersionUID = -7492985842283756943L;

	private String type;
	private String code;
	private String codeText;
	private Integer sortNo;
	private Boolean editable;
	private String parentType;
	private String parentCode;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeText() {
		return codeText;
	}

	public void setCodeText(String codeText) {
		this.codeText = codeText;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}
