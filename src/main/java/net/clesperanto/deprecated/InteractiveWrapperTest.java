package net.clesperanto.deprecated;

import org.bytedeco.javacpp.FloatPointer;

import net.clesperanto.clicwrapper.clesperantojWrapper.ClesperantoJInternal;
import net.clesperanto.clicwrapper.clesperantojWrapper.ObjectJ;
import net.imagej.Dataset;
import net.imagej.ImageJ;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.real.FloatType;

public class InteractiveWrapperTest {
	
	final static ImageJ ij = new ImageJ();

	public static void main(String[] args) throws Exception {
		
		System.out.println("Test clesperantoj wrapper");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));

		ij.launch(args);

		ClesperantoJInternal clesperantoJ = new ClesperantoJInternal();

		// sanity test
		clesperantoJ.sayHello();
		
		// load the test dataset
		Dataset dataset = (Dataset) ij.io().open("./imgs/boats.tif");

		Img<FloatType> img = ij.op().convert().float32((Img)dataset);
		Img<ShortType> imgShort = ij.op().convert().int16((Img)dataset);
		
		ij.ui().show("img as float", img);
		ij.ui().show("img as short", img);
		
		int nx = (int)img.dimension(0);
		int ny = (int)img.dimension(1);
	
		// convert images to FloatPointers and create outputs
		FloatPointer fp = ConvertersUtility.ii2DToFloatPointer(img);
		FloatPointer outFp = new FloatPointer(img.dimension(0)*img.dimension(1));
				
		// push the input 
		ObjectJ objIn = clesperantoJ.FloatPush(fp, nx, ny, 1);
		// create GPU memory for output
		ObjectJ objOut = clesperantoJ.FloatCreate(nx, ny, 1);
		
		// call Gaussian blur
		clesperantoJ.gaussian_blur(objIn, objOut, 10.f, 10.f, 10.f);
		
		// pull from GPU
		clesperantoJ.FloatPull(outFp, objOut);
		
		Img outImFromObj=ConvertersUtility.floatPointerToImg2D(outFp, img);
		ij.ui().show("float result from obj approach", outImFromObj);

		// below is the short version of the above test, commented out for now 
		// as there is an issue with pushing shorts
		/*
		
		ShortPointer sp = ConvertersUtility.ii2DToShortPointer(imgShort);
		
		objIn = clesperantoJ.ShortPush(sp, nx, ny);
		objOut = clesperantoJ.ShortCreate(nx, ny);
		
		clesperantoJ.gaussianBlur2d(objIn, objOut, 10.f, 10.f);
		
		ShortPointer outSp = new ShortPointer(nx*ny);
		clesperantoJ.ShortPull(outSp, objOut);
		
		Img outImFromObjShort=ConvertersUtility.shortPointerToImg2D(outSp, img);
		ij.ui().show("short result from obj approach", outImFromObjShort);
		*/
		
		
		// test templated version of wrapper
		//clesperantoJ.FloatGaussianBlur2dT(fp, outFp, (int)img.dimension(0), (int)img.dimension(1), 3.f, 3.f);
		//test.ShortGaussianBlur2dT(fpShort, outShort, (int)img.dimension(0), (int)img.dimension(1), 3.f, 3.f);
		
		// convert outputs back to Img so we can display them
		
		//Img outIm=ConvertersUtility.floatPointerToImg2D(outFp, img);
		//Img outImShort=ConvertersUtility.shortPointerToImg2D(outShort, img);
		
		//ij.ui().show(outIm);
		//ij.ui().show(outImShort);

	}

}
