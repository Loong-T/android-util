# Permission Util

![](https://jitpack.io/v/Loong-T/android-util.svg?style=flat-square)

Helper for handling request single permission.

处理请求单个权限的帮助方法。

## Usage

```groovy
implementation "in.nerd-is.android-util:permission-util:$version"
```

```kotlin
// in AppCompatActivity or Fragment
handlePermissionRequest(
    PERMISSION,
    "Your rationale message to request this permission", // 请求该权限的理由
    // code to request permission, something like below
    // 请求权限的代码，通常如下
    {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(PERMISSION),
            REQ_PERMISSION
        )
    },
    ::onPermissionGranted, // method called when permission is granted  获得权限时调用的方法
    ::onPermissionDenied // method called when permission is denied     拒绝权限时调用的方法
)
```

**Remember** to override `onRequestPermissionsResult` method to handle permission result.

**记得**重写 `onRequestPermissionsResult` 方法来处理权限请求结果。

```kotlin
override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == REQ_PERMISSION) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }
}
```