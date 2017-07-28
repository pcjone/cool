package com.cool.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cool.api.SysMenuService;
import com.cool.base.BaseController;
import com.cool.model.SysMenu;
import com.cool.util.HtmlUtil;
import com.cool.util.Request2ModelUtil;
import com.cool.util.WebUtil;
import com.github.pagehelper.PageInfo;
/**
 * 
* @ClassName: SysMenuController 
* @Description: 菜单控制器
* @author panlei
* @date 2017年7月27日 下午10:54:02 
*
 */
@Controller
@RequestMapping("menu")
public class SysMenuController extends BaseController{
	private final Logger logger = Logger.getLogger(BaseController.class);
	@Autowired
	private SysMenuService sysMenuService;
	/**
	 * 
	* @Title: list 
	* @Description: 跳转列表页
	* @param @param request
	* @param @param response
	* @param @return     
	* @return Object    
	* @throws
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", "菜单管理");
		return forword("sys/menu/menu",context);
	}
	/**
	 * 
	* @Title: dataList 
	* @Description: 分页查询
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequestMapping(value="/dataList",method = RequestMethod.POST)
	public void dataList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		if(params.get("parentId") == null || params.get("parentId").equals("")) {
			params.put("parentId", 0);
		}
		PageInfo<SysMenu> pageList = sysMenuService.query(params);
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
		sysMenuService.cancelDBAndCache(ids, getCurrUser());
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
		sysMenuService.deleteDBAndCache(ids);
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
		SysMenu record = sysMenuService.queryCacheById(id);
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
		SysMenu record = Request2ModelUtil.covert(SysMenu.class,request);
		if(record != null) {
			if(record.getId() == null) {
				record.setCreateBy(getCurrUser());
				sysMenuService.insert(record);
				sendSuccessMessage(response,"新增成功");
			}else {
				record.setUpdateBy(getCurrUser());
				sysMenuService.updateDB(record);
				sendSuccessMessage(response,"更新成功");
			}
		}
	}
}
