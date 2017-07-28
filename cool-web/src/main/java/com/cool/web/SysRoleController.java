package com.cool.web;

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

import com.cool.api.SysRoleService;
import com.cool.base.BaseController;
import com.cool.model.SysDic;
import com.cool.model.SysRole;
import com.cool.util.HtmlUtil;
import com.cool.util.Request2ModelUtil;
import com.cool.util.WebUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("role")
public class SysRoleController extends BaseController{
	
	private final Logger logger = Logger.getLogger(SysRoleController.class);
	
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 
	* @Title: roleIndex 
	* @Description: 跳转到list页面
	* @param @return     
	* @return Object    
	* @throws
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public Object listGet(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", "角色管理");
		return forword("sys/role/role",context);
	}
	
	/**
	 * 
	* @Title: list 
	* @Description: 分页查询
	* @param @param request
	* @param @param response    
	* @return void    
	* @throws
	 */
	@RequestMapping(value="/dataList",method = RequestMethod.POST)
	public void listPost(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<SysRole> pageList = sysRoleService.query(params);
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
		sysRoleService.cancelDBAndCache(ids, getCurrUser());
		sendSuccessMessage(response,"删除成功");
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
		sysRoleService.deleteDBAndCache(ids);
		sendSuccessMessage(response,"删除成功");
	}
	
	/**
	 * 
	* @Title: queryById 
	* @Description: 根据id查询
	* @param @param id
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequestMapping(value="/queryById",method = RequestMethod.POST)
	public void queryById(Long id,HttpServletRequest request, HttpServletResponse response) {
		SysRole record = sysRoleService.queryCacheById(id);
		HtmlUtil.writerJson(response,record);
	}
	
	/**
	 * 
	* @Title: save 
	* @Description: 新增或更新
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		SysRole record = Request2ModelUtil.covert(SysRole.class,request);
		if(record != null) {
			if(record.getId() == null) {
				record.setCreateBy(getCurrUser());
				sysRoleService.insert(record);
				sendSuccessMessage(response,"新增成功");
			}else {
				record.setUpdateBy(getCurrUser());
				sysRoleService.updateDB(record);
				sendSuccessMessage(response,"更新成功");
			}
		}
	}
	
	/**
	 * 
	* @Title: validateName 
	* @Description: 验证角色名称是否存在
	* @param @param roleName
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "/validateName", method = RequestMethod.POST)
	public void validateName(String roleName, HttpServletRequest request,
			HttpServletResponse response) {
		sysRoleService.validateName(roleName);
		sendSuccessMessage(response,"");
	}

}
