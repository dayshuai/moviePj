package com.moviemn.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.moviemn.base.BaseController;
import com.moviemn.utils.DateTimeUtil;

@Controller
@RequestMapping({ "/upload" })
public class UploadController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

	@ResponseBody
	@RequestMapping({ "uploadImage" })
	public Map<String, Object> uploadTemplateImage(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, Model model) {
		if ((file != null) && (file.getSize() > 0L)) {
			String inputPath = request.getSession().getServletContext().getRealPath("temp");
			File tempFile = new File(inputPath);

			if (!tempFile.exists()) {
				tempFile.mkdir();
			}
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
			try {
				String refName = DateTimeUtil.FormatSystemDateSN() + suffix;
				BufferedInputStream in = new BufferedInputStream(file.getInputStream());
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(inputPath + "//" + refName));
				byte[] bb = new byte[1024];
				int n;
				while ((n = in.read(bb)) != -1) {
					out.write(bb, 0, n);
				}
				out.close();
				in.close();
				String filePath = "/temp/" + refName;

				return resultTrue(filePath);
			} catch (Exception e) {
				e.printStackTrace();
				return resultFalse("上传文件异常请,联系管理员");
			}
		}
		return resultFalse("请导入文件后上传");
	}
}
