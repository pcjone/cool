package com.cool.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cool.Constants;
import com.cool.api.SysMenuService;
import com.cool.api.SysUserService;
import com.cool.base.BaseController;
import com.cool.model.SysUser;
import com.cool.model.expand.SysMenuExpand;
import com.cool.model.SysMenu;
import com.cool.session.UserSession;
import com.cool.util.WebUtil;

/**
 * 
* @ClassName: MainController 
* @Description: 控制器
* @author panlei
* @date 2017年7月20日 上午11:36:42 
*
 */
@Controller
public class MainController extends BaseController{
	
	private final Logger logger = Logger.getLogger(MainController.class);
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("main")
	public Object index(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		UserSession userSession = WebUtil.getCurrentUserSession();
		Map<String,Object> params = new HashMap<String,Object>();
		Long userId = userSession.getId();
		params.put("userId", userId);
		params.put("enable", Constants.ENABLE_NO);
		//!=0 菜单权限
		params.put("menuType", 0);
		List<SysMenuExpand> tree = sysMenuService.queryMenuListByUserId(params);
		context.put("menuTree", tree);
		SysUser user = sysUserService.queryDBById(userId);
		context.put("user", user);
		return forword("main",context);
	}
}
