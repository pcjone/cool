package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysParam;

public interface SysParamService extends BaseService<SysParam>{
	public List<SysParam> validateSysParam(Map<String,Object> params);
}
