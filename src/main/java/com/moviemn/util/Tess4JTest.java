
package com.moviemn.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Tess4JTest {

    public static Logger logger = LoggerFactory.getLogger(Tess4JTest.class);
	static final double MINIMUM_DESKEW_THRESHOLD = 0.05d;
	ITesseract instance;

	private String classPath = Tess4JTest.class.getResource("/").toString().replace("file:/", "");
	private String webappPath = "";

	private final String testResourcesDataPath = classPath + "test-data";
	private final String testResourcesLanguagePath = classPath + "tessdata";

	public String testDoOCR_BufferedImage(String filePath)  {
		if (classPath.contains("WEB-INF")) {
			webappPath = classPath.substring(0, classPath.indexOf("WEB-INF"));
		}
		logger.info("filePath:{},datapath:{}",filePath, classPath);
		instance = new Tesseract();
		instance.setDatapath(new File(classPath).getPath());
		logger.info("doOCR on a buffered image of a PNG");
		File imageFile = new File(webappPath, filePath);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(imageFile);
		} catch (IOException e) {
			logger.error("errorMessage:{}", e);
			return "shame! something was wrong";
		}

		// set language
		instance.setDatapath(testResourcesLanguagePath);
		instance.setLanguage("chi_sim");

		String result = null;
		try {
			result = instance.doOCR(bi);
		} catch (TesseractException e) {
			logger.error("errorMessage:{}", e);
			return "shame! something was wrong";
		}
		logger.info(result);
		//result = StringEscapeUtils.escapeHtml4(result);
		//result = result.replace("\\t", "&#10;");
		//result = result.replace("\\", "&#92;");
		return result;
	}

	
	
	public static void main(String[] args) {
		
	}

}
