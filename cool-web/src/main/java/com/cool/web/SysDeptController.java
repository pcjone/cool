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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.cool.Constants;
import com.cool.api.SysDeptService;
import com.cool.base.BaseController;
import com.cool.model.SysDept;
import com.cool.model.expand.SysDeptExpand;
import com.cool.model.expand.SysMenuExpand;
import com.cool.model.expand.TreeView;
import com.cool.util.HtmlUtil;
import com.cool.util.Request2ModelUtil;
import com.cool.util.WebUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("dept")
public class SysDeptController extends BaseController{
	
	private final Logger logger = Logger.getLogger(SysDeptController.class);
	
	@Autowired
	private SysDeptService sysDeptService;
	
	@RequiresPermissions("sys.dept.list")
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", " 部门管理");
		return forword("sys/dept/dept",context);
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
	@RequiresPermissions("sys.dept.dataList")
	@RequestMapping(value="/dataList",method = RequestMethod.POST)
	public void dataList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		if(params.get("parentId") == null || params.get("parentId").equals("")) {
			params.put("parentId", Constants.PERMISSION_ZERO);
		}
		PageInfo<SysDept> pageList = sysDeptService.query(params);
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
	@RequiresPermissions("sys.dept.cancel")
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	public void cancel(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		sysDeptService.cancelDBAndCache(ids, getCurrUser());
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
	@RequiresPermissions("sys.dept.delete")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public void detete(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		sysDeptService.deleteDBAndCache(ids);
		sendSuccessMessage(response,"删除成功");
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
		SysDept record = Request2ModelUtil.covert(SysDept.class,request);
		if(record != null) {
			if(record.getId() == null) {
				record.setCreateBy(getCurrUser());
				sysDeptService.insert(record);
				sendSuccessMessage(response,"新增成功");
			}else {
				record.setUpdateBy(getCurrUser());
				sysDeptService.updateDB(record);
				sendSuccessMessage(response,"更新成功");
			}
		}
	}
	
	/**
	 * 
	* @Title: getTreeView 
	* @Description: 获取菜单列表
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "/getTreeView", method = RequestMethod.POST)
	public void getTreeView(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("eanble", Constants.ENABLE_NO);
		List<SysDeptExpand> trees = sysDeptService.queryListDeptTree(params);
		TreeView treeView = new TreeView();
		treeView.setText("全部");
		treeView.setValue(Constants.PERMISSION_ZERO);
		createTreeview(treeView,trees);
		HtmlUtil.writerJson(response,"["+JSON.toJSONString(treeView)+"]");
	}
	
	/**
	 * 
	* @Title: createTreeview 
	* @Description: 创建菜单树
	* @param @param treeView
	* @param @param trees     
	* @return void    
	* @throws
	 */
	private void createTreeview(TreeView treeView,List<SysDeptExpand> trees) {
		List<TreeView> nodes = new ArrayList<TreeView>();
		for(SysDeptExpand dept : trees) {
			TreeView treeViewChild = new TreeView();
			treeViewChild.setText(dept.getDeptName());
			treeViewChild.setValue(dept.getId());
			if(dept.isHasChild()) {
				createTreeview(treeViewChild,dept.getChildSysDept());
			}			
			nodes.add(treeViewChild);
		}
		treeView.setNodes(nodes);
	}

}
