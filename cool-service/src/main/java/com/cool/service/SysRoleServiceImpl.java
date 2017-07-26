package com.cool.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cool.api.SysRoleService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.exception.ServiceException;
import com.cool.model.SysRole;
import com.github.pagehelper.PageInfo;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

	@Override
	public PageInfo<SysRole> query(Map<String, Object> params) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseMapper<SysRole> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
