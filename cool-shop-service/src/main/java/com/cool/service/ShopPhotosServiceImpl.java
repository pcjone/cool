package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.ShopPhotosService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.ShopPhotosMapper;
import com.cool.exception.ServiceException;
import com.cool.model.ShopPhotos;
import com.github.pagehelper.PageInfo;
@Service
public class ShopPhotosServiceImpl extends BaseServiceImpl<ShopPhotos> implements ShopPhotosService{
	
	@Autowired
	private ShopPhotosMapper shopPhotosMapper;

	@Override
	public PageInfo<ShopPhotos> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<ShopPhotos> getMapper() {
		return shopPhotosMapper;
	}

}
