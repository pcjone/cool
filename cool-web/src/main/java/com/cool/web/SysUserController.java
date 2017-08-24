package com.cool.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.cool.Constants;
import com.cool.api.SysRoleService;
import com.cool.api.SysUserRoleService;
import com.cool.api.SysUserService;
import com.cool.base.BaseController;
import com.cool.common.Md5;
import com.cool.generator.ConstantsEnum;
import com.cool.model.SysRole;
import com.cool.model.SysUser;
import com.cool.model.expand.JsTree;
import com.cool.model.expand.State;
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
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	/**
	 * 
	* @Title: userIndex 
	* @Description: 首页跳转
	* @param @param request
	* @param @param response
	* @param @return     
	* @return Object    
	* @throws
	 */
	@RequiresPermissions("sys.user.list")
	@RequestMapping("/list")
	public Object userIndex(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", "用户管理");
		return forword("sys/user/user",context);
	}
	/**
	 * 
	* @Title: userList 
	* @Description: 分页查询
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("sys.user.dataList")
	@RequestMapping(value = "/dataList", method = RequestMethod.POST)
	public void userList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<SysUser> pageList = sysUserService.query(params);
		HtmlUtil.writerJson(response,pageList);
	}
	
	@RequestMapping(value="/user/{id}",method = RequestMethod.POST)
	public void queryById(@PathVariable Long id,HttpServletRequest request, HttpServletResponse response) {
		SysUser record = sysUserService.queryDBById(id);
		Map<String,Object> context = getRootMap();
		context.put("user", record);
		HtmlUtil.writerJson(response, context);
	}
	/*
	 * 更新个人资料
	 */
	@RequestMapping(value="/user/update",method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response) {
		SysUser record = Request2ModelUtil.covert(SysUser.class, request);
		sysUserService.updateDB(record);
		sendSuccessMessage(response,"更新成功");
	}
	
	/**
	 * 
	* @Title: cancel 
	* @Description: 逻辑删除
	* @param @param ids
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("sys.user.cancel")
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	public void cancel(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		sysUserService.cancelDBAndCache(ids, getCurrUser());
		sendSuccessMessage(response,"锁定成功");
	}
	
	/**
	 * 
	* @Title: detete 
	* @Description: 物理删除
	* @param @param ids
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("sys.user.delete")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public void detete(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		sysUserService.deleteDBAndCache(ids);
		sendSuccessMessage(response,"删除成功");
	}
	/**
	 * 
	* @Title: save 
	* @Description: 新增或修改
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		SysUser record = Request2ModelUtil.covert(SysUser.class, request);
		if(record != null) {
			if(record.getId() == null) {
				Map<String, Object> params = new HashMap<String,Object>();
				params.put("account", record.getAccount());
				SysUser checkUser = sysUserService.queryUserByName(params);
				if(checkUser != null) {
					sendFailureMessage(response,"账号已存在，请重新输入！");
					return;
				}
				try {
					record.setPassword(Md5.EncoderByMd5("123456"));
					record.setUserType(ConstantsEnum.UserType.SystemUser.key);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				record.setCreateBy(WebUtil.getCurrentUser());
				record = sysUserService.insert(record);
				sendSuccessMessage(response,"账户添加成功！");
			}else {
				record.setUpdateBy(getCurrUser());
				sysUserService.updateDB(record);
				sendSuccessMessage(response,"更新成功");
			}
		}
	}
	
	@RequestMapping(value="/roleTree",method = RequestMethod.POST)
	public void getRoleTree(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("enable", Constants.ENABLE_NO);
		List<SysRole> roles = sysRoleService.queryAll(params);
		JsTree jsTree = new JsTree();
		jsTree.setText("全部");
		jsTree.setId(-1l);
		State state;
		state = new State(true,false,false);		
		jsTree.setState(state);
		createJstree(jsTree,roles);
		HtmlUtil.writerJson(response,"["+JSON.toJSONString(jsTree)+"]");
	}
	/**
	 * 
	* @Title: queryRoleInfo 
	* @Description: 根据userId查询roleIds
	* @param @param userId
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("sys.user.rolePermission")
	@RequestMapping(value="/queryRoleInfo",method = RequestMethod.POST)
	public void queryRoleInfo(Long userId,HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("enable", Constants.ENABLE_NO);
		params.put("userId", userId);
		List<Long> roleIds = sysUserRoleService.queryRoleInfo(params);
		Map<String,Object> contentMap = getRootMap();
		contentMap.put("roleIds", roleIds);
		HtmlUtil.writeGson(response, contentMap);
	}
	
	@RequestMapping(value = "/updateRoleUser", method = RequestMethod.POST)
	public void updateRoleUser(Long[] roleIds,Long userId, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("enable", Constants.ENABLE_NO);
		params.put("userId", userId);
		List<Long> oldRoleIds = sysUserRoleService.queryRoleInfo(params);
		List<Long> addRoleIds = new ArrayList<Long>();
		if(roleIds!=null && roleIds.length>0) {
			for(Long id : roleIds) {
				if(oldRoleIds.contains(id)) {
					//过滤存在的权限，找到需要删除
					oldRoleIds.remove(id);
				}else {
					//添加新角色id
					addRoleIds.add(id);
				}
			}
		}
		sysUserService.addOrDeleteSysRoleUser(addRoleIds,oldRoleIds,userId,WebUtil.getCurrentUser());
		sendSuccessMessage(response,"权限修改成功");
	}
	
	/**
	 * 
	* @Title: createJstree 
	* @Description: 创建角色树
	* @param @param jsTree
	* @param @param sysRoles     
	* @return void    
	* @throws
	 */
	private void createJstree(JsTree jsTree,List<SysRole> sysRoles) {
		List<JsTree> jsTreeList = new ArrayList<JsTree>();
		for(SysRole sysRole: sysRoles) {
			JsTree tree = new JsTree();
			tree.setText(sysRole.getRoleName());
			tree.setId(sysRole.getId());
			State state;
			state = new State(false,false,false);
			tree.setState(state);
			jsTreeList.add(tree);
		}
		jsTree.setChildren(jsTreeList);
	}
	@RequestMapping(value="/editPassword", method = RequestMethod.POST)
	public void editPassword(String account,String oldPassword,String newPassword,HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("account", account);
		SysUser checkUser = sysUserService.queryUserByName(params);
		if(checkUser != null) {
			if(Md5.checkpassword(oldPassword,checkUser.getPassword())) {
				checkUser.setPassword(Md5.EncoderByMd5(newPassword));
				sysUserService.updatePassword(checkUser);
			}else {
				sendFailureMessage(response,"密码错误！");
			}
		}else {
			sendFailureMessage(response,"账号异常！");
		}
	}
}
