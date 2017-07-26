package com.cool.api;

import java.util.List;

import com.cool.base.BaseService;
import com.cool.model.SysUserMenu;

public interface SysUserMenuService extends BaseService<SysUserMenu>{
	public List<SysUserMenu> queryPermissionsByUserId(Long userId);
}
