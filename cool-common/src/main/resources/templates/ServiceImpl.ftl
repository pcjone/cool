package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.${table_name}Service;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.${table_name}Mapper;
import com.cool.exception.ServiceException;
import com.cool.model.${table_name};
import com.github.pagehelper.PageInfo;
@Service
public class ${table_name}ServiceImpl extends BaseServiceImpl<${table_name}> implements ${table_name}Service{
	
	@Autowired
	private ${table_name}Mapper ${table_name}Mapper;

	@Override
	public PageInfo<${table_name}> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<ExecLog> getMapper() {
		return ${table_name}Mapper;
	}

}
