package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.ShopShopsService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.ShopShopsMapper;
import com.cool.exception.ServiceException;
import com.cool.model.ShopShops;
import com.github.pagehelper.PageInfo;
@Service
public class ShopShopsServiceImpl extends BaseServiceImpl<ShopShops> implements ShopShopsService{
	
	@Autowired
	private ShopShopsMapper shopShopsMapper;

	@Override
	public PageInfo<ShopShops> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<ShopShops> getMapper() {
		return shopShopsMapper;
	}

	@Override
	public ShopShops queryShopByUserId(Long userId) {
		return shopShopsMapper.queryShopByUserId(userId);
	}

}
