package com.cool.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.SysDicService;
import com.cool.api.TagRpcService;
import com.cool.model.SysDic;

@Service
public class TagRpcServiceImpl implements TagRpcService {
	
	@Autowired
	private SysDicService sysDicService;

	@Override
	public List<SysDic> queryListByCategory(Map<String, Object> params) {
		return sysDicService.queryListByCategory(params);
	}

}
