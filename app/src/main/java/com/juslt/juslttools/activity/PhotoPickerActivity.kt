package com.juslt.juslttools.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.juslt.juslttools.R
import com.juslt.juslttools.utils.CropUtil
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
    val REQUEST_CAMERA = 200
    val REQUEST_PHOTO_ALBUM = 400
    val CROP_IMAGE = 300

    val string_path = Environment.getExternalStorageDirectory().absolutePath + File.separator + "temp_image"
    val photoPath = string_path + "/temp.jpg"
    var targetUri:Uri?=null
    lateinit var cropFile:File
    lateinit var photoFile: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_picker)

        val string_path = Environment.getExternalStorageDirectory().absolutePath+ File.separator + "temp_image"
        val filePath = File(string_path)
        if (!filePath.exists()) {
            filePath.mkdir()
        }
        cropFile= File(string_path+"/temp_crop.img")



        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            //创建文件
            val filePath = File(string_path)
            if (!filePath.exists()) {
                filePath.mkdir()
            }
            photoFile = File(photoPath)
        }
        btn_open_photo.setOnClickListener {
            openPhotoWithPermissionCheck()
        }
        btn_open_camera.setOnClickListener {
            //调用系统相机拍照，回调data显示图片缩略图
//            val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(captureIntent,TAKE_PHOTO)

            //intent
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                val uri = FileProvider.getUriForFile(this, application.packageName + ".provider", photoFile) //Android7.0以上获取Uri
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            } else {
                val uri = Uri.fromFile(photoFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            }
            startActivityForResult(intent, REQUEST_CAMERA)
        }

        btn_open_photo_album.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, null)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(intent, REQUEST_PHOTO_ALBUM)
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
    fun onStorageDenied() {

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode!=Activity.RESULT_OK){
            return
        }

        when(requestCode){
            100->{

            }
            REQUEST_CAMERA->{
                //            ZipUtils.compImageFile(photoPath)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    val contentUri = FileProvider.getUriForFile(this, application.packageName + ".provider", photoFile)
                    cropImage(contentUri)
                } else {
                    val contentUri = Uri.fromFile(photoFile)
                    cropImage(contentUri)
                }
            }
            REQUEST_PHOTO_ALBUM->{
                //            photoFile = File(data!!.data)
//            cropImage(FileProvider.getUriForFile(this, application.packageName + ".provider", photoFile))
                cropImage(data!!.data)
            }
            CROP_IMAGE->{
                val bitmap = BitmapFactory.decodeFile(cropFile.path)
                iv_photo.setImageBitmap(bitmap)
            }
        }
    }

    private fun cropImage(uri: Uri) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            targetUri = FileProvider.getUriForFile(this, application.packageName + ".provider", cropFile)
        } else {
            targetUri = Uri.fromFile(cropFile)
        }
        startActivityForResult(CropUtil.cropImage(uri,targetUri), CROP_IMAGE)
    }


}
