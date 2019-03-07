package plateOCR;
/*package com.func.img;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImgAPI {
	ImagePlate plate =null;
	PlateToReadable read= null;
	Mat plateImg = null;
	public ImgAPI() {
		// TODO Auto-generated constructor stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		plate =  new ImagePlate();
		read =  new PlateToReadable();
	}
	public BufferedImage getLicenseNumbers(){
		
		BufferedImage img = new BufferedImage(read.ste.width(), read.getlicense().height(), BufferedImage.TYPE_BYTE_GRAY);
		byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		read.getlicense().get(0, 0, data);
	
		return img;
	}
	
	public BufferedImage getCountryCode(){
		//Imgcodecs.imwrite("before.jpg",read.getcountryL());
		//chestii ciudate se intampla cand transformam mat->to->bufferedImage
		BufferedImage img = null ;
		img = matToBufferedImage(read.getCountryCode(), img);
		File f=  new File("after.jpg");
		try {
			ImageIO.write(img, "jpg", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	
	public void ProcessPlate(){
		read.setOrigImg(plate.getOrig());	
		read.setPlate(plate);
		read.defaultPreProcessing(plateImg);
		
		
	}
	
	public void setPlateReadArgs(BufferedImage originalImg,BufferedImage plate){
		read.setOrigImg(bufferedImageToMat(originalImg));
		read.setPlate(bufferedImageToMat2(plate));
	}
	
	public BufferedImage getPlate(){
		if(plateImg == null){
			plateImg = new Mat();
			plateImg =plate.findPlate();
		}
		BufferedImage img = new BufferedImage(plateImg.width(), plateImg.height(), BufferedImage.TYPE_INT_BGR);
		byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		plateImg.get(0, 0, data);
		return img;
	}
	public void findPlate(){
		plateImg = new Mat();
		plateImg =plate.findPlate();
	}
	
	
	public void setOriginalImage(BufferedImage img){
		if(!plate.getOrig().empty())
			System.out.println("Original Image changed ");
		plate.setOrigImg(bufferedImageToMat(img));
	}
	public BufferedImage getOriginalImage(){
		  
		BufferedImage img = new BufferedImage(plate.getOrig().width(), plate.getOrig().height(), BufferedImage.TYPE_INT_RGB);
		byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		plate.getOrig().get(0, 0, data);
		return img;
	}
	
	
	private Mat bufferedImageToMat2(BufferedImage bi) {
		BufferedImage image  = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_BGR);
	    image.getGraphics().drawImage(bi, 0, 0, null);
		System.out.println(image.getType());
		File outputfile = new File("buf.jpg");
		try {
			ImageIO.write(image, "jpg", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC4);
		mat.put(0, 0, data);
		Imgcodecs.imwrite("intomat.jpg", mat);
		return mat;
		}
	
	
	private Mat bufferedImageToMat(BufferedImage bi) {
		BufferedImage image = bi;
		byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		mat.put(0, 0, data);
		return mat;
		}
	
	

private  BufferedImage matToBufferedImage(Mat matrix, BufferedImage bimg)
{
    if ( matrix != null ) { 
    	//necesar pentru ca....
    	matrix = matrix.colRange(matrix.cols() - matrix.cols(), matrix.cols());
        int cols = matrix.cols();  
        int rows = matrix.rows();  
        int elemSize = (int)matrix.elemSize();  
        byte[] data = new byte[cols * rows * elemSize];  
        int type;  
        matrix.get(0, 0, data);  
        switch (matrix.channels()) {  
        case 1:  
            type = BufferedImage.TYPE_BYTE_GRAY;  
            break;  
        case 3:  
            type = BufferedImage.TYPE_3BYTE_BGR;  
            // bgr to rgb  
            byte b;  
            for(int i=0; i<data.length; i=i+3) {  
                b = data[i];  
                data[i] = data[i+2];  
                data[i+2] = b;  
            }  
            break;  
        default:  
            return null;  
        }  

        // Reuse existing BufferedImage if possible
        if (bimg == null || bimg.getWidth() != cols || bimg.getHeight() != rows || bimg.getType() != type) {
            bimg = new BufferedImage(cols, rows, type);
        }        
        bimg.getRaster().setDataElements(0, 0, cols, rows, data);
    } else { // mat was null
        bimg = null;
    }
    return bimg;  
}   
	

}
*/