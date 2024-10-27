@echo off
"G:\\Android Studio\\jbr\\bin\\java" ^
  --class-path ^
  "C:\\Users\\yoohs\\.gradle\\caches\\modules-2\\files-2.1\\com.google.prefab\\cli\\2.0.0\\f2702b5ca13df54e3ca92f29d6b403fb6285d8df\\cli-2.0.0-all.jar" ^
  com.google.prefab.cli.AppKt ^
  --build-system ^
  cmake ^
  --platform ^
  android ^
  --abi ^
  arm64-v8a ^
  --os-version ^
  23 ^
  --stl ^
  c++_shared ^
  --ndk-version ^
  23 ^
  --output ^
  "C:\\Users\\yoohs\\AppData\\Local\\Temp\\agp-prefab-staging11841448949794416061\\staged-cli-output" ^
  "C:\\Users\\yoohs\\.gradle\\caches\\transforms-3\\f5b54108e41ed18a41118f3f089d901e\\transformed\\jetified-games-activity-3.0.5\\prefab" ^
  "C:\\Users\\yoohs\\.gradle\\caches\\transforms-3\\8eda44ec18d45f5c821ec65e2db721d3\\transformed\\jetified-games-frame-pacing-1.10.0\\prefab"
