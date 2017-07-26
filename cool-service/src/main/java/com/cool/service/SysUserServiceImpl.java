package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.SysUserService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.SysUserMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
/**
 * 
* @ClassName: SysUserServiceImpl 
* @Description: 用户管理
* @author panlei
* @date 2017年7月18日 下午2:01:40 
*
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	protected BaseMapper<SysUser> getMapper() {
		return sysUserMapper;
	}

	@Override
	public PageInfo<SysUser> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(sysUserMapper.queryForList(params));
	}

	@Override
	public SysUser queryUserByName(Map<String, Object> params) {
		return sysUserMapper.queryUserByName(params);
	}

}
