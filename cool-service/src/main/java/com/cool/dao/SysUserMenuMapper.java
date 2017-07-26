package com.cool.dao;

import java.util.List;

import com.cool.base.BaseMapper;
import com.cool.model.SysUserMenu;

public interface SysUserMenuMapper extends BaseMapper<SysUserMenu> {
	public List<SysUserMenu> queryPermissionsByUserId(Long userId);
}
