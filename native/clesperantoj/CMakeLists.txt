# CMakeList to fetch and build the CLIc library and its dependencies (OpenCL) from source
#  - CLIc is a C++ library for OpenCL image processing
#  - it can be found at https://github.com/clEsperanto/CLIc_prototype.git
#  - we rely on the master branch

cmake_minimum_required(VERSION 3.20)

project(clesperanto)

if (CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
  set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fPIC")
  set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fPIC")
endif()

include(FetchContent)
option(BUILD_TESTING OFF)
option(BUILD_BENCHMARK OFF)
FetchContent_Declare(
  clic_lib
  GIT_REPOSITORY https://github.com/clEsperanto/CLIc_prototype.git
  GIT_TAG master
)
FetchContent_MakeAvailable(clic_lib)

add_library(clesperantoj ./src/clesperantoj.cpp)
target_include_directories(clesperantoj PUBLIC ./include)
target_link_libraries(clesperantoj PUBLIC CLIc::CLIc)

install(TARGETS clesperantoj DESTINATION .)
