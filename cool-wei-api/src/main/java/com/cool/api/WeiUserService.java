package com.cool.api;

import com.cool.base.BaseService;
import com.cool.model.WeiUser;

public interface WeiUserService extends BaseService<WeiUser>{
	public WeiUser queryByOpenId(String openId);
}
