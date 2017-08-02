package com.cool.api;

import java.util.List;

import com.cool.base.BaseService;
import com.cool.model.SysRoleMenu;

public interface SysRoleMenuService extends BaseService<SysRoleMenu>{
	public List<Long> queryMenuIdByRoleId(Long roleId);
}
