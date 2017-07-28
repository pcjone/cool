package com.cool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.Template;

public class CodeCreateUtil {

	private final String diskPath = "/Users/panlei/MyCodePath/";
	
    public static void main(String[] args) throws Exception {
    		CodeCreateUtil codeCreateUtil = new CodeCreateUtil();
    		System.out.println(codeCreateUtil.getCurDate());
    		System.out.println(codeCreateUtil.replaceUnderLineAndUpperCase("sys_dic"));
    		//codeCreateUtil.generate();
    }
    
    public void generate() throws Exception{
    		Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("table_name_small","sys_dic");
        dataMap.put("table_name",replaceUnderLineAndUpperCase("sys_dic"));
        dataMap.put("author","panlei");
        dataMap.put("date",getCurDate());
        dataMap.put("package_name","com.cool");
        dataMap.put("table_annotation","数据字典");
        dataMap.put("src", "dic");
    		generateControllerFile(dataMap);
    }
	public void generateControllerFile(Map<String,Object> dataMap) throws Exception{
        final String suffix = "Controller.java";
        final String path = diskPath + dataMap.get("table_name") + suffix;
        final String templateName = "Controller.ftl";
        File mapperFile = new File(path);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }
	
	 private void generateFileByTemplate(final String templateName,File file,Map<String,Object> dataMap) throws Exception{
	        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
	        FileOutputStream fos = new FileOutputStream(file);
	        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
	        template.process(dataMap,out);
	    }
	
	public String replaceUnderLineAndUpperCase(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num + 1;
            if(num != -1){
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count , count + 1,ia + "");
            }
        }
        String result = sb.toString().replaceAll("_","");
        return StringUtils.capitalize(result);
    }
	
	public String getCurDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 ahh:mm:ss");
		return sdf.format(date);
	}
}
