package com.cool.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.SysUserMenuService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.SysUserMenuMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysUserMenu;
import com.github.pagehelper.PageInfo;

@Service
public class SysUserMenuServiceImpl extends BaseServiceImpl<SysUserMenu> implements SysUserMenuService {

	@Autowired
	private SysUserMenuMapper sysUserMenuMapper;
	
	@Override
	public PageInfo<SysUserMenu> query(Map<String, Object> params) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseMapper<SysUserMenu> getMapper() {
		return sysUserMenuMapper;
	}

	@Override
	public List<SysUserMenu> queryPermissionsByUserId(Long userId) {
		return sysUserMenuMapper.queryPermissionsByUserId(userId);
	}

	
}
