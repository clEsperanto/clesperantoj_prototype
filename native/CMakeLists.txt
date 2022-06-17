add_subdirectory(test)
add_subdirectory(clesperantoj)

find_package(OpenCL)

# find CLIC path
FIND_PATH( OPENCL_INCLUDE_DIR $ENV{OPENCL_INCLUDE_DIR} clic DOC "OPENCL include path")
FIND_PATH( CLIC_INCLUDE_DIR $ENV{CLIC_INCLUDE_DIR} clic DOC "CLIC include path")
FIND_PATH( CLIC_LIBRARY_DIR $ENV{CLIC_LIBRARY_DIR} clic DOC "CLIC library path")
FIND_PATH( OCLCL_INCLUDE_DIR $ENV{OCLCL_INCLUDE_DIR} oclcl DOC "OCLCL include path")

message("opencl link is ${OpenCL_LIBRARY}")
message("include dir is ${CLIC_INCLUDE_DIR}")
message("ocl include dir is ${OCLCL_INCLUDE_DIR}")
