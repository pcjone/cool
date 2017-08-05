package com.cool.web;

import java.util.ArrayList;
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
import com.cool.api.SysMenuService;
import com.cool.api.SysRoleMenuService;
import com.cool.api.SysRoleService;
import com.cool.base.BaseController;
import com.cool.model.SysMenu;
import com.cool.model.SysRole;
import com.cool.model.expand.JsTree;
import com.cool.model.expand.State;
import com.cool.model.expand.SysMenuExpand;
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
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 
	* @Title: roleIndex 
	* @Description: 跳转到list页面
	* @param @return     
	* @return Object    
	* @throws
	 */
	@RequiresPermissions("sys.role.list")
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
	@RequiresPermissions("sys.role.dataList")
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
	@RequiresPermissions("sys.role.cancel")
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	public void cancel(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		sysRoleService.cancelDBAndCache(ids, getCurrUser());
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
	@RequiresPermissions("sys.role.delete")
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
	
	@RequestMapping(value = "/updateRoleMenu", method = RequestMethod.POST)
	public void updateRoleMenu(Long[] menuIds,Long roleId, HttpServletRequest request,
			HttpServletResponse response) {
		List<Long> oldMenuIds = sysRoleMenuService.queryMenuIdByRoleId(roleId);
		List<Long> addMenuIds = new ArrayList<Long>();
		if(menuIds !=null && menuIds.length>0) {
			for(Long id : menuIds) {
				if(oldMenuIds.contains(id)) {
					//删除已有的权限，找出需要删掉的权限
					oldMenuIds.remove(id);
				}else {
					//找到新增的权限
					addMenuIds.add(id);
				}
			}
		}
		sysRoleService.addOrDeleteSysRoleMenu(addMenuIds,oldMenuIds,roleId,WebUtil.getCurrentUser());
		sendSuccessMessage(response,"权限修改成功");
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
	/**
	 * 
	* @Title: getRoleMenuTree 
	* @Description: 根据角色id查询权限树
	* @param @param roleId
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	
	@RequestMapping(value="/roleMenuTree",method = RequestMethod.POST)
	public void getRoleMenuTree(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,Object> params = new HashMap<String,Object>();
		//顶级目录
		params.put("parentId", Constants.PERMISSION_ZERO);
		params.put("enable", Constants.ENABLE_NO);
		//查询所有权限菜单(!=-1)
		params.put("menuType", -1);
		List<SysMenuExpand> sysMenus = sysMenuService.queryListMenuTree(params);
		JsTree jsTree = new JsTree();
		jsTree.setText("全部");
		jsTree.setId(-1l);
		State state;
		state = new State(true,false,false);		
		jsTree.setState(state);
		createJstree(jsTree,sysMenus);
		HtmlUtil.writerJson(response,"["+JSON.toJSONString(jsTree)+"]");
	}
	
	@RequiresPermissions("sys.role.menuPermission")
	@RequestMapping(value = "/queryRoleInfo",method = RequestMethod.POST)
	public void queryRoleInfo(Long roleId,HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,Object> contentMap = getRootMap();
		List<Long> menuIds = sysRoleMenuService.queryMenuIdByRoleId(roleId);
		contentMap.put("menuIds", menuIds);
		HtmlUtil.writeGson(response, contentMap);
	}
	/**
	 * 
	* @Title: createJstree 
	* @Description: 组装角色权限菜单树
	* @param @param jsTree
	* @param @param sysMenus
	* @param @param roleTrees     
	* @return void    
	* @throws
	 */
	private void createJstree(JsTree jsTree,List<SysMenuExpand> sysMenus) {
		List<JsTree> jsTreeList = new ArrayList<JsTree>();
		for(SysMenuExpand sysMenu : sysMenus) {
			JsTree tree = new JsTree();
			tree.setText(sysMenu.getMenuName());
			tree.setId(sysMenu.getId());
			tree.setIcon(sysMenu.getIconcls());
			State state;
			state = new State(false,false,false);
			tree.setState(state);
			if(sysMenu.isHasChild()) {
				createJstree(tree,sysMenu.getChildSysMenu());
			}
			jsTreeList.add(tree);
		}
		jsTree.setChildren(jsTreeList);
	}

}
