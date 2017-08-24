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

import com.beust.jcommander.internal.Maps;
import com.cool.Constants;
import com.cool.api.SysDicService;
import com.cool.base.BaseController;
import com.cool.model.SysDic;
import com.cool.util.CacheUtil;
import com.cool.util.HtmlUtil;
import com.cool.util.Request2ModelUtil;
import com.cool.util.WebUtil;
import com.github.pagehelper.PageInfo;
/**
 * 
* @ClassName: SysDicController 
* @Description: 数据字典控制器
* @author panlei
* @date 2017年7月26日 下午5:41:17 
*
 */
@Controller
@RequestMapping("dic")
public class SysDicController extends BaseController{
	private final Logger logger = Logger.getLogger(SysDicController.class);
	@Autowired
	private SysDicService sysDicService;
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
	@RequiresPermissions("sys.dic.list")
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", "数据字典");
		return forword("sys/dic/dic",context);
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
	@RequiresPermissions("sys.dic.dataList")
	@RequestMapping(value="/dataList",method = RequestMethod.POST)
	public void dataList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<SysDic> pageList = sysDicService.query(params);
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
	@RequiresPermissions("sys.dic.cancel")
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	public void cancel(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		sysDicService.cancelDBAndCache(ids, getCurrUser());
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
	@RequiresPermissions("sys.dic.delete")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public void detete(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		sysDicService.deleteDBAndCache(ids);
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
		SysDic record = sysDicService.queryCacheById(id);
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
		SysDic record = Request2ModelUtil.covert(SysDic.class,request);
		if(record != null) {
			if(record.getId() == null) {
				Map<String,Object> searchParams = Maps.newHashMap();
				searchParams.put("codeValue", record.getCodeValue());
				searchParams.put("category", record.getCategory());
				List<SysDic> checkList = sysDicService.validateSysDic(searchParams);
				if(checkList !=null && checkList.size()>0) {
					sendFailureMessage(response,"新增失败，键值对重复");
					return;
				}
				record.setCreateBy(getCurrUser());
				sysDicService.insert(record);
				sendSuccessMessage(response,"新增成功");
			}else {
				record.setUpdateBy(getCurrUser());
				sysDicService.updateDB(record);
				sendSuccessMessage(response,"更新成功");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/sysDic", method = RequestMethod.POST)
	public void dicList(String[] categorys, HttpServletRequest request, HttpServletResponse response) {
		List<SysDic> dicList = new ArrayList<SysDic>();
		for (String category : categorys) {
			//先查询缓存		
			List<SysDic> partList = (List<SysDic>) CacheUtil.getRedisHelper()
					.getNoExpiry(Constants.DICTIOINARY_CACHE + category);
			//缓存没有查询DB
			if (partList == null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("category", category);
				params.put("enable", Constants.ENABLE_NO);
				partList = sysDicService.queryListByCategory(params);
			}
			dicList.addAll(partList);
		}
		Map<String, String> dictJsonMap = new HashMap<String, String>();
		for (SysDic d : dicList) {
			dictJsonMap.put(d.getCategory() + d.getCodeValue(), d.getCodeText());
		}
		HtmlUtil.writerJson(response, dictJsonMap);
	}
}
