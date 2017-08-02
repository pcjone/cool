package com.cool.dao;

import java.util.List;

import com.cool.base.BaseMapper;
import com.cool.model.SysUserMenu;

public interface SysUserMenuMapper extends BaseMapper<SysUserMenu> {
	/**
	 * 
	* @Title: selectByUserId 
	* @Description: 根据UserId查询
	* @param @param UserId
	* @param @return     
	* @return SysUserMenu    
	* @throws
	 */
	public List<SysUserMenu> selectByUserId(Long UserId);
	/**
	 * 
	* @Title: selectAllByUserIds 
	* @Description: 根据UserId查询
	* @param @param list
	* @param @return     
	* @return List<SysUserMenu>    
	* @throws
	 */
	public List<SysUserMenu> selectAllByUserIds(List<Long> list);
	/**
	 * 
	* @Title: deleteAllByUserIds 
	* @Description: 根据UserIds删除
	* @param @param list     
	* @return void    
	* @throws
	 */
	public void deleteAllByUserIds(List<Long> list);
	/**
	 * 
	* @Title: deleteAllByMenuIds 
	* @Description: 根据MenuIds删除
	* @param @param list     
	* @return void    
	* @throws
	 */
	public void deleteAllByMenuIds(List<Long> list);
}
