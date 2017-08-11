package com.cool.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cool.Constants;
import com.cool.api.TagRpcService;
import com.cool.model.SysDic;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;
/**
 * 
* @ClassName: MyDirective 
* @Description: 自定义指令
* @author panlei
* @date 2017年8月11日 下午1:40:01 
*
 */
public class MyDirective implements TemplateDirectiveModel{
	
	@Autowired
	private TagRpcService tagRpcService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
        if (params == null || params.size() == 0) {
            throw new TemplateException("params can not be empty", env);
        }
        String excludeStr = null;
        // 处理传入的参数
        for (Object key : params.keySet()) {
        		String name = (String) key;
        		//数据字典标签
        		if (name.equalsIgnoreCase("sysDic")) {
				if (params.get(key) instanceof TemplateScalarModel) {
					excludeStr = ((TemplateScalarModel) params.get(key)).getAsString();
					Map<String, Object> searchParams = new HashMap<String, Object>();
					searchParams.put("category", excludeStr);
					searchParams.put("enable", Constants.ENABLE_NO);
					List<SysDic> lists = tagRpcService.queryListByCategory(searchParams);
					env.setVariable("sysDicList", ObjectWrapper.DEFAULT_WRAPPER.wrap(lists));
				} else {
					throw new TemplateException("execlude param must be string", env);
				}
        		}
        }
        body.render(env.getOut());
	}

}
