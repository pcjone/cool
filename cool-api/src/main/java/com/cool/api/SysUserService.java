package com.cool.api;

import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysUser;

public interface SysUserService extends BaseService<SysUser>{
	public SysUser queryUserByName(Map<String, Object> params);
}
