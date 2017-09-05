package com.cool.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
/**
 * 
* @ClassName: FileUploadUtil 
* @Description: 文件上传工具类
* @author panlei
* @date 2017年9月5日 下午4:29:30 
*
 */
public class FileUploadUtil {
	private static final Logger logger = Logger.getLogger(FileUploadUtil.class);
	/**
	 * 
	* @Title: doUpload 
	* @Description: 文件上传
	* @param @param multipartFile
	* @param @param realPath
	* @param @return     
	* @return String    
	* @throws
	 */
	public static String doUpload(MultipartFile multipartFile, String realPath) {
		logger.info("选择当前保存根路径："+realPath);
        String fileName = multipartFile.getOriginalFilename();//返回文件在客户机的路径
        logger.info("文件原本路径："+fileName);
        String fileUploadName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        logger.info("文件名："+fileUploadName);
        String typeName = fileUploadName.substring(fileUploadName.lastIndexOf(".") + 1);
        logger.info("文件类型："+typeName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        int random = (int) (Math.random()*1000000);
        String timeStamp = sdf.format(new Date());
        fileUploadName =timeStamp.substring(0,8) +"/"+ timeStamp +random+  fileUploadName.substring(fileUploadName.lastIndexOf("."));//重新命名
        String fileSavedPath = realPath +"/"+ fileUploadName;
        File file = new File(realPath,fileUploadName);
        if (!file.exists()) {
            file.mkdirs();
        }
        //复制文件到指定路径  
        try {
        		multipartFile.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return fileSavedPath;
	}
}
