
package net.clesperanto.clicwrapper;

import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

// java cpp configuration class that will enable the parser to create a wrapper class in src/gen/java
// see https://github.com/bytedeco/javacpp/wiki/Mapping-Recipes
@Properties(value = { @Platform(
	// note: comment this in to debug
	compiler = {"fastfpu","debug"},
	// header file for c++ class we want to wrap
	include = "clesperantoj.h",
	// libraries we need to link to
	link = {"clesperantoj", "CLIc_d", "OpenCL" }),
	// os specific properties (note relative include and library paths are specified in the pom)	
	@Platform(value = "windows-x86_64",
		includepath = {
			"C:/Program Files/NVIDIA GPU Computing Toolkit/CUDA/v11.2/include/"},	 
		linkpath = {
			"C:/Program Files/NVIDIA GPU Computing Toolkit/CUDA/v11.2/lib/x64/"
		}),
//	@Platform(value = "linux-x86_64",
//			includepath = {"/usr/local/cuda-10.0/include/"},
//			linkpath = {
//			"/usr/local/cuda-10.0/lib64/" },
//			preload = { "clFFT" }) 
},
// name of java wrapper class that will be generated (look in src/gen/java to find it) 
target = "net.clesperanto.clicwrapper.clesperantojWrapper" 
)
public class clesperantoj implements InfoMapper {
	public void map(InfoMap infoMap) {
		
		// the parser will try to automatically make wrappers for all functions in cleperantoj.h
		// however templates need to be handled specially, and we need to set properties in the info map
		// that specify the name of the wrapper to each template type we want to generate
		infoMap.put(new Info("ClesperantoJ::guassianBlur2dT<float>").javaNames("FloatGaussianBlur2dT"));
		infoMap.put(new Info("ClesperantoJ::guassianBlur2dT<short>").javaNames("ShortGaussianBlur2dT"));
		infoMap.put(new Info("ClesperantoJ::create<float>").javaNames("FloatCreate"));
		infoMap.put(new Info("ClesperantoJ::create<short>").javaNames("ShortCreate"));
		infoMap.put(new Info("ClesperantoJ::push<float>").javaNames("FloatPush"));
		infoMap.put(new Info("ClesperantoJ::push<short>").javaNames("ShortPush"));
		infoMap.put(new Info("ClesperantoJ::pull<float>").javaNames("FloatPull"));
		infoMap.put(new Info("ClesperantoJ::pull<short>").javaNames("ShortPull"));
	}
}
