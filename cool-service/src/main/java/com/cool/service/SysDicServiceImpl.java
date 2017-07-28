package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.SysDicService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.SysDicMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysDic;
import com.github.pagehelper.PageInfo;
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
		return getPageByDB(sysDicMapper.queryForList(params));
	}

	@Override
	protected BaseMapper<SysDic> getMapper() {
		return sysDicMapper;
	}

}
