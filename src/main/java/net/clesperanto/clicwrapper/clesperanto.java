
package net.clesperanto.clicwrapper;

import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(value = { @Platform(
	includepath = {"C:\\structure\\code\\clesperantoj_prototype\\native\\includes\\core\\",
			"C:\\structure\\code\\CLIc_prototype\\thirdparty\\OpenCL-CLHPP\\include\\",
			"C:\\Program Files\\NVIDIA GPU Computing Toolkit\\CUDA\\v11.2\\include\\"},
	include = "clesperanto.hpp",
	linkpath = {"C:\\structure\\code\\clesperantoj_prototype\\native\\lib\\",
			"C:\\Program Files\\NVIDIA GPU Computing Toolkit\\CUDA\\v11.2\\lib\\x64\\"},
	link = {"CLIc", "OpenCL" })
//	@Platform(value = "windows-x86_64",
//		linkpath = {
//			"C:/Program Files/NVIDIA GPU Computing Toolkit/CUDA/v11.2/lib/x64/"
//		},
//		preloadpath = {
//			"C:/OpenCL/clFFT-2.12.2-Windows-x64/bin/" },
//			preload = { "clFFT" }),
//	@Platform(value = "linux-x86_64",
//			includepath = {"/usr/local/cuda-10.0/include/"},
//			linkpath = {
//			"/usr/local/cuda-10.0/lib64/" },
//			preload = { "clFFT" })
})
public class clesperanto {
	public void map(InfoMap infoMap) {
	}
}
