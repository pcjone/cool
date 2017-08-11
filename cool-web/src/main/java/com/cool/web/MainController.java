package com.cool.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cool.Constants;
import com.cool.api.SysMenuService;
import com.cool.api.SysUserService;
import com.cool.base.BaseController;
import com.cool.model.SysUser;
import com.cool.model.expand.MenuTree;
import com.cool.model.expand.SysMenuExpand;
import com.cool.model.SysMenu;
import com.cool.session.UserSession;
import com.cool.util.HtmlUtil;
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
		String html="";
		createUI(html,tree);
		context.put("menuHtml", html);
		return forword("main",context);
	}
	
	@RequestMapping("main/tree")
	public void mainTree(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		UserSession userSession = WebUtil.getCurrentUserSession();
		Map<String,Object> params = new HashMap<String,Object>();
		Long userId = userSession.getId();
		params.put("userId", userId);
		params.put("enable", Constants.ENABLE_NO);
		//!=0 菜单权限
		params.put("menuType", 0);
		List<SysMenuExpand> tree = sysMenuService.queryMenuListByUserId(params);
		List<MenuTree> nodes = new ArrayList<MenuTree>();		
		MenuTree menuTree = new MenuTree();
		menuTree.setId("1");
		menuTree.setText("我的工作台");
		menuTree.setIsHeader(true);
		nodes.add(menuTree);
		for(SysMenuExpand menu :  tree) {
			MenuTree child = new MenuTree();
			child.setId(menu.getId().toString());
			child.setText(menu.getMenuName());
			child.setIcon(menu.getIconcls());
			child.setIsOpen(false);
			if(menu.isHasChild()) {
				createMenuTree(child,menu.getChildSysMenu());
			}else {
				child.setTargetType("iframe-tab");
				child.setUrl(menu.getRequest());
			}
			nodes.add(child);
		}
		SysUser user = sysUserService.queryDBById(userId);
		context.put("user", user);
		context.put("menus", nodes);
		HtmlUtil.writerJson(response, context);
	}
	
	private void createMenuTree(MenuTree menuTree,List<SysMenuExpand> trees) {
		List<MenuTree> nodes = new ArrayList<MenuTree>();
		for(SysMenuExpand tree :  trees) {
			MenuTree child = new MenuTree();
			child.setId(tree.getId().toString());
			child.setText(tree.getMenuName());
			child.setIcon(tree.getIconcls());
			child.setIsOpen(false);
			if(tree.isHasChild()) {
				createMenuTree(child,tree.getChildSysMenu());
			}else {
				child.setTargetType("iframe-tab");
				child.setUrl(tree.getRequest());
			}
			nodes.add(child);
		}
		menuTree.setChildren(nodes);
	}
	
	private void createUI(String html,List<SysMenuExpand> trees) {
		for(SysMenuExpand menu :  trees) {
			html+="<li><a ";
			if(!menu.isHasChild()) {
				html+="class=\"J_menuItem\"";
			}
			html+=" href='"+menu.getRequest()+"'><i class='"+menu.getIconcls()+"'></i><span class=\"nav-label\">"+menu.getMenuName()+"</span>";
			if(!menu.isHasChild()) {
				html+="<span class=\\\"fa arrow\\\"></span>";
			}
			html+="</a>";
			if(menu.isHasChild()) {
				html+="<ul class=\"nav nav-second-level\">";
				createUI(html,menu.getChildSysMenu());
				html+="</ul>";
			}
			html+="</li>";
		}
	}
}
