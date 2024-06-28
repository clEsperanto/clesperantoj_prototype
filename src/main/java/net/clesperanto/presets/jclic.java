
package net.clesperanto.presets;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

//
// @Properties(inherit = javacpp.class, value = {
// 		@Platform(compiler = { "cpp17", "-framework OpenCL" }, define = { "SHARED_PTR_NAMESPACE std" }, include = {
// 				"clesperantoj.hpp" }, includepath = {
// 						"/usr/local/cuda-12.3/targets/x86_64-linux/include" }, linkpath = {
// 								"/usr/local/cuda-12.3/targets/x86_64-linux/lib" }, link = { "JCLIc", "OpenCL" })
// }, target = "net.clesperanto.wrapper.clesperantoj")

@Properties(inherit = javacpp.class, value = {
	    @Platform(
	        value = "windows",
	        include = "clesperantoj.hpp",
	        link = {"JCLIc", "Shell32"}
	    ),
	    @Platform(
	        value = {"linux", "macosx"},
	        include = "clesperantoj.hpp",
	        link = "JCLIc"
	    )},
	    target = "net.clesperanto._internals.jclic"
	)

public class jclic implements InfoMapper {
	static {
		Loader.load();
	}

	public void map(InfoMap infoMap) {

		infoMap.put(new Info("std::size_t").cast().valueTypes("long").pointerTypes("SizeTPointer"));

		infoMap.put(new Info("std::string").annotations("@StdString").valueTypes("String", "BytePointer")
				.pointerTypes("@Cast({\"char*\", \"std::string*\"}) BytePointer"))
				.put(new Info("std::vector<std::string>").pointerTypes("StringVector").define());

		infoMap.put(new Info("cle::Array", "cle::Device", "cle::BackendManager", "cle::Backend").skip());
	}
}
