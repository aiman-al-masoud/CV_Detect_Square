package it.unipv.cv.edge_detection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Utility {

	/**
	 * To read the image
	 * @param fileName
	 * @return
	 */
	public static BufferedImage read(String fileName) {
		try {
			File f = new File(fileName);
			return ImageIO.read(f);
		} catch(IOException e) {
			System.err.println("Error in the file reading!");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	/**
	 * Starting from any (color) image, it creates a grayscale one
	 * @param weight (w) of the input image
	 * @param height (h) of the input image
	 * @param input 
	 * @return
	 */
	public static BufferedImage toGrayScale(BufferedImage input, int w, int h) {
		// creating output Image
		BufferedImage output = new BufferedImage(w,h,BufferedImage.TYPE_BYTE_GRAY);

		//convert to grayscale
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				int v = input.getRGB(x,y);

				int r = (v>>16)&255;
				int g = (v>>8)&255;
				int b = (v)&255;

				//calculate average or the common choise
				//int g = (r+g+b)/3;
				int avg = (int) (0.299*r + 0.587*g + 0.114*b);


				//replace RGB value with avg
				v = (avg<<16) | (avg<<8) | avg;

				output.setRGB(x, y, v);
			}
		}
		
		//write the new image
		writeImage(output);
		return output;
	}
	
	/**
	 * write image on the disk
	 * @param output
	 */
	private static void writeImage(BufferedImage output) {
		try{
		      File f = new File("./images/output/output1.png");
		      ImageIO.write(output, "jpg", f);
		    }catch(IOException e){
		    	System.err.println("Error in the file writing!");
				e.printStackTrace();
		    }
	}
}
