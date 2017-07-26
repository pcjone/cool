package com.cool.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.github.pagehelper.PageInfo;

/**
 * 页面工具类
 */
public class HtmlUtil {

	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出json格式<br>
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void writerJson(HttpServletResponse response, String jsonStr) {
		writer(response, jsonStr);
	}

	public static void writerJson(HttpServletResponse response, Object object) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			writer(response, JSON.toJSONString(object));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void writerJson(HttpServletResponse response, PageInfo<?> pageList) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", pageList.getTotal());
			jsonMap.put("rows", pageList.getList());
			writer(response, JSON.toJSONString(jsonMap));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出HTML代码<br>
	 * @param response
	 * @param htmlStr
	 * @throws Exception
	 */
	public static void writerHtml(HttpServletResponse response, String htmlStr) {
		writer(response, htmlStr);
	}

	private static void writer(HttpServletResponse response, String str) {
		try {
			//设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 写json数据 SPMS3.0 ADDED BY LWS 20160415
	 * 
	 * @param response
	 * @param str
	 */
	public static void writeGson(HttpServletResponse response, Map<String, Object> contentMap) {
		PrintWriter out = null;
		try {
			//设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("charset=UTF-8");
			out = response.getWriter();
			String content = JSON.toJSONString(contentMap);
			out.write(content);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
