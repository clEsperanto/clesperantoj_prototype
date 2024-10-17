[![Build Status](https://github.com/clEsperanto/clesperantoj_prototype/actions/workflows/build.yml/badge.svg)](https://github.com/clEsperanto/clesperantoj_prototype/actions/workflows/build.yml)



# clEsperantoJ

clEsperantoJ is the Java package of [clEsperanto] - a multi-language framework for GPU-accelerated image processing. It relies on a familly of [OpenCL kernels] originated from [CLIJ]. This package is developped in Java and C++ wrapped using JavaCPP, and uses the C++ [CLIc] library as a processing backend.

## How to Build from source

Please note this project is in early stages.
It is expected that this repository, its build instruction, as well as the global design code may change over time.

#### pre-requisites

clesperantoJ is a Java bidings of the C++ library [CLIc]() relying of [OpenCL]() for computation.
A certain diversity of ressources will be required to build clesperantoJ from source:
- a C++ compiler (`gcc`, `clang`, `cl`, etc.)
- the Java Development Kit (JDK)
- [CMake](https://cmake.org/) (a C++ project manager)
- [Maven](https://maven.apache.org/download.cgi) (a Java project manager)
- Bash (a shell program and command language)
- OpenCL (your GPU driver properly install with their Development Kit if possible)

#### Install a C++ compiler and CMake

For __windows__, you can install [Microsoft Build tools](https://visualstudio.microsoft.com/visual-cpp-build-tools/) (or [Visual Studio Community edition](https://visualstudio.microsoft.com/vs/community/) with "Desktop development with C++" selected). A more detail tutorial can be found [here](https://biapol.github.io/blog/robert_haase/ms_build_tools/readme.html).
For __MacOS__, you will need to install the command-line tools from xcode by running this command in a prompt: `xcode-select --install`.
For __Unix__, similarly to OSX, you will need to install the `build-essential` package by running this command in a prompt: `sudo apt install build-essential`.

CMake can be install by downloading it from its official website or using package managed like brew or apt for MacOS and Ubuntu.
You will need to install cmake with its CLI (Command Line Interface).
You can test if installed by calling the command `cmake --version` in a command prompt.

#### Java Development Kit and Maven

You can download and install a Java-Development-Kit (JDK), preferably version 11, from [azul](https://www.azul.com/downloads/?package=jdk#download-openjdk).
Follow the installation instruction for you OS, and define the `JAVA_HOME` system variable to your Java JDK install folder, as well as add the JDK folder to your system `PATH` variable.

Similar procedure for Maven, you can download [apache-maven](https://maven.apache.org/download.cgi) and install it where you see fit. Add Maven to your system `PATH`, similarly to the JAVA JDK.

You can test both installation by calling respectivaly `java --version` and `mvn --version` in your command prompt.

### Bash

Already provided in MacOS and Unix, this section only concern Windows user.
You can get Bash by simply installing [git-scm](https://git-scm.com/downloads) and add it to you `PATH`.

### Install OpenCL

You mainly need to install your GPU driver provided by your GPU vendor, e.g. NVIDIA, AMD, Intel, etc. Please refer to their official website.
For NVIDIA, we advise to install the [CUDAToolKit](https://developer.nvidia.com/cuda-toolkit).

## PATH

To make the above installed programs work, you need to add them to your path, e.g. on Windows like this:
```
C:\programs\apache-maven-3.9.9\bin
C:\Program Files\Git\bin
C:\Program Files\CMake\bin
```

### Build clesperantoJ

In a commmand prompt (on Windows "x64 Native Tools Command Prompt"), start by git clone the repository and move inside the repo
```
git clone https://github.com/clEsperanto/clesperantoj_prototype.git
cd clesperantoj_prototype
```
You can then use Maven to build the project
```
mvn
```
This should start the build of the source and generate the various `jar` and classes in the `target` folder.

WARNING: for windows users, you may need to rely on the x64 Native Tools Command Prompt for VS 2019 (64 bit!) to run the build.

## Acknowledgements
This project was supported by the Deutsche Forschungsgemeinschaft under Germany’s Excellence Strategy – EXC2068 - Cluster of Excellence "Physics of Life" of TU Dresden.
This project has been made possible in part by grant number [2021-237734 (GPU-accelerating Fiji and friends using distributed CLIJ, NEUBIAS-style, EOSS4)](https://chanzuckerberg.com/eoss/proposals/gpu-accelerating-fiji-and-friends-using-distributed-clij-neubias-style/) from the Chan Zuckerberg Initiative DAF, an advised fund of the Silicon Valley Community Foundation.


[clEsperanto]: http://clesperanto.net/
[OpenCL kernels]: https://github.com/clEsperanto/clij-opencl-kernels/tree/clesperanto_kernels
[CLIJ]: http://clij.github.io/
[CLIc]: https://github.com/clEsperanto/CLIc
[community guidelines]: https://clij.github.io/clij2-docs/community_guidelines
[github issue]: https://github.com/clEsperanto/pyclesperanto/issues
[image.sc forum]: https://forum.image.sc/
