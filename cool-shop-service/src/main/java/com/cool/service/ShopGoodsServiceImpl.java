package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.ShopGoodsService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.ShopGoodsMapper;
import com.cool.exception.ServiceException;
import com.cool.model.ShopGoods;
import com.github.pagehelper.PageInfo;
@Service
public class ShopGoodsServiceImpl extends BaseServiceImpl<ShopGoods> implements ShopGoodsService{
	
	@Autowired
	private ShopGoodsMapper shopGoodsMapper;

	@Override
	public PageInfo<ShopGoods> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<ShopGoods> getMapper() {
		return shopGoodsMapper;
	}

}
