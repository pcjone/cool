package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.WxNewsService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.WxNewsMapper;
import com.cool.exception.ServiceException;
import com.cool.model.WxNews;
import com.github.pagehelper.PageInfo;

@Service
public class WxNewsServiceImpl extends BaseServiceImpl<WxNews> implements WxNewsService{
	
	@Autowired
	private WxNewsMapper wxNewsMapper;

	@Override
	public PageInfo<WxNews> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<WxNews> getMapper() {
		return wxNewsMapper;
	}

}
