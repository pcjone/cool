package com.cool.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import com.cool.common.Md5;
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
	@RequestMapping(value = "/dataList", method = RequestMethod.POST)
	public void userList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<SysUser> pageList = sysUserService.query(params);
		HtmlUtil.writerJson(response,pageList);
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
	
	@RequestMapping(value="roleTree",method = RequestMethod.POST)
	public void getRoleTree(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
}
