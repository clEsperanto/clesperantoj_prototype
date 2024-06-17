## fetch CLIc from repo release
include(FetchContent)

FetchContent_Declare(
  ocl_headers
  GIT_REPOSITORY https://github.com/KhronosGroup/OpenCL-Headers.git
  GIT_TAG v2024.05.08
)
FetchContent_MakeAvailable(ocl_headers)

option(BUILD_SHARED_LIBS OFF)
option(BUILD_TESTING OFF)
option(BUILD_BENCHMARK OFF)

FetchContent_Declare(
  CLIc
  GIT_REPOSITORY ${CLIC_REPO_URL}
  GIT_TAG ${CLIC_REPO_TAG}
)
FetchContent_MakeAvailable(CLIc)
