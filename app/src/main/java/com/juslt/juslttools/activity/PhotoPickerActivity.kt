package com.juslt.juslttools.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.juslt.common.utils.BitmapUtil
import com.juslt.common.utils.ZipUtils
import com.juslt.juslttools.R
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import kotlinx.android.synthetic.main.activity_photo_picker.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import java.io.File

@RuntimePermissions
class PhotoPickerActivity : AppCompatActivity() {
    val TAKE_PHOTO=200
    val CROP_IMAGE=300

    val string_path = Environment.getExternalStorageDirectory().absolutePath+File.separator+"temp_image"
    val photoPath = string_path  + "/temp.jpg"
    lateinit var photoFile:File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_picker)
        btn_open_photo.setOnClickListener {
        openPhotoWithPermissionCheck()
        }
        btn_open_camera.setOnClickListener {
            //调用系统相机拍照，回调data显示图片缩略图
//            val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(captureIntent,TAKE_PHOTO)


            val filePath = File(string_path)
            if(!filePath.exists()){
                filePath.mkdir()
            }
            val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
             photoFile = File(photoPath)
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT,FileProvider.getUriForFile(this,application.packageName +".provider",photoFile))
            startActivityForResult(captureIntent,TAKE_PHOTO)
        }
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun openPhoto() {
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .countable(false)
                .maxSelectable(9)
                .imageEngine(GlideEngine())
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .theme(R.style.Matisse_Dracula)
                .forResult(100)
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun onStorageDenied(){

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode,grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== Activity.RESULT_OK && requestCode==TAKE_PHOTO){

//            val bitmap = data!!.extras.get("data") as Bitmap
//            iv_photo.setImageBitmap(bitmap)

            //data为空的， 这时使用自定义的路径来获取图片资源
//            val photoPath = ZipUtils.compImageFile(photoPath)
//            val bitmap = BitmapFactory.decodeFile(photoPath)
//            iv_photo.setImageBitmap(bitmap)
            ZipUtils.compImageFile(photoPath)
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                val contentUri = FileProvider.getUriForFile(this,application.packageName +".provider",photoFile)
                cropImage(contentUri)
            }else{
                val contentUri = Uri.fromFile(photoFile)
                cropImage(contentUri)
            }

        }
        if(resultCode== Activity.RESULT_OK&&requestCode==CROP_IMAGE){
//            val bitmap = BitmapFactory.decodeFile(photoPath)
            val bundle = intent.extras
            if(bundle!=null){
                val bitmap = bundle.getParcelable<Bitmap>("data")
                iv_photo.setImageBitmap(bitmap)
            }

        }
    }

    private fun cropImage(uri:Uri) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.setDataAndType(uri,"image/*")
        intent.putExtra("crop","true")
        intent.putExtra("aspectX",1)
        intent.putExtra("aspectY",1)

        intent.putExtra("outputX",500)
        intent.putExtra("outputY",500)
        intent.putExtra("return-data",true)
        startActivityForResult(intent,CROP_IMAGE)
    }
}
