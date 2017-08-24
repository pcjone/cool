package com.cool.tags;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.Constants;
import com.cool.api.TaskGroupService;
import com.cool.freemarker.SuperBaseTag;
/**
 * 
* @ClassName: TaskGroupTag 
* @Description: 分组任务标签
* @author panlei
* @date 2017年8月21日 下午5:17:41 
*
 */
@Service
public class TaskGroupTag extends SuperBaseTag{
	@Autowired
	private TaskGroupService taskGroupService;
	@Override
	protected Object result(Map params) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("enable", Constants.ENABLE_NO);
		return taskGroupService.queryAllGroup(searchParams);
	}

}
