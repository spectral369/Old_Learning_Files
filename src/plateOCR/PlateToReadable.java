package plateOCR;
/*package com.func.img;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;

public class PlateToReadable {
	public Mat plateImg;
	public Mat origImg;
	private Mat plateKernel = null;
	List<MatOfPoint> plateContours;
	MatOfFloat4 plateHierarchy;
	Mat plateMask;
	Mat bin;
	List<Mat> countryLetters = new ArrayList<>();
	Mat plateNumbers;


	
	*//**
	 * get country code mat
	 * @return
	 *//*
	public Mat getCountryCode(){
	
	
		Mat m = new Mat(countryLetters.get(0).cols(), (countryLetters.get(0).rows() + countryLetters.get(1).rows()), countryLetters.get(0).type());
	
		for(Mat  c :countryLetters){
		c.adjustROI(0, 0, c.cols(),c.rows());
		c.copyTo(m);
		}
	
		m =  m.colRange(m.cols()-m.cols()+22, m.cols()-28);
		int erosion_size = 1;
		int dialate_size = 4;
		Mat element_ero = Imgproc.getStructuringElement(Imgproc.MORPH_OPEN,
			    new Size(1.0 * erosion_size + 1, 1.4 * erosion_size + 1),  //1.9/1.4
			    new Point(erosion_size, erosion_size));
			Mat element_dia = Imgproc.getStructuringElement(Imgproc.MORPH_OPEN,
			    new Size(1.0 * dialate_size + 4, 2.2* dialate_size + 1.3),//1.4/1.4
			    new Point(dialate_size, dialate_size));
			Imgproc.erode(m, m, element_ero);
			Imgproc.dilate(m, m, element_dia);
			Imgproc.medianBlur(m, m, 1);
			
			Imgproc.GaussianBlur(m, m, new Size(3, 3), 10);
			Core.addWeighted(m,9, m, -6, 0, m);
		
		Imgproc.resize(m, m, new Size(origImg.cols() * 0.24,origImg.rows() * 0.15), 0, 0, Imgproc.INTER_LINEAR);
		
	//test
		
		Photo.fastNlMeansDenoising(m, m, 12, 21, 12);
		
		
		
		
		return m;
	}
	
	*//**
	 * Standard ro license plate
	 * get:-license plate numbers
	 * get:-country codes
	 *//*
	public void Step4ProcessPlateNumbers() {
		Mat cropC = new Mat();
		Mat cropCM = new Mat();
	
		MatOfPoint2f approxCurve = new MatOfPoint2f();
		
		for (int idx2 = 0; idx2 < plateContours.size(); idx2++) {
			MatOfPoint2f contour2f = new MatOfPoint2f( plateContours.get(idx2).toArray() );
            //Processing on mMOP2f1 which is in type MatOfPoint2f
            double approxDistance = Imgproc.arcLength(contour2f, true)*0.04;//0.03
            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

            //Convert back to MatOfPoint
            MatOfPoint points = new MatOfPoint( approxCurve.toArray() );
            
	        Rect rect2 = Imgproc.boundingRect(points);

			Mat maskROI2 = new Mat(plateMask, rect2);
			maskROI2.setTo(new Scalar(0, 0, 0));
			 Imgproc.drawContours(plateMask, plateContours, idx2, new Scalar(255, 255, 255), Core.FILLED);
			double r2 = (double)Core.countNonZero(maskROI2)/(rect2.width*rect2.height);
			
			Imgproc.rectangle(codulMasinii, Imgproc.boundingRect(plateContours.get(idx2)).tl(),
		    		Imgproc.boundingRect(plateContours.get(idx2)).br(), new Scalar(255, 255, 255),4, 8, 0);
        //  Imgcodecs.imwrite("nr"+idx2+".jpg", codulMasinii);
			
			if (r2 > .80 && ((rect2.height < 300 && rect2.height > 80) && (rect2.width < 80 && rect2.width > 50))) {
			
				Imgcodecs.imwrite("image.jpg", bin);
				Rect ro = new Rect(Imgproc.boundingRect(plateContours.get(idx2)).x,
						Imgproc.boundingRect(plateContours.get(idx2)).y,
						Imgproc.boundingRect(plateContours.get(idx2)).width,
						Imgproc.boundingRect(plateContours.get(idx2)).height);

				cropC = new Mat(bin, ro);
				cropC = removeBorders(cropC);
				Imgproc.threshold(cropC, cropC, 0.0, 255.0, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
				Imgcodecs.imwrite("letters"+idx2+".jpg", cropC);
				countryLetters.add(cropC);

				
				 * Imgproc.rectangle(codulTarii,
				 * Imgproc.boundingRect(plateContours.get(idx2)).tl(),
				 * Imgproc.boundingRect(plateContours.get(idx2)).br(), new
				 * Scalar(255, 255, 255),4, 8, 0);
				 * Imgcodecs.imwrite("pl"+idx2+".jpg", codulTarii);
				 
			}
			 if (r2 > .85   &&  ((rect2.height<350&& rect2.height>250) && (rect2.width > rect2.width-(rect2.width/3)))) {
				
				Rect roi2 = new Rect(Imgproc.boundingRect(plateContours.get(idx2)).x ,
						Imgproc.boundingRect(plateContours.get(idx2)).y,
						Imgproc.boundingRect(plateContours.get(idx2)).width ,
						Imgproc.boundingRect(plateContours.get(idx2)).height );
			
					cropCM = new Mat(bin, roi2);
					
					
					cropCM = removeBorders(cropCM);
					Imgproc.GaussianBlur(cropCM, cropCM, new Size(0, 0), 3);
					Core.addWeighted(cropCM, 9, cropCM, -6, 0, cropCM);
					
					Imgproc.threshold(cropCM, cropCM,0.0, 255.0, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
					
					Imgproc.GaussianBlur(cropCM, cropCM, new Size(0, 0), 3);
					Core.addWeighted(cropCM, 9, cropCM, -6, 0, cropCM);
					
					Imgproc.threshold(cropCM, cropCM,0.0, 255.0, Imgproc.ADAPTIVE_THRESH_MEAN_C| Imgproc.THRESH_OTSU);
					//^remove
					plateNumbers =  cropCM;
					Imgcodecs.imwrite("platenum"+idx2+".jpg", cropCM);
				
				
				
				 * Imgproc.rectangle(codulMasinii,
				 * Imgproc.boundingRect(plateContours.get(idx2)).tl(),
				 * Imgproc.boundingRect(plateContours.get(idx2)).br(), new
				 * Scalar(255, 255, 255),4, 8, 0);
				 * Imgcodecs.imwrite("nr"+idx2+".jpg", codulMasinii);
				 
					
			}

		}
	}

	*//**
	 * get plate contours,
	 * 
	 * @param sharpImg
	 *//*
	public void Step3GetPlateContours(Mat sharpImg) {
		Mat grad = new Mat();
		Imgproc.morphologyEx(sharpImg, grad, Imgproc.MORPH_GRADIENT, plateImg);//Imgproc.MORPH_CROSS
		bin = new Mat();
		Imgproc.threshold(sharpImg, bin, 0.0, 255.0, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
		Imgproc.medianBlur(bin, bin, 15);
		Mat plateConn = new Mat();
		plateKernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(9, 1));
		Imgproc.morphologyEx(bin, plateConn, Imgproc.MORPH_CLOSE, plateKernel);
		plateMask = Mat.zeros(bin.size(), CvType.CV_8UC1);
		plateContours = new ArrayList<MatOfPoint>();
		plateHierarchy = new MatOfFloat4();
		Imgproc.findContours(plateConn, plateContours, plateHierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE,
				new Point(0, 0));
		
	}
	
	public Mat defaultPreProcessing(Mat plate){
		Mat resizedImg = new Mat();
		Imgproc.resize(plate, resizedImg, new Size(origImg.cols() * 0.85, origImg.rows() * 0.26), 0, 0,
				Imgproc.INTER_LINEAR);
		Photo.fastNlMeansDenoising(resizedImg, resizedImg, 12, 21, 12);
		Mat sharp = new Mat();
		Imgproc.GaussianBlur(resizedImg, sharp, new Size(3, 3), 12);
		Core.addWeighted(sharp, 1.5, sharp, -0.7, 0, sharp);
		Photo.fastNlMeansDenoising(sharp, sharp, 12, 21, 12);
		 return sharp;
	}


	*//**
	 * Sharpen and very little denoiseing
	 * 
	 * @param resizedPlate
	 * @return sharpened Mat
	 *//*
	public Mat Step2DefaultSharpening(Mat resizedPlate) {
		Mat sharp = new Mat();
		Imgproc.GaussianBlur(resizedPlate, sharp, new Size(3, 3), 12);
		Core.addWeighted(sharp, 1.5, sharp, -0.7, 0, sharp);
		Photo.fastNlMeansDenoising(sharp, sharp, 12, 21, 12);
		return sharp;
	}

	
	*//**
	 * Resize plate Mat (% of orig img)
	 * 
	 * @param plate
	 * @param origImg
	 * @return
	 *//*
	public Mat Step1Resize(Mat plate) {
		Mat resizedImg = new Mat();
		Imgproc.resize(plate, resizedImg, new Size(origImg.cols() * 0.85, origImg.rows() * 0.26), 0, 0,
				Imgproc.INTER_LINEAR);
		Photo.fastNlMeansDenoising(resizedImg, resizedImg, 12, 21, 12);
		return resizedImg;
	}

	*//**
	 * SetPlate Mat
	 * 
	 * @param plateImg
	 *//*
	public void setPlateImg(Mat plateImg, Mat origImg) {
		this.plateImg = plateImg;
		this.origImg = origImg;
	}
	public void setPlateImg(Mat plateImg){
		this.plateImg = plateImg;
	}

	private Mat removeBorders(Mat m) {
		Mat img = m;
		Mat trash = new Mat();
		Imgproc.threshold(img, trash, 1, 255, Imgproc.THRESH_BINARY_INV);
		List<MatOfPoint> cons = new ArrayList<MatOfPoint>();
		MatOfFloat4 hie = new MatOfFloat4();

		Imgproc.findContours(trash, cons, hie, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0,0));//0.0
		Rect roi2 = null;

		for (int i = 0; i < cons.size(); i++) {
			roi2 = new Rect(Imgproc.boundingRect(cons.get(i)).x, Imgproc.boundingRect(cons.get(i)).y,
					Imgproc.boundingRect(cons.get(i)).width, Imgproc.boundingRect(cons.get(i)).height);

		}
		Mat crop = new Mat(img, roi2);
		
		return crop;

	}
	public void setOrigImg(Mat img){
		this.origImg = img;
	}
	public Mat getPlateNUmbers() {
		return this.plateNumbers;
	}

}
*/