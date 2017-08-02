package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysMenu;

public interface SysMenuService extends BaseService<SysMenu>{
	/**
	 * 
	* @Title: querySysMenuByUserId 
	* @Description: 登录查询用户权限
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> querySysMenuByUserId(Map<String,Object> params);
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
	public List<SysMenu> queryListMenuTree(Map<String,Object> params);
	
}
