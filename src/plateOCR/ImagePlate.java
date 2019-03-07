package plateOCR;
/*package com.func.img;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImagePlate {
	private Mat origImg;
	private Mat morphKernel = null;
	 List<MatOfPoint> contours;
	    MatOfFloat4 hierarchy;
	    Mat mask ;
	    
	     * Atempt to get license plate rectangle
	      @param grayScaleImg
	      @return license plate rectangle
	      @return null if plate not found
	     * 
	     

	   public Mat getPlateMat(Mat grayScaleImg){
	    	 Rect roi = null; 
	    	for(int i = 0; i < contours.size(); i++)
			    {
	    	Rect rect = Imgproc.boundingRect(contours.get(i));
	        Mat maskROI =  new Mat(mask, rect);
	        maskROI.setTo(new Scalar(0, 0, 0));
	        Imgproc.drawContours(mask, contours, i, new Scalar(255, 255, 255), Core.FILLED);
	        // ratio of non-zero pixels in the filled region
	        double r = (double)Core.countNonZero(maskROI)/(rect.width*rect.height);

	        if (r > .90  &&  (rect.height > 40 && rect.width >380)){
	         
	           Imgproc.rectangle(origImg, Imgproc.boundingRect(contours.get(i)).tl(),
			    		Imgproc.boundingRect(contours.get(i)).br(), new Scalar(0, 0, 255),0, 8, 0);
	           
	          roi  = new Rect(Imgproc.boundingRect(contours.get(i)).x, Imgproc.boundingRect(contours.get(i)).y,
	            		Imgproc.boundingRect(contours.get(i)).width, Imgproc.boundingRect(contours.get(i)).height);
	        }
			    }
	    	if(roi == null)
	    		return null;
	    	return new Mat(grayScaleImg,roi);
	    }
	    
	     *  find image contours 
	      @param binarizeImg
	     @param connImg
	     * 
	     * 
	     
	  
	     
	     
	public void findCountrours(Mat binarizeImg,Mat connImg){
			mask = Mat.zeros(binarizeImg.size(), CvType.CV_8UC1);
			contours = new ArrayList<MatOfPoint>();  
			hierarchy =  new MatOfFloat4();
		    Imgproc.findContours(connImg, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE,new Point(0, 0));
	}
	
	
	 * create connected mat
	 * @param binarizeImg
	 * @return conectedMat
	 
	public Mat imgConnectoH(Mat binarizeImg){
		  Mat connected =  new Mat();
		    morphKernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(9, 1));
		    Imgproc.morphologyEx(binarizeImg, connected, Imgproc.MORPH_CLOSE, morphKernel);
		    return connected;
	}
	
	
	 * binarize img
	 * @param grayImg
	 * @return 
	 
	public Mat imgToBinarize(Mat grayImg){
		 Mat bw =  new Mat();
		    Imgproc.threshold(grayImg, bw, 0.0, 255.0, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		    return bw;
	}
	
	 *  convert to BGR2GRAY
	 * @param nonGrayImg
	 * @return GrayScaleImg
	 
	
	public Mat imgToGrayScale(Mat nonGrayImg){
		Mat gray =  new Mat();
		Imgproc.cvtColor(nonGrayImg, gray, Imgproc.COLOR_BGR2GRAY);
		return gray;
	}
	
	public Mat imgToGradient(Mat grayScaleImg){
		Mat grad =  new Mat();
		morphKernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3, 3));
	    Imgproc.morphologyEx(grayScaleImg, grad, Imgproc.MORPH_GRADIENT, morphKernel);
	    return grad;
	}
	
	 * getmorphKernel
	 * 
	 * @return morphKernel
	
	public Mat getmorphKernel(){
		if(morphKernel == null)
			return null;
		return morphKernel;
	}
	
	public void setOrigImg(Mat orig){
		this.origImg =  orig;
	}
	public Mat getOrig() {
		return this.origImg;
	}
	

}
*/