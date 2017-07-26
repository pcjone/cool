package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysMenu;
import com.cool.model.expand.SysMenuTree;

public interface SysMenuService extends BaseService<SysMenu>{
	public List<SysMenu> queryMenuListByUserId(Long userId);
	
	public List<SysMenuTree> querySysMenuTree(Map<String,Object> params);
	/**
	 * 
	* @Title: querySysMenuByUserId 
	* @Description: 查询用户权限
	* @param @param userId
	* @param @return
	* @return List<SysMenu>
	* @throws
	 */
	public List<SysMenu> querySysMenuByUserId(Long userId);
}
