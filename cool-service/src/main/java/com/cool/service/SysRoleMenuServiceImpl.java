package com.cool.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cool.api.SysRoleMenuService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.exception.ServiceException;
import com.cool.model.SysRoleMenu;
import com.github.pagehelper.PageInfo;
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuService {

	@Override
	public PageInfo<SysRoleMenu> query(Map<String, Object> params) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseMapper<SysRoleMenu> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
