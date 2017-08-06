package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysUserRole;

public interface SysUserRoleService extends BaseService<SysUserRole>{
	public List<Long> queryRoleInfo(Map<String,Object> params);
}
