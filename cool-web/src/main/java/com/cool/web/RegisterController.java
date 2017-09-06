package com.cool.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cool.Constants;
import com.cool.api.SysUserService;
import com.cool.base.BaseController;
import com.cool.common.Md5;
import com.cool.generator.ConstantsEnum;
import com.cool.model.SysUser;
/**
 * 
* @ClassName: RegisterController 
* @Description: 注册用户
* @author panlei
* @date 2017年8月22日 下午3:43:31 
*
 */
@Controller
public class RegisterController extends BaseController {
	private final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "/register", method = { RequestMethod.GET })
	public Object index(HttpServletRequest request) {
		Map<String, Object> context = getRootMap();
		return forword("register", context);
	}

	@RequestMapping(value = "/register", method = { RequestMethod.POST })
	public void register(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", account);
		SysUser checkUser = sysUserService.queryUserByName(params);
		if (checkUser != null) {
			sendFailureMessage(response, "账号已存在，请重新输入！");
		}else {			
			checkUser = new SysUser();
			checkUser.setAccount(account);
			checkUser.setUserType(ConstantsEnum.UserType.RegisterUser.key);
			try {
				checkUser.setPassword(Md5.EncoderByMd5(password));
				checkUser.setCreateBy(account);
				checkUser.setSex(0);
				checkUser.setEnable(Constants.ENABLE_NO);
				checkUser = sysUserService.insert(checkUser);
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
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
