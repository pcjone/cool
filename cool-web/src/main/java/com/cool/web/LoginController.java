package com.cool.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cool.base.BaseController;
import com.cool.common.HttpCode;
import com.cool.common.Md5;
import com.cool.constants.LoginConstants;
import com.cool.util.RedisUtil;
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
	
	@RequestMapping(value = "login",method = {RequestMethod.GET})
	public Object index(HttpServletRequest request) {
		Map<String,Object> context = getRootMap();
		return forword("login",context);
	}
	
	@RequestMapping(value = "login", method= {RequestMethod.POST})
	public void login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) throws AuthenticationException, NoSuchAlgorithmException, IOException {
		try {  
	        Subject subject= SecurityUtils.getSubject();  
	        if (subject.isAuthenticated()) {  
	            return;  
	        }                
	        boolean rememberMe = ServletRequestUtils.getBooleanParameter(request, "rememberMe", false);  
	        UsernamePasswordToken token = new UsernamePasswordToken(account, Md5.EncoderByMd5(password), rememberMe);  
	        subject.login(token); // 登录  
	        sendSuccessMessage(response,LoginConstants.SUCCESS,account);	
	    } catch (Exception e) {  
	    		sendFailureMessage(response,LoginConstants.USER_NOT_EXIST);
	        //做一些异常处理  
	    }finally{  
	    		sendFailureMessage(response,LoginConstants.USER_NOT_EXIST);
	    }  
	}
	
	@RequestMapping("/logout")
	public Object logout(HttpServletRequest request, ModelMap modelMap) {
		RedisUtil.del(WebUtil.getCurrentUser());
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
