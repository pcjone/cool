package com.cool.model;

import com.cool.base.BaseModel;
/**
 * 
* @ClassName: WxArticleNews 
* @Description: 多图文关系
* @author panlei
* @date 2017年8月15日 下午3:43:33 
*
 */
public class WxArticleNews extends BaseModel{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	
	private Long articleId;
	
	private Long newsId;

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
}
