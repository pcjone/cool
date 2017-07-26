package com.cool.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cool.base.BaseController;

@Controller
@RequestMapping("role")
public class SysRoleController extends BaseController{
	
	private final Logger logger = Logger.getLogger(SysRoleController.class);
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public Object roleIndex() {
		return forword("sys/role/role",null);
	}

}
