package com.cool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cool.generator.ColumnClass;
import com.cool.generator.FreeMarkerTemplateUtils;

import freemarker.template.Template;

public class GeneratorUtil {

	private final String diskPath = "/Users/panlei/code/";
	// 表名称
	private final String tableName = "shop_goods";
	// 系统名称
	private final String sysName = "shop";

	private final String changeTableName = replaceUnderLineAndUpperCase(tableName);

	private final String smallTableName = StringUtils.lowerCase(changeTableName.substring(0, 1))
			+ changeTableName.substring(1);

	public static void main(String[] args) throws Exception {
		GeneratorUtil codeCreateUtil = new GeneratorUtil();
		System.out.println(codeCreateUtil.smallTableName);
		codeCreateUtil.generate();
	}

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cool_shop?useSSL=false", "root",
				"15158133802");
		return connection;
	}

	public void generate() throws Exception {
		Connection connection = getConnection();
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
		List<ColumnClass> list = getColumnClass(resultSet);
		// 生成xml文件
		generateXMLFile(resultSet, list);
		// 生成service文件
		generateServiceFile(resultSet);
		// 生成serviceImpl文件
		generateServiceImplFile(resultSet);
		// 生成mapper文件
		generateMapperFile(resultSet);
		// 生成controller文件
		generateControllerFile(resultSet);
		// 生成js文件
		generateJSFile(resultSet, list);
		//生产html文件
		generateHtmlFile(resultSet, list);
		// 生成Model文件
		generateModelFile(resultSet, list);

	}

	/**
	 * 
	 * @Title: generateModelFile @Description: 创建model类 @param @param
	 * resultSet @param @throws Exception @return void @throws
	 */
	private void generateModelFile(ResultSet resultSet, List<ColumnClass> list) throws Exception {
		final String suffix = ".java";
		final String path = diskPath + changeTableName + suffix;
		final String templateName = "Model.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_column", list);
		generateFileByTemplate(templateName, mapperFile, dataMap);
	}

	private void generateXMLFile(ResultSet resultSet, List<ColumnClass> list) throws Exception {
		final String suffix = "Mapper.xml";
		final String path = diskPath + changeTableName + suffix;
		final String templateName = "XML.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_column", list);
		generateFileByTemplate(templateName, mapperFile, dataMap);
	}

	private void generateServiceFile(ResultSet resultSet) throws Exception {
		final String suffix = "Service.java";
		final String path = diskPath + changeTableName + suffix;
		final String templateName = "Service.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		generateFileByTemplate(templateName, mapperFile, dataMap);
	}

	private void generateMapperFile(ResultSet resultSet) throws Exception {
		final String suffix = "Mapper.java";
		final String path = diskPath + changeTableName + suffix;
		final String templateName = "Mapper.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		generateFileByTemplate(templateName, mapperFile, dataMap);
	}

	private void generateServiceImplFile(ResultSet resultSet) throws Exception {
		final String suffix = "ServiceImpl.java";
		final String path = diskPath + changeTableName + suffix;
		final String templateName = "ServiceImpl.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		generateFileByTemplate(templateName, mapperFile, dataMap);
	}

	private void generateControllerFile(ResultSet resultSet) throws Exception {
		final String suffix = "Controller.java";
		final String path = diskPath + changeTableName + suffix;
		final String templateName = "Controller.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		generateFileByTemplate(templateName, mapperFile, dataMap);
	}

	private void generateJSFile(ResultSet resultSet, List<ColumnClass> list) throws Exception {
		final String suffix = ".js";
		final String path = diskPath + smallTableName + suffix;
		final String templateName = "js.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_column", list);
		generateFileByTemplate(templateName, mapperFile, dataMap);

	}
	
	private void generateHtmlFile(ResultSet resultSet, List<ColumnClass> list) throws Exception {
		final String suffix = ".html";
		final String path = diskPath + smallTableName + suffix;
		final String templateName = "html.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_column", list);
		generateFileByTemplate(templateName, mapperFile, dataMap);

	}

	private List<ColumnClass> getColumnClass(ResultSet resultSet) throws Exception {
		List<ColumnClass> columnClassList = new ArrayList<>();
		ColumnClass columnClass = null;
		while (resultSet.next()) {
			// id字段略过
			if (resultSet.getString("COLUMN_NAME").equals("id"))
				continue;
			if (resultSet.getString("COLUMN_NAME").equals("enable"))
				continue;
			if (resultSet.getString("COLUMN_NAME").equals("remark"))
				continue;
			if (resultSet.getString("COLUMN_NAME").equals("create_by"))
				continue;
			if (resultSet.getString("COLUMN_NAME").equals("create_time"))
				continue;
			if (resultSet.getString("COLUMN_NAME").equals("update_by"))
				continue;
			if (resultSet.getString("COLUMN_NAME").equals("update_time"))
				continue;
			columnClass = new ColumnClass();
			// 获取字段名称
			columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
			// 获取字段类型
			columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
			// 转换字段名称，如 sys_name 变成 SysName
			columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
			// 字段在数据库的注释
			columnClass.setColumnComment(resultSet.getString("REMARKS"));
			columnClassList.add(columnClass);
		}
		return columnClassList;
	}

	private void generateFileByTemplate(final String templateName, File file, Map<String, Object> dataMap)
			throws Exception {
		Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
		FileOutputStream fos = new FileOutputStream(file);
		dataMap.put("table", tableName);
		dataMap.put("table_name_small", smallTableName);
		dataMap.put("table_name", changeTableName);
		dataMap.put("sys_name", sysName);
		Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
		template.process(dataMap, out);
	}

	private String replaceUnderLineAndUpperCase(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		int count = sb.indexOf("_");
		while (count != 0) {
			int num = sb.indexOf("_", count);
			count = num + 1;
			if (num != -1) {
				char ss = sb.charAt(count);
				char ia = (char) (ss - 32);
				sb.replace(count, count + 1, ia + "");
			}
		}
		String result = sb.toString().replaceAll("_", "");
		return StringUtils.capitalize(result);
	}

	public String getCurDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 ahh:mm:ss");
		return sdf.format(date);
	}
}
