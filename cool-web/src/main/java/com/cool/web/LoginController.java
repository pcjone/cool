package com.cool.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cool.api.SysUserService;
import com.cool.base.BaseController;
import com.cool.common.HttpCode;
import com.cool.constants.LoginConstants;
import com.cool.model.SysUser;
/**
 * 
* @ClassName: LoginController 
* @Description: 登录控制器
* @author panlei
* @date 2017年7月19日 下午5:24:57 
*
 */
@RestController
public class LoginController extends BaseController {
	private final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value = "login",method = {RequestMethod.GET})
	public Object index(HttpServletRequest request) {
		Map<String,Object> context = getRootMap();
		return forword("login",context);
	}
	
	@RequestMapping(value = "login", method= {RequestMethod.POST})
	public void login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) {
		//SysUser sysUser = Request2ModelUtil.covert(SysUser.class, request);
		if(!StringUtils.isNotBlank(account)) {
			sendFailureMessage(response,LoginConstants.ACCOUNT_IS_NULL);
			return;
		}
		if(!StringUtils.isNotBlank(password)) {
			sendFailureMessage(response,LoginConstants.PASSWORD_IS_NULL);
			return;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("account", account);
		SysUser user = sysUserService.queryUserByName(params);
		if(user != null) {
			if(user.getPassword().equals(password) && user.getEnable() == 1) {
				Subject subject = SecurityUtils.getSubject();
				UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(account, password);
				subject.login(usernamePasswordToken);
				sendSuccessMessage(response,LoginConstants.SUCCESS,user);			
			}else {
				if(user.getPassword().equals(password)) {
					sendFailureMessage(response,LoginConstants.USER_ENABLED);	
				}else {
					sendFailureMessage(response,LoginConstants.USER_WRONG);	
				}			
			}
			return;
		}
		sendFailureMessage(response,LoginConstants.USER_NOT_EXIST);
	}
	
	@RequestMapping("/logout")
	public Object logout(HttpServletRequest request, ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		Map<String,Object> context = getRootMap();
		return forword("login",context);
	}

	// 没有登录
	@RequestMapping("/unauthorized")
	public Object unauthorized(ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		return forword("login",null);
	}

	// 没有权限
	@RequestMapping("/forbidden")
	public Object forbidden(ModelMap modelMap) {
		return setModelMap(modelMap, HttpCode.FORBIDDEN);
	}
}
