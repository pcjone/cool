package com.cool.dao;

import java.util.Map;

import com.cool.base.BaseMapper;
import com.cool.model.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser>{
	public SysUser queryUserByName(Map<String, Object> params);
	
	public int updatePassword(SysUser record);
}
