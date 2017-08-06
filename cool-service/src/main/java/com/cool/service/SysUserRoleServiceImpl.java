package com.cool.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.SysUserRoleService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.SysUserRoleMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysUserRole;
import com.github.pagehelper.PageInfo;

@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService{

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Override
	public PageInfo<SysUserRole> query(Map<String, Object> params) throws ServiceException {
		return null;
	}	

	@Override
	protected BaseMapper<SysUserRole> getMapper() {
		return sysUserRoleMapper;
	}
	
	@Override
	public List<Long> queryRoleInfo(Map<String, Object> params) {
		return sysUserRoleMapper.queryRoleInfo(params);
	}

}
