# CMakeList to fetch and build the CLIc library and its dependencies (OpenCL) from source
#  - CLIc is a C++ library for OpenCL image processing
#  - it can be found at https://github.com/clEsperanto/CLIc_prototype.git
#  - we rely on the master branch
cmake_minimum_required(VERSION 3.20)

project(clesperanto)

message(STATUS "clesperantoj")

if (CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
  set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fPIC")
  set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fPIC")
endif()

set(CMAKE_CXX_STANDARD 17) # C++17...
set(CMAKE_CXX_STANDARD_REQUIRED ON) # Require (at least) it
set(CMAKE_CXX_EXTENSIONS OFF) # Don't use e.g. GNU extension (like -std=gnu++11) for portability

include(FetchContent)
option(BUILD_TESTING OFF)
option(BUILD_BENCHMARK OFF)
FetchContent_Declare(
  clic_lib
  GIT_REPOSITORY https://github.com/clEsperanto/CLIc_prototype.git
  GIT_TAG std-flag-compatibility
)
FetchContent_MakeAvailable(clic_lib)

add_library(clesperantoj SHARED ./src/clesperantoj.cpp)
add_library(clesperantoj::clesperantoj ALIAS clesperantoj)

target_include_directories(clesperantoj PUBLIC ./include)
target_link_libraries(clesperantoj PUBLIC CLIc::CLIc)

# target_compile_features(clesperantoj PUBLIC cxx_std_17)

install(TARGETS clesperantoj DESTINATION .)
