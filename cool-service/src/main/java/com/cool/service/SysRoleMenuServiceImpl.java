package com.cool.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.SysRoleMenuService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.SysRoleMenuMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysRoleMenu;
import com.github.pagehelper.PageInfo;
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuService {

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Override
	public PageInfo<SysRoleMenu> query(Map<String, Object> params) throws ServiceException {
		return null;
	}

	@Override
	protected BaseMapper<SysRoleMenu> getMapper() {
		return sysRoleMenuMapper;
	}

	@Override
	public List<Long> queryMenuIdByRoleId(Long roleId) {
		return sysRoleMenuMapper.queryMenuIdByRoleId(roleId);
	}

}
