package com.cool.dao;

import java.util.List;

import com.cool.base.BaseMapper;
import com.cool.model.SysRoleMenu;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
	/**
	 * 
	* @Title: selectByRoleId 
	* @Description: 根据roleId查询
	* @param @param roleId
	* @param @return     
	* @return SysRoleMenu    
	* @throws
	 */
	public List<SysRoleMenu> selectByRoleId(Long roleId);
	/**
	 * 
	* @Title: selectAllByRoleIds 
	* @Description: 根据roleId查询
	* @param @param list
	* @param @return     
	* @return List<SysRoleMenu>    
	* @throws
	 */
	public List<SysRoleMenu> selectAllByRoleIds(List<Long> list);
	/**
	 * 
	* @Title: deleteAllByRoleIds 
	* @Description: 根据roleIds删除
	* @param @param list     
	* @return void    
	* @throws
	 */
	public void deleteAllByRoleIds(List<Long> list);
	/**
	 * 
	* @Title: deleteAllByMenuIds 
	* @Description: 根据MenuIds删除
	* @param @param list     
	* @return void    
	* @throws
	 */
	public void deleteAllByMenuIds(List<Long> list);
	/**
	 * 
	* @Title: deleteByRecord 
	* @Description: 根据roleId跟menuId删除权限
	* @param @param sysMenuRole
	* @param @return     
	* @return int    
	* @throws
	 */
	public int deleteByRecord(SysRoleMenu sysRoleMenu);
	/**
	 * 
	* @Title: queryMenuIdByRoleId 
	* @Description: 根据roleId查询菜单id
	* @param @param roleId
	* @param @return     
	* @return List<Long>    
	* @throws
	 */
	public List<Long> queryMenuIdByRoleId(Long roleId);
}
