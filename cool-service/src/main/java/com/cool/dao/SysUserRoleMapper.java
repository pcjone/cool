package com.cool.dao;

import java.util.List;

import com.cool.base.BaseMapper;
import com.cool.model.SysUserRole;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole>{
	/**
	 * 
	* @Title: selectByUserId 
	* @Description: 根据UserId查询
	* @param @param UserId
	* @param @return     
	* @return SysUserRole    
	* @throws
	 */
	public List<SysUserRole> selectByUserId(Long UserId);
	/**
	 * 
	* @Title: selectAllByUserIds 
	* @Description: 根据UserId查询
	* @param @param list
	* @param @return     
	* @return List<SysUserRole>    
	* @throws
	 */
	public List<SysUserRole> selectAllByUserIds(List<Long> list);
	
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
	* @Title: deleteAllByRoleIds 
	* @Description: 根据RoleIds删除
	* @param @param list     
	* @return void    
	* @throws
	 */
	public void deleteAllByRoleIds(List<Long> list);
}
