package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysDept;
import com.cool.model.expand.SysDeptExpand;

public interface SysDeptService extends BaseService<SysDept>{
	
	public List<SysDeptExpand> queryListDeptTree(Map<String,Object> params);

}
