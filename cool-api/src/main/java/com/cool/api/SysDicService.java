package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysDic;

public interface SysDicService extends BaseService<SysDic>{

	public List<SysDic> queryListByCategory(Map<String,Object> params);

	public List<SysDic> validateSysDic(Map<String,Object> params);
}
