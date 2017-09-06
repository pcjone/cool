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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cool.api.ShopGoodsService;
import com.cool.api.ShopPhotosService;
import com.cool.api.SysParamService;
import com.cool.base.BaseController;
import com.cool.model.ShopGoods;
import com.cool.model.ShopPhotos;
import com.cool.model.SysParam;
import com.cool.session.UserSession;
import com.cool.util.FileUploadUtil;
import com.cool.util.HtmlUtil;
import com.cool.util.Request2ModelUtil;
import com.cool.util.UUIDUtil;
import com.cool.util.WebUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("shopGoods")
public class ShopGoodsController extends BaseController{
	private final Logger logger = Logger.getLogger(ShopGoodsController.class);
	@Autowired
	private ShopGoodsService shopGoodsService;
	
	@Autowired
	private ShopPhotosService shopPhotosService;
	
	@Autowired
	private SysParamService sysParamService;
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
	@RequiresPermissions("shop.shopGoods.list")
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", "商品管理");
		return forword("shop/shopGoods/shopGoods",context);
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
	@RequiresPermissions("shop.shopGoods.dataList")
	@RequestMapping(value="/dataList",method = RequestMethod.POST)
	public void dataList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<ShopGoods> pageList = shopGoodsService.query(params);
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
	@RequiresPermissions("shop.shopGoods.cancel")
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	public void cancel(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		shopGoodsService.cancelDBAndCache(ids, getCurrUser());
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
	@RequiresPermissions("shop.shopGoods.delete")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public void detete(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		shopGoodsService.deleteDBAndCache(ids);
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
		ShopGoods record = shopGoodsService.queryCacheById(id);
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
	public void save(HttpServletRequest request, HttpServletResponse response,
						@RequestParam(value="image",required = false) MultipartFile multipartFile) {
		ShopGoods record = Request2ModelUtil.covert(ShopGoods.class,request);
		if(record != null) {
			if(multipartFile != null && !multipartFile.isEmpty()) {
				SysParam param  = sysParamService.queryByKey("GOODS_PHOTO_PATH");
				String realPath  = param.getParamValue();
				String path = FileUploadUtil.doUpload(multipartFile, realPath);
				ShopPhotos photo = new ShopPhotos();
				photo.setPath(path);
				photo.setTableName("shop_goods");
				photo.setCreateBy(getCurrUser());
				photo = shopPhotosService.insert(photo);
				record.setGoodsIcon(photo.getId());
			}
			//元转分
			record.setAmount(record.getAmount()*100);
			if(record.getId() == null) {
				UserSession userSession = WebUtil.getCurrentUserSession();
				record.setUuid(UUIDUtil.getId());
				record.setShopId(userSession.getShopId());
				record.setCreateBy(getCurrUser());
				shopGoodsService.insert(record);
				sendSuccessMessage(response,"新增成功");
			}else {
				record.setUpdateBy(getCurrUser());
				shopGoodsService.updateDB(record);
				sendSuccessMessage(response,"更新成功");
			}
		}
	}
}
