package com.ng.ngleetcode

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ng.base.utils.DialogUtils
import com.ng.ngbaselib.BaseActivity
import com.ng.ngbaselib.ViewModelFactory
import com.ng.ngleetcode.databinding.ActivitySplashBinding
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


/**
 * 开屏页
 */
class SplashActivity : BaseActivity(),
    EasyPermissions.PermissionCallbacks {

    private val tips = "玩安卓现在要向您申请存储权限，用于访问您的本地音乐，您也可以在设置中手动开启或者取消。"
    private val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    companion object {
        private const val WRITE_EXTERNAL_STORAGE = 100
    }

    override fun createViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun createViewModel(): EmptyViewModel =
        ViewModelProvider(this, ViewModelFactory()).get(EmptyViewModel::class.java)


    override fun layoutId(): Int = R.layout.activity_splash


    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData() {
        requestPermission()
    }

    /**
     * 申请权限
     */
    private fun requestPermission() {
        //已申请
        if (EasyPermissions.hasPermissions(this, *perms)) {
            startIntent()
        } else {
            //为申请，显示申请提示语
            DialogUtils.tips(this, tips) {
                RequestLocationAndCallPermission()
            }
        }
    }

    @AfterPermissionGranted(WRITE_EXTERNAL_STORAGE)
    private fun RequestLocationAndCallPermission() {
        val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //数组中权限都已申请
        if (EasyPermissions.hasPermissions(this, *perms)) {
            startIntent()
        } else {
            EasyPermissions.requestPermissions(this, "请求写入权限", WRITE_EXTERNAL_STORAGE, *perms)
        }
    }

    /**
     * 开始倒计时跳转
     */
    private fun startIntent() {
        //开启服务
        //startService(Intent(this,PlayService::class.java))
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onRetryBtnClick() {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        startIntent()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

}
