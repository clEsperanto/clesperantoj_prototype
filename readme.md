

## How to build on Windows

Install Microsoft Build tools as explained [here](https://biapol.github.io/blog/robert_haase/ms_build_tools/). Hint: activate Windows C++ development.
Install [git-scm](https://git-scm.com/downloads) and add it to the PATH so that `bash` can be called from any command line.

Add the folder where "cl.exe" lives to the PATH, e.g. this one:

```
C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\VC\Tools\MSVC\14.29.30133\bin\Hostx64\x64
```

Follow the instructions to build [CLIc](https://github.com/clEsperanto/CLIc_prototype/blob/master/docs/windows_build/windows_build.md). 
Make sure when setting up CMAKE, that it is built into this directory as explained [here](https://github.com/clEsperanto/CLIc_prototype/blob/master/docs/windows_build/windows_build.md).

```
../CLIc_prototype/build/
```

Set the environment variable JAVA_HOME to a valid SDK, e.g.:

```
setx JAVA_HOME C:\Users\rober\.jdks\azul-11.0.14.1\
echo %JAVA_HOME%
```

Download apache-maven, unzip it and make it part of PATH:

```
C:\programs\apache-maven-3.8.4-bin\apache-maven-3.8.4\bin
```

Open the x64 Native Tools Command Prompt for VS 2019 (64 bit!), navigate to the root directory of this project and run

```
mvn
```

## SWIG Links
http://www.swig.org/tutorial.html
https://valelab4.ucsf.edu/svn/3rdpartypublic/swig/Doc/Manual/Java.html