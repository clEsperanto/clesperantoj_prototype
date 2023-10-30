
package net.clesperanto.presets;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(inherit = javacpp.class, value = {
		@Platform(compiler = { "cpp17" }, define = { "SHARED_PTR_NAMESPACE std" }, include = {
				"clesperantoj.hpp" }, linkpath = {"C:\\Program Files\\NVIDIA GPU Computing Toolkit\\CUDA\\v11.2\\lib\\x64"},
				link = { "JCLIc", "OpenCL", "cuda" })
}, target = "net.clesperanto.wrapper.clesperantoj")

public class clesperantoj implements InfoMapper {
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