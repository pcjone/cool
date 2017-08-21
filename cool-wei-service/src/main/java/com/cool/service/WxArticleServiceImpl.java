package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.WxArticleService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.WxArticleMapper;
import com.cool.exception.ServiceException;
import com.cool.model.WxArticle;
import com.github.pagehelper.PageInfo;
@Service
public class WxArticleServiceImpl extends BaseServiceImpl<WxArticle> implements WxArticleService{
	
	@Autowired
	private WxArticleMapper wxArticleMapper;

	@Override
	public PageInfo<WxArticle> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<WxArticle> getMapper() {
		return wxArticleMapper;
	}

}
