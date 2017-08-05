package com.cool.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cool.Constants;
import com.cool.api.SysUserService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.SysUserMapper;
import com.cool.dao.SysUserRoleMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysUser;
import com.cool.model.SysUserRole;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
/**
 * 
* @ClassName: SysUserServiceImpl 
* @Description: 用户管理
* @author panlei
* @date 2017年7月18日 下午2:01:40 
*
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	protected BaseMapper<SysUser> getMapper() {
		return sysUserMapper;
	}

	@Override
	public PageInfo<SysUser> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	public SysUser queryUserByName(Map<String, Object> params) {
		return sysUserMapper.queryUserByName(params);
	}
	@Transactional
	@Override
	public void addOrDeleteSysRoleUser(List<Long> addRoleIds, List<Long> deleteRoleIds, Long userId, String curUser) {
		List<SysUserRole> sysUserRoles = new ArrayList<SysUserRole>();
		for(Long role : addRoleIds) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setCreateBy(curUser);
			sysUserRole.setCreateTime(new Date());
			sysUserRole.setEnable(Constants.ENABLE_NO);
			sysUserRole.setRoleId(role);
			sysUserRole.setUserId(userId);
			sysUserRoles.add(sysUserRole);
		}
		if(sysUserRoles != null && sysUserRoles.size()>0) {
			sysUserRoleMapper.insertBatch(sysUserRoles);
		}
		for(Long role : deleteRoleIds) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setRoleId(role);
			sysUserRole.setUserId(userId);
			sysUserRoleMapper.deleteByRecord(sysUserRole);
		}
	}

}
