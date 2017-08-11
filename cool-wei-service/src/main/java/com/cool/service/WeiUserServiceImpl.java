package com.cool.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cool.api.WeiUserService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.WeiUserMapper;
import com.cool.exception.ServiceException;
import com.cool.model.WeiUser;
import com.github.pagehelper.PageInfo;

@Service
public class WeiUserServiceImpl extends BaseServiceImpl<WeiUser> implements WeiUserService{

	@Autowired
	private WeiUserMapper weiUserMapper;
	
	@Override
	public PageInfo<WeiUser> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return null;
	}

	@Override
	protected BaseMapper<WeiUser> getMapper() {
		return weiUserMapper;
	}

	@Override
	public WeiUser queryByOpenId(String openId) {
		return weiUserMapper.queryByOpenId(openId);
	}
	
	@Transactional
	@Override
	public WeiUser insert(WeiUser weiUser) {
		getMapper().insert(weiUser);
		return weiUser;
	}
	
	@Transactional
	@Override
	public WeiUser updateDB(WeiUser weiUser) {
		try {
			if (weiUser.getId() == null) {
				getMapper().insert(weiUser);
			} else {
				getMapper().updateByPrimaryKey(weiUser);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return weiUser;
	}

}
