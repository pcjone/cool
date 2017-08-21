package com.cool.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.cool.Constants;
import com.cool.tags.BaseTemplateDirectiveModel;
import com.cool.util.DataUtil;
import com.cool.util.DateUtil;
import com.cool.util.InstanceUtil;
import com.cool.util.RedisUtil;
import com.cool.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 业务逻辑层基类<br/>
 * 继承基类后必须配置CacheConfig(cacheNames="")
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public abstract class BaseServiceImpl<T extends BaseModel>{
	protected Logger logger = LogManager.getLogger(this.getClass());

	/** 启动分页查询 */
	protected void startPage(Map<String, Object> params) {
		if (DataUtil.isEmpty(params.get("pageNum"))) {
			params.put("pageNum", 1);
		}
		if (DataUtil.isEmpty(params.get("pageSize"))) {
			params.put("pageSize", 10);
		}
		if (DataUtil.isNotEmpty(params.get("sort"))) {
			params.put(
					"orderBy",
					StringUtil.getOrderCondition(String.valueOf(params.get("sort")),
							String.valueOf(params.get("order"))));
		}
		PageHelper.startPage(params);
	}

	@SuppressWarnings("unchecked")
	private BaseServiceImpl<T> getService() {
		return ContextLoader.getCurrentWebApplicationContext().getBean(getClass());
	}

	/**
	 * 根据Id查询，缓存查询(默认类型T)
	 * <p>1、查询分页ID集合</p>
	 * <p>2、根据ID集合进行遍历，针对每个ID进行redis缓存查询</p>
	 * <p>3、如果缓存无数据，则查询DB并加入缓存</p>
	 * @param ids ID集合
	 * @return
	 */
	public PageInfo<T> getPageByCache(Page<Long> ids) {
		Page<T> page = new Page<T>(ids.getPageNum(), ids.getPageSize());
		page.setTotal(ids.getTotal());
		if (ids != null) {
			page.clear();
			BaseServiceImpl<T> baseService = getService();
			for (Long id : ids) {
				page.add(baseService.queryCacheById(id));
			}
		}
		return new PageInfo<T>(page);
	}

	/**
	 * 分页查询(实时查询不取缓存)
	 * @param datas
	 * @return
	 */
	public PageInfo<T> getPageByDB(Page<T> datas) {
		Page<T> page = new Page<T>(datas.getPageNum(), datas.getPageSize());
		page.setTotal(datas.getTotal());
		if (datas != null) {
			page.addAll(datas);
		}
		return new PageInfo<T>(page);
	}

	/**
	 * 分页查询(实时查询不取缓存)
	 * <p>返回扩展Bean调用</p>
	 * @param datas
	 * @return
	 */
	public <K> PageInfo<K> getPageByDB(Page<K> datas, Class<K> cls) {
		Page<K> page = new Page<K>(datas.getPageNum(), datas.getPageSize());
		page.setTotal(datas.getTotal());
		if (datas != null) {
			page.addAll(datas);
		}
		return new PageInfo<K>(page);
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> PageInfo<K> getPageByCache(Page<Long> ids, Class<K> cls) {
		Page<K> page = new Page<K>(ids.getPageNum(), ids.getPageSize());
		page.setTotal(ids.getTotal());
		if (ids != null) {
			page.clear();
			BaseServiceImpl<T> baseService = getService();
			for (Long id : ids) {
				T t = baseService.queryCacheById(id);
				K k = null;
				try {
					k = cls.newInstance();
				} catch (Exception e1) {
				}
				if (k != null) {
					try {
						PropertyUtils.copyProperties(k, t);
					} catch (Exception e) {
					}
					page.add(k);
				}
			}
		}
		return new PageInfo<K>(page);
	}

	/** 根据Id查询(默认类型T) */
	public List<T> getList(List<Long> ids) {
		List<T> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (Long id : ids) {
				list.add(getService().queryCacheById(id));
			}
		}
		return list;
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> List<K> getList(List<Long> ids, Class<K> cls) {
		List<K> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (Long id : ids) {
				T t = getService().queryCacheById(id);
				K k = null;
				try {
					k = cls.newInstance();
				} catch (Exception e1) {
				}
				if (k != null) {
					try {
						PropertyUtils.copyProperties(k, t);
					} catch (Exception e) {
					}
					list.add(k);
				}
			}
		}
		return list;
	}

	/**
	 * 数据删除，并删除redis缓存中的数据
	 * @param ids ID集合
	 */
	@Transactional
	public void deleteDBAndCache(Long[] ids) {
		try {
			List<Long> list = new ArrayList<Long>();
			for (Long id : ids) {
				list.add(id);
				RedisUtil.del(getCacheKey(id));
			}
			getMapper().deleteAllByPrimaryKey(list);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 数据库数据删除,无需刷新缓存
	 * @param ids ID集合
	 */
	@Transactional
	public void deleteDB(Long[] ids) {
		try {
			List<Long> list = new ArrayList<Long>();
			for(Long id : ids) {
				list.add(id);
			}
			getMapper().deleteAllByPrimaryKey(list);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * 数据删除，并删除redis缓存中的数据
	 * @param ids ID集合
	 * @param account 用户
	 */
	@Transactional
	public void cancelDBAndCache(Long[] ids, String account) {
		try {
			for (Long id : ids) {
				T record = queryCacheById(id);
				record.setEnable(Constants.ENABLE_YES);
				record.setUpdateTime(new Date());
				record.setUpdateBy(account);
				getMapper().updateByPrimaryKey(record);
				RedisUtil.set(getCacheKey(id), record);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 数据库数据删除,无需刷新缓存
	 * @param ids ID集合
	 * @param account 用户
	 */
	@Transactional
	public void cancelDB(Long[] ids, String account) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ids", ids);
			paramMap.put("enable", Constants.ENABLE_YES);
			paramMap.put("updateTime", DateUtil.getCurrentDate());
			paramMap.put("updateBy", account);
			getMapper().updateAllByPrimaryKey(paramMap);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 数据库数据更新,无需刷新缓存
	 * @param record
	 * @return
	 */
	@Transactional
	public T updateDB(T record) {
		try {
			record.setUpdateTime(new Date());
			if (record.getId() == null) {
				record.setCreateTime(new Date());
				getMapper().insert(record);
			} else {
				getMapper().updateByPrimaryKey(record);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return record;
	}

	/**
	 * 数据更新，并删除redis缓存中的数据
	 * @param record
	 * @return
	 */
	@Transactional
	public T updateDBAndCache(T record) {
		try {
			record.setUpdateTime(new Date());
			if (record.getId() == null) {
				record.setCreateTime(new Date());
				getMapper().insert(record);
			} else {
				getMapper().updateByPrimaryKey(record);
			}
			RedisUtil.del(getCacheKey(record.getId()));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return record;
	}

	@Transactional
	public T insert(T record) {
		record.setEnable(Constants.ENABLE_NO);
		record.setCreateTime(new Date());
		getMapper().insert(record);
		return record;
	}
	
	@Transactional
	public int insertBatch(List<T> list) {
		for(T record : list) {
			record.setEnable(Constants.ENABLE_NO);
			record.setCreateTime(new Date());
		}
		return getMapper().insertBatch(list);
	}

	/**
	 * 获取redis缓存数据
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public T queryCacheById(Long id) {
		try {
			String key = getCacheKey(id);
			T record = (T) RedisUtil.get(key);
			if (record == null) {
				record = getMapper().selectByPrimaryKey(id);
				RedisUtil.set(key, record);
			}
			return record;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 直接查询DB获取数据
	 * @param id
	 * @return
	 */
	@Transactional
	public T queryDBById(Long id) {
		return getMapper().selectByPrimaryKey(id);
	}

	/** 获取缓存键值 */
	private String getCacheKey(Object id) {
		String cacheName = null;
		CacheConfig cacheConfig = getClass().getAnnotation(CacheConfig.class);
		if (cacheConfig == null || cacheConfig.cacheNames() == null
				|| cacheConfig.cacheNames().length < 1) {
			cacheName = getClass().getName();
		} else {
			cacheName = cacheConfig.cacheNames()[0];
		}
		return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":")
				.append(id).toString();
	}

	protected abstract BaseMapper<T> getMapper();

	/**
	 * 只做更新操作  不插入
	 */
	@Transactional
	public T updateOnly(T record) {
		try {
			record.setUpdateTime(new Date());
			getMapper().updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return record;
	}
}
