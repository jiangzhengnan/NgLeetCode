package com.ng.ngleetcode.model.splash

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.ng.base.BaseActivity
import com.ng.base.utils.MLog
import com.ng.ngbaselib.permission.PermissionResult
import com.ng.ngbaselib.permission.PermissionUtil
import com.ng.ngbaselib.permission.Permissions
import com.ng.ngleetcode.EmptyViewModel
import com.ng.ngleetcode.HomeActivity
import com.ng.ngleetcode.databinding.ActivitySplashBinding

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
                        onGetPermission()
                    }
                    // 进入设置界面申请权限
                    is PermissionResult.Rationale -> {
                        onRationPermission()
                    }
                    // 进入设置界面申请权限
                    is PermissionResult.Deny -> {
                        onDenyPermission()
                    }
                }
            }
        )
    }

    private fun onDenyPermission() {
        MLog.d("PermissionResult.Deny")
        PermissionUtil.showDialog(
            this, "申请权限",
            "没有相关权限应用将无法正常运行，点击确定进入权限设置界面来进行更改"
        ) { dialog, _ ->
            dialog.dismiss()
            finish()
        }
    }

    private fun onRationPermission() {
        MLog.d("PermissionResult.Rationale")
        PermissionUtil.showDialog(
            this, "考虑一下申请权限",
            "没有相关权限应用将无法正常运行，点击确定进入权限设置界面来进行更改"
        ) { dialog, _ ->
            dialog.dismiss()
            finish()
        }
    }

    private fun onGetPermission() {
        MLog.d("PermissionResult.Grant")
        startTo(HomeActivity::class.java)
        finish()
    }
}