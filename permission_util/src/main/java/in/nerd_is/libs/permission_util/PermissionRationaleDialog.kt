/*
 * Copyright 2018 Xuqiang ZHENG
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

package `in`.nerd_is.libs.permission_util

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment

/**
 * @author Xuqiang ZHENG on 18/5/22.
 */
class PermissionRationaleDialog : AppCompatDialogFragment() {

    private lateinit var positiveListener: () -> Unit
    private lateinit var negativeListener: () -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage(arguments!!.getString(ARG_MESSAGE))
            .setPositiveButton(arguments!!.getInt(ARG_POSITIVE_STRING_RES)) { _, _ ->
                if (::positiveListener.isInitialized) {
                    positiveListener.invoke()
                }
            }
            .setNegativeButton(arguments!!.getInt(ARG_NEGATIVE_STRING_RES)) { _, _ ->
                if (::negativeListener.isInitialized) {
                    negativeListener.invoke()
                }
            }
            .create()
    }

    fun setPositiveClickListener(listener: () -> Unit) {
        positiveListener = listener
    }

    fun setNegativeClickListener(listener: () -> Unit) {
        negativeListener = listener
    }

    interface Callback {
        fun onPositiveClicked()
        fun onNegativeClicked()
    }

    companion object {

        const val ARG_MESSAGE = "arg_message"
        const val ARG_POSITIVE_STRING_RES = "arg_positive_string_res"
        const val ARG_NEGATIVE_STRING_RES = "arg_negative_string_res"

        fun new(
            message: String,
            @StringRes positiveStrRes: Int,
            @StringRes negativeStrRes: Int
        ): PermissionRationaleDialog {
            val fragment = PermissionRationaleDialog()
            fragment.arguments = Bundle().apply {
                putString(ARG_MESSAGE, message)
                putInt(ARG_POSITIVE_STRING_RES, positiveStrRes)
                putInt(ARG_NEGATIVE_STRING_RES, negativeStrRes)
            }
            return fragment
        }
    }
}