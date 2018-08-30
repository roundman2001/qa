package com.fallen.springboot.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.fallen.springboot.models.Ueditor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
public class UeditorController {

	@RequestMapping("/ueditor")
	@ResponseBody
	public Object ueditor(@RequestParam(value = "action", defaultValue = "uploadimage"  ) String param, MultipartFile upfile, HttpServletRequest request) {
		Ueditor ueditor = new Ueditor();
		if (param != null && param.equals("config")) { 
			JSONObject jsonConfig = JSONObject.parseObject(UeditorConfig.UEDITOR_CONFIG);
			JSONArray.parse(UeditorConfig.UEDITOR_CONFIG);
			return JSONArray.parse(UeditorConfig.UEDITOR_CONFIG);
		} else if (param != null && param.equals("uploadimage") || param.equals("uploadscrawl")) {
			if (upfile != null) {
				// {state：”数据状态信息”，url：”图片回显路径”，title：”文件title”，original：”文件名称”，···}
				try {
					return uploadImg(upfile, request);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ueditor.setState("出现异常");
					return JSON.toJSONString(ueditor);
				}
			} else {
				ueditor.setState("文件为空");
				return JSON.toJSONString(ueditor);
			}
		} else {
			ueditor.setState("不支持该操作");
			return JSON.toJSONString(ueditor);
		}
	}

	@RequestMapping(value = "/imgUpload")
	@ResponseBody
	public Ueditor imgUpload(@RequestParam("action") String param, MultipartFile upfile, HttpServletRequest request) {
		Ueditor ueditor = new Ueditor();
		return ueditor;
	}

	public Object uploadImg(MultipartFile file, HttpServletRequest request) throws IOException {
		Ueditor ueditor = new Ueditor();
		// Users user = UserUtils.getUser(request) ;
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/public/ueditor/jsp/upload/image");
		//path = path.replaceAll("webapp", "resources");
		String ct = file.getContentType();
		
		String fileType = "";
		
		if (ct.indexOf("/") > 0) { 
			fileType = ct.substring(ct.indexOf("/") + 1);
		}
		String fileName = UUID.randomUUID() + "." + fileType;
		File targetFile = new File(path);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		File targetFile2 = new File(path + "/" + fileName);
		if (!targetFile2.exists()) {
			targetFile2.createNewFile();
		}
		// 保存
		try {
			file.transferTo(targetFile2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ueditor.setState("SUCCESS");
		ueditor.setTitle(fileName);
		ueditor.setOriginal(fileName);
		ueditor.setUrl("/ueditor/jsp/upload/image" + File.separator + fileName);
		System.out.println(JSON.toJSONString(ueditor));
		return ueditor;

	}
}