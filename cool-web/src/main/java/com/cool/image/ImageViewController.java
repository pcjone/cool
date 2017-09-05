package com.cool.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cool.api.ShopPhotosService;
import com.cool.model.ShopPhotos;

@Controller
@RequestMapping("image")
public class ImageViewController {
	
	@Autowired
	private ShopPhotosService shopPhotosService;
	
	@RequestMapping(value="/view",method = RequestMethod.GET)
	public void imageView(@RequestParam(value="path",required=true)long id,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		ShopPhotos photo = shopPhotosService.queryDBById(id);	
		File file = new File(photo.getPath());
		//判断文件是否存在如果不存在就返回默认图标
        if(!(file.exists() && file.canRead())) {
            file = new File(request.getSession().getServletContext().getRealPath("/")
                    + "resources/icons/icon_upload.png");
        }
        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        inputStream.read(data);
        inputStream.close();
		response.setContentType("image/png");  
        response.setCharacterEncoding("utf-8"); 
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
	}
}
