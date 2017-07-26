package com.cool.dao;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseMapper;
import com.cool.model.SysMenu;
import com.cool.model.expand.SysMenuTree;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
	/**
	 * 
	* @Title: querySysMenuByUserId 
	* @Description: 根据用户id查询所有权限
	* @param @param userId
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> querySysMenuByUserId(Long userId);
	
	public List<SysMenu> queryMenuListByUserId(Long userId);
	/**
	 * 
	* @Title: queryMenuTree 
	* @Description: 查询用户顶级菜单
	* @param @param params
	* @param @return     
	* @return List<SysMenuTree>    
	* @throws
	 */
	public List<SysMenuTree> queryMenuTree(Map<String,Object> params);
}
