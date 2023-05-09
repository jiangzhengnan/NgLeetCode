package com.ng.ngleetcode.splash

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.ng.base.utils.MLog
import com.ng.ngbaselib.BaseActivity
import com.ng.ngbaselib.permission.PermissionResult
import com.ng.ngbaselib.permission.PermissionUtil
import com.ng.ngbaselib.permission.Permissions
import com.ng.ngleetcode.EmptyViewModel
import com.ng.ngleetcode.NewHomeActivity
import com.ng.ngleetcode.databinding.ActivitySplashBinding

/**
 * 描述:启动图，申请权限
 * @author Jzn
 *
 * @date 2020/6/29
 */
class SplashActivity : BaseActivity<EmptyViewModel, ActivitySplashBinding>() {

    //需要申请的权限列表
    private val mPermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )



    override fun initView(savedInstanceState: Bundle?) {
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
    }

    override fun initData() {
        getPermissions(mPermissions)
    }

    private fun getPermissions(permissions: Array<String>) {
        Permissions(this).requestArray(permissions).observe(
            this, Observer {
                when (it) {
                    is PermissionResult.Grant -> {
                        MLog.d("PermissionResult.Grant")
                        startTo(NewHomeActivity::class.java)
                        finish()
                    }
                    // 进入设置界面申请权限
                    is PermissionResult.Rationale -> {
                        MLog.d("PermissionResult.Rationale")
                        PermissionUtil.showDialog(this, "考虑一下申请权限",
                            "该权限是用来干嘛的，没有它会巴拉巴拉，点击确定进入权限设置界面进行更高",
                            DialogInterface.OnClickListener { dialog, _ ->
                                dialog.dismiss()
                                finish()
                            }
                        )
                    }
                    // 进入设置界面申请权限
                    is PermissionResult.Deny -> {
                        MLog.d("PermissionResult.Deny")
                        PermissionUtil.showDialog(this, "申请权限",
                            "没有相关权限应用将无法正常运行，点击确定进入权限设置界面来进行更改",
                            DialogInterface.OnClickListener { dialog, _ ->
                                dialog.dismiss()
                                finish()
                            }
                        )
                    }
                }
            }
        )
    }

    override fun onRetryBtnClick() {

    }

}