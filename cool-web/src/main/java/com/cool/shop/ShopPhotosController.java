package com.cool.shop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cool.api.ShopPhotosService;
import com.cool.base.BaseController;
import com.cool.model.ShopPhotos;
import com.cool.util.HtmlUtil;
import com.cool.util.Request2ModelUtil;
import com.cool.util.WebUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("shopPhotos")
public class ShopPhotosController extends BaseController{
	private final Logger logger = Logger.getLogger(ShopPhotosController.class);
	@Autowired
	private ShopPhotosService shopPhotosService;
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
	@RequiresPermissions("shop.shopPhotos.list")
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", "图片管理");
		return forword("shop/shopPhotos/shopPhotos",context);
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
	@RequiresPermissions("shop.shopPhotos.dataList")
	@RequestMapping(value="/dataList",method = RequestMethod.POST)
	public void dataList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<ShopPhotos> pageList = shopPhotosService.query(params);
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
	@RequiresPermissions("shop.shopPhotos.cancel")
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	public void cancel(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		shopPhotosService.cancelDBAndCache(ids, getCurrUser());
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
	@RequiresPermissions("shop.shopPhotos.delete")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public void detete(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		shopPhotosService.deleteDBAndCache(ids);
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
		ShopPhotos record = shopPhotosService.queryCacheById(id);
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
		ShopPhotos record = Request2ModelUtil.covert(ShopPhotos.class,request);
		if(record != null) {
			if(record.getId() == null) {
				record.setCreateBy(getCurrUser());
				shopPhotosService.insert(record);
				sendSuccessMessage(response,"新增成功");
			}else {
				record.setUpdateBy(getCurrUser());
				shopPhotosService.updateDB(record);
				sendSuccessMessage(response,"更新成功");
			}
		}
	}
}
