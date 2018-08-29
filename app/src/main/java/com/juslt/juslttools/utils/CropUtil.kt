package com.juslt.juslttools.utils

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore

/**
 * Created by juslt on 2018/8/8.
 */
object CropUtil{

    fun cropImage(uri: Uri, targetUri: Uri?):Intent{
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri,"image/*")
        //设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop","true")
        //设置剪裁区域的宽高比
        intent.putExtra("scale",true)
        intent.putExtra("aspectX",1)
        intent.putExtra("aspectY",1)
        intent.putExtra("outputX",500)
        intent.putExtra("outputY",500)

        intent.putExtra("return-data",false)
        intent.putExtra("noFaceDetection",true)
        intent.putExtra(MediaStore.EXTRA_OUTPUT,targetUri)

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        return intent
    }
}