package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.model.SysDic;

public interface TagRpcService {
	List<SysDic> queryListByCategory(Map<String,Object> params);
}
