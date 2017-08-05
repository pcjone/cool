package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysRole;

public interface SysRoleService extends BaseService<SysRole>{
	public void validateName(String roleName);
	public void addOrDeleteSysRoleMenu(List<Long> addMenuIds,List<Long> deleteMenuIds,Long roleId,String createBy);
	public List<SysRole> queryAll(Map<String,Object> params);
}
