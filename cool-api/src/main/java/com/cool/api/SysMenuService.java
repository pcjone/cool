package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysMenu;
import com.cool.model.expand.SysMenuExpand;

public interface SysMenuService extends BaseService<SysMenu>{
	/**
	 * 
	* @Title: querySysMenuByUserId 
	* @Description: 登录查询用户权限(组装成tree)
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenuExpand> queryMenuListByUserId(Map<String,Object> params);
	/**
	 * 
	* @Title: queryPermissionByUserId 
	* @Description: 查询shiro权限
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> queryPermissionByUserId(Map<String,Object> params);
	/**
	 * 
	* @Title: querySysMenuByRolerId 
	* @Description: 根据角色查询所有菜单树
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> querySysMenuByRolerId(Map<String,Object> params);	
	/**
	 * 
	* @Title: queryListMenuTree 
	* @Description: 查询所有菜单树
	* @param @param params
	* @param @return     
	* @return List<SysMenuTree>    
	* @throws
	 */
	public List<SysMenuExpand> queryListMenuTree(Map<String,Object> params);
	
}
