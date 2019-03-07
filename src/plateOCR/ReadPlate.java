package plateOCR;

import java.awt.image.BufferedImage;

import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

public class ReadPlate {
	
	public String defaultPlateReader(BufferedImage img) {
		String result =null;
		
		System.load("/usr/local/lib/libtesseract.so");
		//usr/local/lib/tesseract 
		String low = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		low = low.toLowerCase();
		 String whiteList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+low+"0123456789!";
	       
	        //Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping

	         Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
	         instance.setTessVariable("tessedit_char_whitelist", whiteList);
	         
	         instance.setDatapath("/usr/local/share/tessdata/"); 
	         instance.setLanguage("eng");
	        // System.out.println(whiteList);
	       
	        try {
	           result  = instance.doOCR(img);
	            //System.out.println(result);
	        } catch (TesseractException e) {
	            System.err.println(e.getMessage());
	}
			return result;
	}
	
	

}
