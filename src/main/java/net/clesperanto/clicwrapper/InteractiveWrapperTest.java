package net.clesperanto.clicwrapper;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.ShortPointer;
import org.jocl.NativePointerObject;

import net.clesperanto.clicwrapper.clesperantojWrapper.ClesperantoJ;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.coremem.enums.NativeTypeEnum;
import net.haesleinhuepf.clij2.CLIJ2;
import net.clesperanto.clicwrapper.ConvertersUtility;
import net.imagej.Dataset;
import net.imagej.ImageJ;
import net.imglib2.Dimensions;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.real.FloatType;

public class InteractiveWrapperTest {
	
	final static ImageJ ij = new ImageJ();

	public static void main(String[] args) throws Exception {
		System.out.println("Test clesperantoj wrapper");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		ij.launch(args);
		
		ClesperantoJ test = new ClesperantoJ();
		
		test.sayHello();
		
		// load the dataset
		Dataset dataset = (Dataset) ij.io().open("./imgs/boats.tif");

		Img<FloatType> img = ij.op().convert().float32((Img)dataset);
		Img<ShortType> imgShort = ij.op().convert().int16((Img)dataset);
		
		ij.ui().show(img);
	
		// convert images to Float/ShortPointers and create outputs
		FloatPointer fp = ConvertersUtility.ii2DToFloatPointer(img);
		FloatPointer out = new FloatPointer(img.dimension(0)*img.dimension(1));
		
		ShortPointer fpShort = ConvertersUtility.ii2DToShortPointer(imgShort);
		ShortPointer outShort = new ShortPointer(img.dimension(0)*img.dimension(1));
		
		// test templated version of wrapper
		test.FloatGaussianBlur2dT(fp, out, (int)img.dimension(0), (int)img.dimension(1), 3.f, 3.f);
		//test.ShortGaussianBlur2dT(fpShort, outShort, (int)img.dimension(0), (int)img.dimension(1), 3.f, 3.f);
		
		// convert outputs back to Img so we can display them
		
		Img outIm=ConvertersUtility.floatPointerToImg2D(out, img);
		//Img outImShort=ConvertersUtility.shortPointerToImg2D(outShort, img);
		
		ij.ui().show(outIm);
		//ij.ui().show(outImShort);
		
		
		///////////////// long pointer test
		
		CLIJ2 clij2=null;
		// get clij
		try {
			clij2 = CLIJ2.getInstance("RTX");
		}
		catch(Exception e) {
			System.out.println(e);
			return;
		}
		
		ClearCLBuffer gpuIn = clij2.push(img);
		ClearCLBuffer gpuOut= clij2.create(gpuIn.getDimensions(), NativeTypeEnum.Float);
		
		// Get the CL Buffers, context, queue and device as long native pointers
		long longPointerIn = ((NativePointerObject) (gpuIn.getPeerPointer()
				.getPointer())).getNativePointer();
		long longPointerOut = ((NativePointerObject) (gpuOut.getPeerPointer()
				.getPointer())).getNativePointer();
		
		test.guassianBlur2dLongLong(longPointerIn, longPointerOut, (int)(img.dimension(0)), (int)(img.dimension(1)), 3.f, 3.f);
		
		clij2.show(gpuOut, "from CLIJ2 long pointer");
		
	}

}
