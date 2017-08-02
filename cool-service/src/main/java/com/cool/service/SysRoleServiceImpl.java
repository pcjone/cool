package com.cool.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cool.Constants;
import com.cool.api.SysRoleService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.config.Resources;
import com.cool.dao.SysRoleMapper;
import com.cool.dao.SysRoleMenuMapper;
import com.cool.dao.SysUserRoleMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysRole;
import com.cool.model.SysRoleMenu;
import com.github.pagehelper.PageInfo;
/**
 * 
* @ClassName: SysRoleServiceImpl 
* @Description: 角色管理服务
* @author panlei
* @date 2017年7月28日 下午6:15:38 
*
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Override
	public PageInfo<SysRole> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
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
	
	@Transactional
	@Override
	public void deleteDBAndCache(Long[] ids) {
		super.deleteDBAndCache(ids);
		List<Long> list = new ArrayList<Long>();
		for(Long id : ids) {
			list.add(id);
		}
		sysRoleMenuMapper.deleteAllByRoleIds(list);
		sysUserRoleMapper.deleteAllByRoleIds(list);
	}
	
	@Transactional
	@Override
	public void deleteDB(Long[] ids) {
		super.deleteDBAndCache(ids);
		List<Long> list = new ArrayList<Long>();
		for(Long id : ids) {
			list.add(id);
		}
		sysRoleMenuMapper.deleteAllByRoleIds(list);
		sysUserRoleMapper.deleteAllByRoleIds(list);
	}

	@Transactional
	@Override
	public void addOrDeleteSysRoleMenu(List<Long> addMenuIds, List<Long> deleteMenuIds,Long roleId,String createBy) {
		List<SysRoleMenu> addSysRoleMenus = new ArrayList<SysRoleMenu>();
		for(Long menu : addMenuIds) {
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setMenuId(menu);
			sysRoleMenu.setRoleId(roleId);
			sysRoleMenu.setEnable(Constants.ENABLE_NO);
			sysRoleMenu.setCreateBy(createBy);
			sysRoleMenu.setCreateTime(new Date());
			addSysRoleMenus.add(sysRoleMenu);
		}
		sysRoleMenuMapper.insertBatch(addSysRoleMenus);
		for(Long menu : deleteMenuIds) {
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setMenuId(menu);
			sysRoleMenu.setRoleId(roleId);
			sysRoleMenu.setCreateBy(createBy);
			sysRoleMenu.setCreateTime(new Date());
			sysRoleMenuMapper.deleteByRecord(sysRoleMenu);
		}
	}
}
