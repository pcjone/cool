package com.cool.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.Constants;
import com.cool.api.SysDicService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.SysDicMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysDic;
import com.github.pagehelper.PageInfo;

import freemarker.template.TemplateException;
/**
 * 
* @ClassName: SysDicServiceImpl 
* @Description: 数据字典处理
* @author panlei
* @date 2017年7月26日 下午5:29:49 
*
 */
@Service
public class SysDicServiceImpl extends BaseServiceImpl<SysDic> implements SysDicService{

	@Autowired
	private SysDicMapper sysDicMapper;
	
	@Override
	public PageInfo<SysDic> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<SysDic> getMapper() {
		return sysDicMapper;
	}

	@Override
	public List<SysDic> queryListByCategory(Map<String, Object> params) {
		return sysDicMapper.queryListByCategory(params);
	}

	@Override
	public List<SysDic> validateSysDic(Map<String, Object> params) {
		return sysDicMapper.queryListByCategory(params);
	}
}
