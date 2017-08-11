package com.cool.model;

import java.util.List;

import com.cool.base.BaseModel;
/**
 * 
* @ClassName: WxArticle 
* @Description: 多图文消息
* @author panlei
* @date 2017年8月9日 上午11:33:22 
*
 */
public class WxArticle extends BaseModel{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	
	private Long articleCount;
	
	private List<WxNews> articles;
	
	private String title;
	
	private String description;

	public Long getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Long articleCount) {
		this.articleCount = articleCount;
	}

	public List<WxNews> getArticles() {
		return articles;
	}

	public void setArticles(List<WxNews> articles) {
		this.articles = articles;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
