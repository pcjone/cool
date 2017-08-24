package com.cool.tags;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.Constants;
import com.cool.api.SysDicService;
import com.cool.freemarker.SuperBaseTag;
/**
 * 
* @ClassName: SysDicTag 
* @Description: 获取字典值标签
* @author panlei
* @date 2017年8月21日 下午5:19:09 
*
 */
@Service
public class SysDicTag extends SuperBaseTag {

	@Autowired
	private SysDicService sysDicService;

	@Override
	public Object result(Map params) {
		String excludeStr = null;
		Map<String, Object> searchParams = new HashMap<String, Object>();
		excludeStr = getString(params, "value");
		searchParams.put("category", excludeStr);
		searchParams.put("enable", Constants.ENABLE_NO);
		return sysDicService.queryListByCategory(searchParams);
	}

}
