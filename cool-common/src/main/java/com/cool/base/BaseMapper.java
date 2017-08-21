package com.cool.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * @author ShenHuaJie
 * @version 2016年6月3日 下午2:30:14
 */
public interface BaseMapper<T extends BaseModel> {
	
	/** 条件分页查询 */
	Page<Long> query(Map<String, Object> params);

	/** 实时条件分页查询 */
	Page<T> queryForList(Map<String, Object> params);

	void deleteByPrimaryKey(@Param(value = "id") Long id);

	T selectByPrimaryKey(@Param(value = "id") Long id);

	int insert(T record);
	
	int insertBatch(List<T> list);

	int updateByPrimaryKey(T record);

	void updateAllByPrimaryKey(Map<String, Object> paramMap);
	
	void deleteAllByPrimaryKey(List<Long> list);
}
