/*
 * Copyright 2019 Xuqiang ZHENG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

@file:Suppress("unused")

package `in`.nerd_is.libs.permission_util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * @author Xuqiang ZHENG on 18/6/17.
 */
fun Activity.isNotGranted(permission: String) = isNotGranted(this, permission)

fun Fragment.isNotGranted(permission: String) = isNotGranted(requireContext(), permission)

private fun isNotGranted(context: Context, permission: String) =
    ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED

fun AppCompatActivity.handlePermissionRequest(
    permission: String,
    rationalMessage: String,
    requestPermission: () -> Unit,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
    @StringRes positiveStrRes: Int = R.string.common_ok,
    @StringRes negativeStrRes: Int = R.string.common_cancel
) {
    if (isNotGranted(permission)) {
        if (shouldShowRequestPermissionRationale(this, permission)) {
            val dialog = createDialog(
                rationalMessage,
                positiveStrRes,
                negativeStrRes,
                requestPermission,
                onPermissionDenied
            )
            dialog.showNow(supportFragmentManager, null)
        } else {
            requestPermission()
        }
    } else {
        onPermissionGranted()
    }
}

fun Fragment.handlePermissionRequest(
    permission: String,
    rationalMessage: String,
    requestPermission: () -> Unit,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
    @StringRes positiveStrRes: Int = R.string.common_ok,
    @StringRes negativeStrRes: Int = R.string.common_cancel
) {
    if (isNotGranted(permission)) {
        if (shouldShowRequestPermissionRationale(permission)) {
            val dialog = createDialog(
                rationalMessage,
                positiveStrRes,
                negativeStrRes,
                requestPermission,
                onPermissionDenied
            )
            dialog.showNow(fragmentManager!!, null)
        } else {
            requestPermission()
        }
    } else {
        onPermissionGranted()
    }
}

private fun createDialog(
    rationalMessage: String,
    positiveStrRes: Int,
    negativeStrRes: Int,
    requestPermission: () -> Unit,
    permissionDenied: () -> Unit
): PermissionRationaleDialog {
    return PermissionRationaleDialog
        .new(rationalMessage, positiveStrRes, negativeStrRes)
        .apply {
            setPositiveClickListener {
                requestPermission()
            }
            setNegativeClickListener {
                permissionDenied()
            }
        }
}
