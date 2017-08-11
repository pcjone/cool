package com.cool.dao;

import com.cool.base.BaseMapper;
import com.cool.model.WeiUser;

public interface WeiUserMapper extends BaseMapper<WeiUser>{
	public WeiUser queryByOpenId(String openId);
}
