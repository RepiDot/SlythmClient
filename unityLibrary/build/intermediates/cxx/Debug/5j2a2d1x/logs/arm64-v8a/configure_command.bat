@echo off
"G:\\AndroidSDK\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HC:\\Users\\yoohs\\AndroidStudioProjects\\SlythmClient\\unityLibrary\\src\\main\\cpp" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=23" ^
  "-DANDROID_PLATFORM=android-23" ^
  "-DANDROID_ABI=arm64-v8a" ^
  "-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a" ^
  "-DANDROID_NDK=G:\\Unity\\6000.0.24f1\\Editor\\Data\\PlaybackEngines\\AndroidPlayer\\NDK" ^
  "-DCMAKE_ANDROID_NDK=G:\\Unity\\6000.0.24f1\\Editor\\Data\\PlaybackEngines\\AndroidPlayer\\NDK" ^
  "-DCMAKE_TOOLCHAIN_FILE=G:\\Unity\\6000.0.24f1\\Editor\\Data\\PlaybackEngines\\AndroidPlayer\\NDK\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=G:\\AndroidSDK\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=C:\\Users\\yoohs\\AndroidStudioProjects\\SlythmClient\\unityLibrary\\build\\intermediates\\cxx\\Debug\\5j2a2d1x\\obj\\arm64-v8a" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=C:\\Users\\yoohs\\AndroidStudioProjects\\SlythmClient\\unityLibrary\\build\\intermediates\\cxx\\Debug\\5j2a2d1x\\obj\\arm64-v8a" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-DCMAKE_FIND_ROOT_PATH=C:\\Users\\yoohs\\AndroidStudioProjects\\SlythmClient\\unityLibrary\\.cxx\\Debug\\5j2a2d1x\\prefab\\arm64-v8a\\prefab" ^
  "-BC:\\Users\\yoohs\\AndroidStudioProjects\\SlythmClient\\unityLibrary\\.cxx\\Debug\\5j2a2d1x\\arm64-v8a" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared"