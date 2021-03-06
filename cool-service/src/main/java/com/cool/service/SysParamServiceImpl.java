package com.cool.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.SysParamService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.SysParamMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysParam;
import com.github.pagehelper.PageInfo;
/**
 * 
* @ClassName: SysParamServiceImpl 
* @Description: 参数服务
* @author panlei
* @date 2017年7月27日 下午8:46:59 
*
 */
@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParam> implements SysParamService{

	@Autowired
	private SysParamMapper sysParamMapper;
	
	@Override
	public PageInfo<SysParam> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<SysParam> getMapper() {
		return sysParamMapper;
	}

	@Override
	public List<SysParam> validateSysParam(Map<String, Object> params) {
		return sysParamMapper.querySysParam(params);
	}

	@Override
	public SysParam queryByKey(String paramKey) {
		return sysParamMapper.queryByKey(paramKey);
	}

}
