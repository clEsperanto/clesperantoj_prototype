project(test)

FIND_PACKAGE( OpenCV REQUIRED PATHS ${OPENCV_INSTALL_DIR})

include_directories(${OPENCL_INCLUDE_DIR} ${CLIC_INCLUDE_DIR} ${OpenCV_INCLUDE_DIRS} ${OCLCL_INCLUDE_DIR})
link_directories(${CLIC_LIBRARY_DIR})

add_executable(clictest clictest.cpp)

target_link_libraries(clictest CLIc_d ${OpenCL_LIBRARY} ${OpenCV_LIBS})