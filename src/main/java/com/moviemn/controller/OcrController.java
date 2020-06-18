package com.moviemn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moviemn.base.BaseController;
import com.moviemn.bean.TbMovie;
import com.moviemn.service.OcrService;

@Controller
@RequestMapping({ "/ocr" })
public class OcrController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(OcrController.class);

	@Autowired
	OcrService ocrService;
	
	@ResponseBody
	@RequestMapping({ "doOcr" })
	public Map<String, Object> doOcr(String filePath,
			HttpServletRequest request, Model model) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String result = ocrService.doOcr(filePath);
		returnMap.put("returnVal", result);
		return returnMap;
	}
	
	
	@RequestMapping()
	public String index(Model model, TbMovie tbMovie) {
		return "/ocr/ocrView";
	}
}
