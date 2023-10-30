## fetch CLIc from repo release
include(FetchContent)

option(BUILD_SHARED_LIBS OFF)
option(BUILD_TESTING OFF)
option(BUILD_BENCHMARK OFF)

FetchContent_Declare(
  CLIc
  GIT_REPOSITORY ${CLIC_REPO_URL}
  GIT_TAG ${CLIC_REPO_TAG}
)
FetchContent_MakeAvailable(CLIc)
