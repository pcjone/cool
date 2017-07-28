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
	/**
	 * 类型
	 */
	private String category;
	/**
	 * 描述
	 */
	private String categoryName;
	/**
	 * 属性值
	 */
	private String codeValue;
	/**
	 * 属性名称
	 */
	private String codeText;
	/**
	 * 排序
	 */
	private Integer sortNo;
	private Boolean editable;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
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

	
}
