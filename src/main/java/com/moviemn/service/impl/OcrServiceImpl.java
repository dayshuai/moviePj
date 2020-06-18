package com.moviemn.service.impl;

import org.springframework.stereotype.Service;

import com.moviemn.service.OcrService;
import com.moviemn.util.Tess4JTest;

/**
 * Created by SuperS on 15/12/13.
 */
@Service("ocrService")
public class OcrServiceImpl implements OcrService {

	@Override
	public String doOcr(String filePath) {
		Tess4JTest test = new Tess4JTest();
		return test.testDoOCR_BufferedImage(filePath);
	}
}
