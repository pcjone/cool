package com.cool.dao;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseMapper;
import com.cool.model.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole> {
	public SysRole validateName(String roleName);
	public List<SysRole> queryAll(Map<String, Object> params);
}
