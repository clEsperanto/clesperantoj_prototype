

## How to build

Install Microsoft Build tools as explained [here](https://biapol.github.io/blog/robert_haase/ms_build_tools/). Hint: activate Windows C++ development.

Add the folder where "cl.exe" lives to the PATH, e.g. this one:

```
C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\VC\Tools\MSVC\14.29.30133\bin\Hostx64\x64
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

Also install the Windows Subsystem for Linux as explained [here](https://www.groovypost.com/howto/install-and-start-bash-in-windows-10-anniversary-update/)
and choose [Ubuntu](https://www.microsoft.com/store/productId/9PDXGNCFSCZV) as distribution in the [Windows store](https://aka.ms/wslstore).
After Ubuntu installation is done in the Windows Store, click on "Open" to finish the installation.

Open the x64 Native Tools Command Prompt for VS 2019 (64 bit!), navigate to the root directory of this project and run

```
mvn
```

## SWIG Links
http://www.swig.org/tutorial.html
https://valelab4.ucsf.edu/svn/3rdpartypublic/swig/Doc/Manual/Java.html