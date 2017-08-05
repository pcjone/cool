package com.cool.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cool.Constants;
import com.cool.api.SysMenuService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.SysMenuMapper;
import com.cool.dao.SysRoleMenuMapper;
import com.cool.dao.SysUserMenuMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysMenu;
import com.cool.model.expand.SysMenuExpand;
import com.github.pagehelper.PageInfo;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService{

	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Autowired
	private SysUserMenuMapper sysUserMenuMapper;
	
	@Override
	public PageInfo<SysMenu> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<SysMenu> getMapper() {
		return sysMenuMapper;
	}
	
	@Transactional
	@Override
	public void deleteDBAndCache(Long[] ids) {
		super.deleteDBAndCache(ids);
		List<Long> list = new ArrayList<Long>();
		for(Long id : ids) {
			list.add(id);
		}
		sysRoleMenuMapper.deleteAllByMenuIds(list);
		sysUserMenuMapper.deleteAllByMenuIds(list);
	}
	
	@Transactional
	@Override
	public void deleteDB(Long[] ids) {
		super.deleteDBAndCache(ids);
		List<Long> list = new ArrayList<Long>();
		for(Long id : ids) {
			list.add(id);
		}
		sysRoleMenuMapper.deleteAllByMenuIds(list);
		sysUserMenuMapper.deleteAllByMenuIds(list);
	}

	@Override
	public List<SysMenuExpand> queryMenuListByUserId(Map<String, Object> params) {
		List<SysMenu> sysMenuList = sysMenuMapper.queryMenuListByUserId(params);
		return getChild(sysMenuList,Constants.PERMISSION_ZERO);
	}
	
	@Override
	public List<SysMenu> querySysMenuByRolerId(Map<String, Object> params) {
		List<SysMenu> sysMenuList = sysMenuMapper.querySysMenuByRoleId(params);
		return sysMenuList;
	}
	
	@Override
	public List<SysMenuExpand> queryListMenuTree(Map<String, Object> params) {
		List<SysMenu> sysMenuList = sysMenuMapper.queryListMenuTree(params);
		return getChild(sysMenuList,Constants.PERMISSION_ZERO);
	}
	
	/**
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * 
	* @Title: getChild 
	* @Description: 对list进行组装成tree结构list
	* @param @param sysMenuList
	* @param @param parentId
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenuExpand> getChild(List<SysMenu> sysMenuList,Long parentId){
		List<SysMenuExpand> returnSysMenu = new ArrayList<SysMenuExpand>();
		//循环查询parentId相等的
		for(SysMenu tree : sysMenuList) {
			SysMenuExpand treeExpand = new SysMenuExpand();
			try {
				BeanUtils.copyProperties(treeExpand,tree);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			if(tree.getParentId().equals(parentId)) {
				List<SysMenuExpand> child = getChild(sysMenuList,tree.getId());			
				if(child != null && child.size()>0) {
					treeExpand.setChildSysMenu(child);
					treeExpand.setHasChild(true);
				}else {
					treeExpand.setHasChild(false);
				}
				returnSysMenu.add(treeExpand);
			}
		}
		return returnSysMenu;
	}

	@Override
	public List<SysMenu> queryPermissionByUserId(Map<String, Object> params) {
		return sysMenuMapper.queryMenuListByUserId(params);
	}
}
