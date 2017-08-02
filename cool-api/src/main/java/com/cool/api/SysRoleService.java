package com.cool.api;

import java.util.List;

import com.cool.base.BaseService;
import com.cool.model.SysRole;

public interface SysRoleService extends BaseService<SysRole>{
	public void validateName(String roleName);
	public void addOrDeleteSysRoleMenu(List<Long> addMenuIds,List<Long> deleteMenuIds,Long roleId,String createBy);
}
