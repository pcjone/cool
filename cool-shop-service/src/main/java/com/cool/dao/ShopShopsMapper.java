package com.cool.dao;

import com.cool.base.BaseMapper;
import com.cool.model.ShopShops;

public interface ShopShopsMapper extends BaseMapper<ShopShops>{
	public ShopShops queryShopByUserId(Long userId);
}
