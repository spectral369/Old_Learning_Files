package plateOCR;
/*package functions;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.func.img.ImagePlate;
import com.func.img.ImgAPI;
import com.func.img.PlateToReadable;


public class Main {
	

	
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		ImagePlate i =  new ImagePlate();
		i.setStartImage(Imgcodecs.imread("car.jpg"));
		Mat h =i.findPlate();
		PlateToReadable p =  new PlateToReadable();
		p.setor(i.getOrig());
		p.setPlate(i.findPlate());
		p.processPlate();
		Imgcodecs.imwrite("finalcountryLetters.jpg",p.getcountryL());
		Imgcodecs.imwrite("finallicenseplate.jpg", p.getlicense());

	
		ImgAPI api =  new ImgAPI();
		try {
			api.setOriginalImage(ImageIO.read(new File("car.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		api.ProcessPlate();
		
		ReadPlate read =  new ReadPlate();
		String countryCode = read.defaultPlateReader(api.getCountryCode());
		String plateNumber =  read.defaultPlateReader(api.getLicenseNumbers());
		System.out.println(countryCode.trim() +" "+plateNumber.trim());
	}

}
*/