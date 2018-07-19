package com.juslt.juslttools.activity

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.juslt.juslttools.R
import com.juslt.juslttools.activity.main.BaseActivity
import kotlinx.android.synthetic.main.activity_permission.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class PermissionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        btn_camera.setOnClickListener {
            showDialogWithPermissionCheck()
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun showDialog() {
        Toast.makeText(this, "假装打开相机", Toast.LENGTH_SHORT).show()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onCameraDenied() {
//        Toast.makeText(this, "相机权限被拒绝了，如果访问相机需要打开相机权限", Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
                .setPositiveButton("设置") { _, _ ->
                    Toast.makeText(this, "去设置", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("取消") { _, _ -> }
                .setCancelable(false)
                .setMessage("相机权限申请")
                .show()
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun onCameraNeverAskAgain() {
//        Toast.makeText(this, "相机权限再也不会访问", Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
                .setPositiveButton("设置") { _, _ ->
                    Toast.makeText(this, "去设置", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("取消") { _, _ -> }
                .setCancelable(false)
                .setMessage("相机权限申请")
                .show()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }
}
