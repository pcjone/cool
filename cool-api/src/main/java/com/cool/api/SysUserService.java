package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysUser;

public interface SysUserService extends BaseService<SysUser>{
	
	public int updatePassword(SysUser record);
	
	public SysUser queryUserByName(Map<String, Object> params);
	
	public void addOrDeleteSysRoleUser(List<Long> addRoleIds,List<Long> deleteRoleIds,Long userId,String curUser);
}
