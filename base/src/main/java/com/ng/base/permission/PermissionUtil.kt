package com.ng.ngbaselib.permission

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.ng.base.R

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/19
 */
object PermissionUtil {


    fun showDialog(activity: Activity?, title: String?, message: String?,negativeListener: DialogInterface.OnClickListener?) {
        val dialog: AlertDialog? = createSingleMessage(activity, title, message,
                DialogInterface.OnClickListener { dialog, which ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    val uri = Uri.fromParts("package", activity?.packageName, null)
                    intent.data = uri
                    activity?.startActivity(intent)
                },
                negativeListener)
        dialog?.show()
    }

    fun createSingleMessage(context: Context?, title: String?, message: String?, positiveListener: DialogInterface.OnClickListener?, negativeListener: DialogInterface.OnClickListener?): AlertDialog? {
        val builder = AlertDialog.Builder(context!!, R.style.CommonDialogStyle)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(R.string.dialog_cancel, negativeListener)
        builder.setPositiveButton(R.string.dialog_ok, positiveListener)
        return builder.create()
    }

    interface PermissionMoreStatusListener {
        fun granted()
        fun refuse()
    }



}