package com.cool.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cool.api.SysUserService;
import com.cool.base.BaseController;
import com.cool.model.SysUser;
import com.cool.util.HtmlUtil;
import com.cool.util.Request2ModelUtil;
import com.cool.util.WebUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("user")
public class SysUserController extends BaseController{
	private final static Logger logger = Logger.getLogger(SysUserController.class);
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/index")
	public Object userIndex(HttpServletRequest request, HttpServletResponse response) {
		return forword("sys/user/user",null);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void userList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		params.put("enable", 1);
		PageInfo<SysUser> pageList = sysUserService.query(params);
		HtmlUtil.writerJson(response,pageList);
	}

	@RequestMapping("list/{id}")
	public void findUser(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		SysUser user = sysUserService.queryDBById(id);
		HtmlUtil.writerJson(response, user);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request, HttpServletResponse response,String confirm_password) {
		SysUser user = Request2ModelUtil.covert(SysUser.class, request);		
		if(!user.getPassword().equals(confirm_password)) {
			sendFailureMessage(response,"两次密码不一致！");
			return;
		}
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("account", user.getAccount());
		SysUser checkUser = sysUserService.queryUserByName(params);
		if(checkUser != null) {
			sendFailureMessage(response,"账号已存在，请重新输入！");
			return;
		}
		user.setEnable(1);
		user.setCreateTime(new Date());
		user.setCreateBy(WebUtil.getCurrentUser());
		user = sysUserService.insert(user);
		sendSuccessMessage(response,"添加成功！");
	}
}
