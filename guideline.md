
# pre-requisites and installation

We will need the following tools installed and available in the path:
- [Git for Windows](https://git-scm.com/download/win)
- [cmake](https://cmake.org/download/)
- [java 11 JDK](https://zulu.org/download/)
- [maven](https://maven.apache.org/download.cgi)
- OpenCL, provided by your GPU card vendor
    - NVidia: [CUDA](https://developer.nvidia.com/cuda-downloads)
    - AMD: (todo)
    - Intel: (todo)
- C/C++ compiler
    - Windows: [Visual Studio](https://visualstudio.microsoft.com/downloads/) with C++ support
    - Linux: run the command `sudo apt install build-essential`
    - Mac: run the command `xcode-select --install`

Most of those software will propose to be added to the path during installation. If not, you will need to add them manually.

#### **_For windows:_**
To do so, open the "System" control panel, then "Advanced system settings", then "Environment Variables". In the "System variables" section, edit the "Path" variable and add the path to the executables.
E.g. for maven, you will need to add `C:\Program Files\apache-maven-3.6.0\bin` to the path.

#### **_For Linux:_**
Add the following lines to your `~/.bashrc` file:
```
export PATH=$PATH:/path/to/your/executable
```
E.g. for maven, you will need to add `export PATH=$PATH:/usr/share/maven/bin` to the path.

#### **_For Mac:_**
Add the following lines to your `~/.bash_profile` file:
```
export PATH=$PATH:/path/to/your/executable
```
E.g. for maven, you will need to add `export PATH=$PATH:/usr/local/Cellar/maven/3.6.0/bin` to the path.

# Build the project

Once all the pre-requisites are installed, and the repository cloned, you can build the project. You can do it by running the command `mvn` in the root folder of the project. This will build the project and run the tests.

#### **_For windows:_**
You will need to run the command in the `x64 Native Tools Command Prompt for VS 2022`. You can find it by searching for it in the start menu. You will also need to make sure that the command `bash` or `sh` is callable from the command prompt. If not, you will need to make it available by adding the `bin` directory of `git-sm` to your path. This is needed to run the `bash` scripts used to build the project.

# Project structure

The project is structured as follow:
- the native folder contains `CLIc`, the native code of the project in C/C++ and can be compiled using cmake. It also contains a set of `bash` scripts to build the project by calling CMakes. During compilation, the build folder of `CLIc` will be in the `cppbuild` folder of the project, and the final library will be in the `lib` folder in the root. The `lib` folder should contain the shared library `clesperantoj.dll` (windows) or `libclesperantoj.so` (linux) or `libclesperantoj.dylib` (mac), as well as static libraries `CLIc` and the includes of `CLIc`.
- the src folder contains both the generated source code from JavaCPP parsing the C++ headers of `clesprantoj` and the `java` source code of the project. The `gen` folder contains the source code generated from the headers of `clesprantoj`. The `main` folder contains the source code of the project, the wrapper and the new java library.
- the `pom.xml` file is the maven configuration file of the project. It contains the dependencies of the project, the build configuration, the plugins used to build the project, etc.

# Troubleshooting

* Take a look at the ```javacpp``` section of the poml.xml and make sure the ```includePath``` and ```linkPath``` locations exist on your machine.

* Sometimes we had to make a trivial change to [clesperantoj](https://github.com/clEsperanto/clesperantoj_prototype/blob/javacpp/src/main/java/net/clesperanto/clicwrapper/clesperantoj.java), like adding a space, to activate the parser that generated the java wrapper.)

* In some cases, the build fails when executed the first time.
You find a potential workaround explained [here](https://github.com/clEsperanto/clesperantoj_prototype/issues/4#issuecomment-1184768674).

* You may need to add the src/gen/java path to the java build path in your IDE.

  * For Eclipse follow below instructions

        1.  Go to Project -> Properties -> Java Build Path -> Source (tab). Then add src/main as a source folder.
        2. Right-clicking your project, then click Properties.
        3. In the left pane, click Java Build Path . In the right pane, select the Source tab.
        4. Here you can add/edit/remove source folders.
