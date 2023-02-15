
package net.clesperanto.clicwrapper;

import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

// java cpp configuration class that will enable the parser to create a wrapper class in src/gen/java
// see https://github.com/bytedeco/javacpp/wiki/Mapping-Recipes
@Properties(value = { @Platform(
		// note: comment this in to debug
		compiler = { "cpp17" }, // removed "fastfpu", don't know what it does
		// header file for c++ class we want to wrap
		include = "clesperantoj.hpp",
		// libraries we need to link to
		link = { "clesperantoj", "lib/CLIc", "C:/Program Files/NVIDIA GPU Computing Toolkit/CUDA/v11.2/lib/x64/OpenCL" }) // Addin "CLIc" should not be necessary as it is
									// included in clesperantoj
		// os specific properties (note relative include and library paths are specified
		// in the pom)
		// @Platform(value = "windows-x86_64", includepath = {
		// "C:/Program Files/NVIDIA GPU Computing Toolkit/CUDA/v11.2/include/" },
		// linkpath = {
		// "C:/Program Files/NVIDIA GPU Computing Toolkit/CUDA/v11.2/lib/x64/"
		// }),
		// @Platform(value = "linux-x86_64"
		// includepath = {"/usr/local/cuda-10.0/include/"},
		// linkpath = {
		// "/usr/lib/x86_64-linux-gnu/" }
		// preload = { "clFFT" }
		// )
},
		// name of java wrapper class that will be generated (look in src/gen/java to
		// find it)
		target = "net.clesperanto.clicwrapper.clesperantojWrapper")

public class clesperantoj implements InfoMapper {
	public void map(InfoMap infoMap) {

		// the parser will try to automatically make wrappers for all functions in
		// cleperantoj.h
		// however templates need to be handled specially, and we need to set properties
		// in the info map
		// that specify the name of the wrapper to each template type we want to
		// generate
		// infoMap.put(new
		// Info("ClesperantoJInternal::guassianBlur2dT<float>").javaNames("FloatGaussianBlur2dT"));
		// infoMap.put(new
		// Info("ClesperantoJInternal::guassianBlur2dT<short>").javaNames("ShortGaussianBlur2dT"));
		// infoMap.put(new
		// Info("ClesperantoJInternal::create<char>").javaNames("CharCreate"));
		// infoMap.put(new
		// Info("ClesperantoJInternal::create<float>").javaNames("FloatCreate"));
		// infoMap.put(new
		// Info("ClesperantoJInternal::create<short>").javaNames("ShortCreate"));
		// infoMap.put(new
		// Info("ClesperantoJInternal::push<char>").javaNames("CharPush"));
		// infoMap.put(new
		// Info("ClesperantoJInternal::push<float>").javaNames("FloatPush"));
		// infoMap.put(new
		// Info("ClesperantoJInternal::push<short>").javaNames("ShortPush"));
		// infoMap.put(new
		// Info("ClesperantoJInternal::pull<char>").javaNames("CharPull"));
		// infoMap.put(new
		// Info("ClesperantoJInternal::pull<float>").javaNames("FloatPull"));
		// infoMap.put(new
		// Info("ClesperantoJInternal::pull<short>").javaNames("ShortPull"));
	}
}
