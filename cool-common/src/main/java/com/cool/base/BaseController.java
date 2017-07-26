/**
 * 
 */
package com.cool.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cool.Constants;
import com.cool.common.HttpCode;
import com.cool.config.Resources;
import com.cool.exception.ServiceException;
import com.cool.util.HtmlUtil;
import com.cool.util.WebUtil;


/**
 * 控制器基类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class BaseController {
	protected final Logger logger = LogManager.getLogger(this.getClass());
	public final static String SUCCESS = "success";
	public final static String MSG = "msg";
	public final static String DATA = "data";

	/** 获取当前用户 */
	protected String getCurrUser() {
		return WebUtil.getCurrentUser();
	}

	/** 设置成功响应代码 */
	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
		return setSuccessModelMap(modelMap, null);
	}

	/** 设置成功响应代码 */
	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
		return setModelMap(modelMap, HttpCode.OK, data);
	}

	/** 设置响应代码 */
	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code) {
		return setModelMap(modelMap, code, null);
	}

	/** 设置响应代码 */
	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
		modelMap.remove("void");
		if (data != null) {
			modelMap.put("data", data);
		}
		modelMap.put("httpCode", code.value());
		modelMap.put("msg", code.msg());
		modelMap.put("timestamp", System.currentTimeMillis());
		return ResponseEntity.ok(modelMap);
	}

	/** 异常处理 
	 * @throws Exception 
	 * @throws IOException */
	@ExceptionHandler(RuntimeException.class)
	public void exceptionHandler(HttpServletResponse response, Exception ex) {
		logger.error(Constants.Exception_Head, ex);
		String msg = "";
		if (ex instanceof ServiceException) {
			msg = ex.getMessage();
		} else if (ex instanceof NullPointerException) {
			msg = Resources.getMessage("NULL_POINT");
		} else if (ex instanceof UnauthorizedException) {
			try {
				response.sendRedirect("noPerm");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (ex instanceof IOException) {
			msg = Resources.getMessage("IO_EXCEPTION");
		} else {
			msg = Resources.getMessage("SYS_ERROR");
		}
		sendFailureMessage(response, msg);
	}

	/**
	  * 所有ActionMap 统一从这里获取
	  * @return
	  */
	public Map<String, Object> getRootMap() {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		return rootMap;
	}

	/**
	 * SpringMVC 页面跳转
	 * @param viewName
	 * @param context
	 * @return
	 */
	public ModelAndView forword(String viewName, Map<String, Object> context) {
		return new ModelAndView(viewName, context);
	}

	/**
	 *
	 * 提示成功信息
	 *
	 * @param message
	 *
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 提示成功信息
	 * @param response
	 * @param message
	 * @param data 附带的数据
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		result.put(DATA, data);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 *
	 * 提示失败信息
	 *
	 * @param message
	 *
	 */
	public void sendFailureMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}
}
