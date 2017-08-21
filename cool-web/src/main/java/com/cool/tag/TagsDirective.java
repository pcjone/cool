package com.cool.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cool.Constants;
import com.cool.api.TagRpcService;
import com.cool.api.TaskRpcService;
import com.cool.model.SysDic;
import com.cool.model.TaskGroup;

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
public class TagsDirective implements TemplateDirectiveModel{
	
	@Autowired
	private TagRpcService tagRpcService;
	
	@Autowired
	private TaskRpcService taskRpcService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
        if (params == null || params.size() == 0) {
            throw new TemplateException("params can not be empty", env);
        }
        String excludeStr = null;
        String typeInput = null;
        // 处理传入的参数
        for (Object key : params.keySet()) {
        		String name = (String) key;
        		//数据字典标签
        		if (name.equalsIgnoreCase("type")) {
				if (params.get(key) instanceof TemplateScalarModel) {
					typeInput = ((TemplateScalarModel) params.get(key)).getAsString();
					System.out.println("typeInput:"+typeInput);
					if(typeInput.equals("sysDic")) {
						excludeStr = ((TemplateScalarModel) params.get("value")).getAsString();
						System.out.println("value:"+excludeStr);
						Map<String, Object> searchParams = new HashMap<String, Object>();
						searchParams.put("category", excludeStr);
						searchParams.put("enable", Constants.ENABLE_NO);
						List<SysDic> lists = tagRpcService.queryListByCategory(searchParams);
						for(SysDic l : lists) {
							System.out.println(l.getCategory());
						}
						env.setVariable("lists", ObjectWrapper.DEFAULT_WRAPPER.wrap(lists));
					}else if(typeInput.equals("taskGroup")) {
						Map<String, Object> searchParams = new HashMap<String, Object>();
		        			searchParams.put("enable", Constants.ENABLE_NO);
		        			List<TaskGroup> lists = taskRpcService.queryAllGroup(searchParams);
		        			for(TaskGroup l : lists) {
		        				System.out.println(l.getGroupName());
		        			}
		        			env.setVariable("lists",ObjectWrapper.DEFAULT_WRAPPER.wrap(lists));
					}
					
				} else {
					throw new TemplateException("execlude param must be string", env);
				}
        		}
        }
        body.render(env.getOut());
	}

}
