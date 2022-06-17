project(clesperantoj)

include_directories(./include  ${OPENCL_INCLUDE_DIR} ${CLIC_INCLUDE_DIR}  ${OCLCL_INCLUDE_DIR})
link_directories(${CLIC_LIBRARY_DIR})

add_library(clesperantoj src/clesperantoj.cpp)
#target_link_libraries(clesperantoj CLIc_d ${OpenCL_LIBRARY})

install(TARGETS clesperantoj DESTINATION .)