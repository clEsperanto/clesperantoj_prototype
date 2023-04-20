# CMakeList to fetch and build the CLIc library and its dependencies (OpenCL) from source
#  - CLIc is a C++ library for OpenCL image processing
#  - it can be found at https://github.com/clEsperanto/CLIc_prototype.git
#  - we rely on the master branch
cmake_minimum_required(VERSION 3.20)

project(JCLIc)

set(LIB_PACKAGE_NAME ${PROJECT_NAME} )

if (CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
  set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fPIC")
  set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fPIC")
endif()

set(CMAKE_CXX_STANDARD 17) # C++17...
set(CMAKE_CXX_STANDARD_REQUIRED ON) # Require (at least) it
set(CMAKE_CXX_EXTENSIONS OFF) # Don't use e.g. GNU extension (like -std=gnu++11) for portability
set(CMAKE_WINDOWS_EXPORT_ALL_SYMBOLS ON) # Export all symbols for shared library windows

include(FetchContent)
option(BUILD_TESTING OFF)
option(BUILD_BENCHMARK OFF)
FetchContent_Declare(
  clic_lib
  GIT_REPOSITORY https://github.com/clEsperanto/CLIc_prototype.git
  GIT_TAG 0.6.4
)
FetchContent_MakeAvailable(clic_lib)

file(GLOB_RECURSE WRAPPER_SOURCES_FILES ${CMAKE_CURRENT_SOURCE_DIR}/src/*.cpp)
file(GLOB_RECURSE WRAPPER_HEADERS_FILES ${CMAKE_CURRENT_SOURCE_DIR}/include/*.hpp)
set(WRAPPER_DIR ${CMAKE_CURRENT_SOURCE_DIR}/include)

add_library(${LIB_PACKAGE_NAME} SHARED ${WRAPPER_SOURCES_FILES})

target_link_libraries(${LIB_PACKAGE_NAME} PUBLIC CLIc::CLIc)

target_include_directories(${LIB_PACKAGE_NAME} PUBLIC "$<BUILD_INTERFACE:${WRAPPER_DIR}>")

install(TARGETS ${LIB_PACKAGE_NAME} DESTINATION .)

# if(APPLE)
#     set_target_properties(${LIB_PACKAGE_NAME} PROPERTIES INSTALL_RPATH "@loader_path/${CMAKE_INSTALL_LIBDIR}")
# else()
#     set_target_properties(${LIB_PACKAGE_NAME} PROPERTIES INSTALL_RPATH "$ORIGIN/${CMAKE_INSTALL_LIBDIR}")
# endif()
