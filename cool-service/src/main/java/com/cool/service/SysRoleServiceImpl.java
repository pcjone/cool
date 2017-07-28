package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.SysRoleService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.config.Resources;
import com.cool.dao.SysRoleMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysRole;
import com.github.pagehelper.PageInfo;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public PageInfo<SysRole> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(sysRoleMapper.queryForList(params));
	}

	@Override
	protected BaseMapper<SysRole> getMapper() {
		return sysRoleMapper;
	}

	@Override
	public void validateName(String roleName) {
		SysRole role = sysRoleMapper.validateName(roleName);
		if(role != null) {
			throw new ServiceException(Resources.getMessage("角色名称已存在"));
		}
	}

}
