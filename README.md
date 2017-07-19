# dualunityandroidlibrary
Demo Android application that loads independent Unity projects via their own Android library

**Unity version: 5.4.4f1**


## Build Steps

1. Open `Unity/UnityProjectA` in Unity and export as a Google Android project
2. Copy the exported `Unity Project A/assets/bin` to `Android/androidlibrarya/src/main/assets/bin`
3. Open `Unity/UnityProjectB` in Unity and build/install apk to device
4. Build `Android/unitylibrary` and copy `unitylibrary-debug.aar` to each of the following
   * `Android/androidlibrarya/libs`
   * `Android/androidlibraryb/libs`
   * `Android/androidapp/app/libs`
5. Build `Android/androidlibrarya` and copy `androidlibrarya-debug.aar` to `Android/androidapp/app/libs`
6. Build `Android/androidlibraryb` and copy `androidlibraryb-debug.aar` to `Android/androidapp/app/libs`
7. Build and run `Android/androidapp`