package com.ugurd.guideapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_save.*
import kotlinx.android.synthetic.main.fragment_edit_save.*


class SaveActivity : AppCompatActivity() {
    var selectedPicture : Uri? = null
    var selectedBitmap : Bitmap? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)
    }


    fun buttonSave(view: View){
        //sql kayıt
    }
    fun imageSaveSelect(view: View){
        if (ContextCompat.checkSelfPermission(applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //izin iste
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)

        }else{
            //galeriye git
            val galeryintent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeryintent,2)
        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == 1){
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // izin alındı
                val galeryintent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeryintent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){


            selectedPicture = data.data
            try {

                    if (selectedPicture != null) {
                        if (Build.VERSION.SDK_INT >= 28) {
                            println("çalıştı")
                            val source = ImageDecoder.createSource(applicationContext.contentResolver, selectedPicture!!)
                            selectedBitmap = ImageDecoder.decodeBitmap(source)
                            imageSave.setImageBitmap(selectedBitmap)
                        }else{
                            selectedBitmap = MediaStore.Images.Media.getBitmap(applicationContext.contentResolver,selectedPicture)
                            imageSave.setImageBitmap(selectedBitmap)

                        }
                    }



            }catch (e : Exception){
                e.printStackTrace()
            }
        }


        super.onActivityResult(requestCode, resultCode, data)
    }
    fun bitmapMinimize(selectedBitmap : Bitmap, maxSize:Int) : Bitmap{

        var width = selectedBitmap.width
        var height = selectedBitmap.height

        val bitmapRate : Double = width.toDouble()/ height.toDouble()
        if (bitmapRate > 1){
            width = maxSize
            val shortenedHeight = width / bitmapRate
            height = shortenedHeight.toInt()
        }else{
            height = maxSize
            val shortenedWidth = height * bitmapRate
            width = shortenedWidth.toInt()
        }

        return  Bitmap.createScaledBitmap(selectedBitmap,width,height,true)
    }
}