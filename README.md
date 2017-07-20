# dualunityandroidlibrary
Demo Android application that loads independent Unity projects via their own Android library

**Unity version: 5.4.4f1**


## Build Steps

1. Build `Android/unitylibrary` in Android Studio and copy `unitylibrary-debug.aar` to each of the following
   * `Android/androidlibrarya/libs`
   * `Android/androidlibraryb/libs`
   * `Android/androidapp/app/libs`
2. Build `Android/androidlibrarya` in Android Studio and copy `androidlibrarya-debug.aar` to `Android/androidapp/app/libs`
3. Build `Android/androidlibraryb` in Android Studio and copy `androidlibraryb-debug.aar` to `Android/androidapp/app/libs`
4. Build and install `Android/androidapp`
5. Open `Unity/UnityProjectA` in Unity and build as `UnityProjectA.apk`
6. Open `Unity/UnityProjectB` in Unity and build as `UnityProjectB.apk`
7. Copy both Unity apk's to `/storage/[YOUR_VOLUME]/Android/data/com.sk.androidapp/files`
8. Run `Android/androidapp` (app name is “Dual Unity Libraries”)