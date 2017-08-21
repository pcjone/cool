package com.cool.dao;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseMapper;
import com.cool.model.SysParam;

public interface SysParamMapper extends BaseMapper<SysParam>{
	public List<SysParam> querySysParam(Map<String, Object> params);
}
