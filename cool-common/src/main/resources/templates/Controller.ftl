package com.cool.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cool.api.SysDicService;
import com.cool.base.BaseController;
/**
 * 
* @ClassName: SysDicController 
* @Description: 数据字典控制器
* @author panlei
* @date 2017年7月26日 下午5:41:17 
*
 */
@Controller
@RequestMapping("${scr}")
public class ${table_name} extends BaseController{
	private final Logger logger = Logger.getLogger(BaseController.class);
	@Autowired
	private SysDicService sysDicService;
	
	@RequestMapping(value="list",method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", "${table_annotation}");
		return forword("sys/${scr}/${scr}",context);
	}
	
	@RequestMapping(value="dataList",method = RequestMethod.POST)
	public void dataList(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
