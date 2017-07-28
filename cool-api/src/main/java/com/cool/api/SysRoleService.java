package com.cool.api;

import com.cool.base.BaseService;
import com.cool.model.SysRole;

public interface SysRoleService extends BaseService<SysRole>{
	public void validateName(String roleName);
}
