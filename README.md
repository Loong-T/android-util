# Android Util
Some simple reusable code for Android dev.

一些 Android 开发中可复用的简单代码。

## Usage

Firstly, add jitpack to root build.gradle.

```groovy
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

Then add libs which are needed.

```groovy
implementation 'in.nerd-is:lib-name:-lib-version'
```

## [Permission Util](https://github.com/Loong-T/android-util/tree/master/permission_util)
Helper for handling request single permission.

处理请求单个权限的帮助方法。

```groovy
implementation 'in.nerd-is:android-util:1.0.0'
```

