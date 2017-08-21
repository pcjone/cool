package com.cool.tags;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cool.Constants;
import com.cool.util.SpringContextUtil;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class APITemplateDirectiveModel extends BaseTemplateDirectiveModel{
	
	private final Logger logger = Logger.getLogger(APITemplateDirectiveModel.class);

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Override
	protected Map<String, TemplateModel> putValue(Map params) throws TemplateModelException {
		Map<String, TemplateModel> paramWrap = null ;
		if(null != params && params.size() != 0 || null != params.get(Constants.TARGET)){
			String name =  params.get(Constants.TARGET).toString() ;
			logger.info("Constants.TARGET:"+name);
			paramWrap = new HashMap<String, TemplateModel>(params);			
			/**
			 * 获取子类，用父类接收，
			 */
			SuperBaseTag tag =  SpringContextUtil.getBean(name,SuperBaseTag.class);
			logger.info("Service:"+tag);
			//父类调用子类方法
			Object result = tag.result(params);
			
			//输出
			paramWrap.put(Constants.OUT_TAG_NAME, DEFAULT_WRAPPER.wrap(result));
		}else{
			logger.debug("Cannot be null, must include a 'name' attribute!");
		}
		return paramWrap;
	}

}
