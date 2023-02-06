cmake_minimum_required(VERSION 3.20)

project(clesperantoj_test)

message(STATUS "TEST")

add_executable(clesperantoj_test clictest.cpp)

target_link_libraries(clesperantoj_test PUBLIC clesperantoj::clesperantoj)