package com.cool.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cool.exception.ServiceException;
import com.github.pagehelper.PageInfo;

public interface BaseService <T extends BaseModel>{
	
	@Transactional
	public T insert(T record) throws ServiceException;
	
	@Transactional
	public int insertBatch(List<T> list) throws ServiceException;

	@Transactional
	public T updateDBAndCache(T record) throws ServiceException;

	@Transactional
	public T updateDB(T record) throws ServiceException;

	@Transactional
	public void deleteDBAndCache(Long[] ids, String account) throws ServiceException;

	@Transactional
	public void deleteDB(Long[] ids, String account) throws ServiceException;

	public T queryDBById(Long id) throws ServiceException;

	public T queryCacheById(Long id) throws ServiceException;

	public PageInfo<T> query(Map<String, Object> params) throws ServiceException;

	@Transactional
	public T updateOnly(T record) throws ServiceException;
}
