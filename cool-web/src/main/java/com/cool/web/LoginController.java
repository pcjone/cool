package com.cool.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cool.Constants;
import com.cool.base.BaseController;
import com.cool.common.Md5;
import com.cool.util.CacheUtil;
import com.cool.util.WebUtil;
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
	
	@RequestMapping(value = "/login",method = {RequestMethod.GET})
	public Object index(HttpServletRequest request) {
		Map<String,Object> context = getRootMap();
		return forword("login",context);
	}
	
	@RequestMapping(value = "/login", method= {RequestMethod.POST})
	public void login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "account", required = true) String account,
			@RequestParam(value = "password", required = true) String password) throws NoSuchAlgorithmException, IOException{		  
		UsernamePasswordToken token = new UsernamePasswordToken(account, Md5.EncoderByMd5(password));
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if(subject.isAuthenticated()) {
				sendSuccessMessage(response,Constants.SUCCESS,account);	
			}
		} catch (LockedAccountException e) {
			sendFailureMessage(response,Constants.USER_ENABLED);	
		} catch (DisabledAccountException e) {
			sendFailureMessage(response,Constants.USER_ENABLED);	
		} catch (ExpiredCredentialsException e) {
			sendFailureMessage(response,Constants.FAILURE);	
		} catch (UnknownAccountException e) {
			sendFailureMessage(response,Constants.USER_WRONG);	
		} catch(IncorrectCredentialsException e) {
			sendFailureMessage(response,Constants.USER_WRONG);	
		}finally{
			sendFailureMessage(response,Constants.FAILURE);	
		}
	}
	
	@RequestMapping("/logout")
	public Object logout(HttpServletRequest request, ModelMap modelMap) {
		CacheUtil.getRedisHelper().del(WebUtil.getCurrentUser());
		SecurityUtils.getSubject().logout();
		Map<String,Object> context = getRootMap();
		return forword("login",context);
	}

	// 没有登录
	@RequestMapping("/unauthorized")
	public Object unauthorized(ModelMap modelMap) {
		Map<String,Object> context = getRootMap();
		return forword("login",context);
	}

	// 没有权限
	@RequestMapping("/forbidden")
	public Object forbidden(ModelMap modelMap) {
		Map<String,Object> context = getRootMap();
		return forword("forbidden",context);
	}
}
