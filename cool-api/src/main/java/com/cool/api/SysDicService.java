package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.SysDic;

public interface SysDicService extends BaseService<SysDic>{

	
	/*-----RPC接口-----*/
	List<SysDic> queryListByCategory(Map<String,Object> params);
}
