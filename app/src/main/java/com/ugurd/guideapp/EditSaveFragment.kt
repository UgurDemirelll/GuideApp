package com.ugurd.guideapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_edit_topic_select.*
import kotlinx.android.synthetic.main.fragment_edit_save.*
import java.io.ByteArrayOutputStream

class EditSaveFragment : Fragment() {

    var topicName : String? = null
    var selectedPicture : Uri? = null
    var selectedBitmap : Bitmap? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_edit_save, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonEditSave.setOnClickListener {
            saveButton(it)
        }
        imageEditSave.setOnClickListener {
            imageSelect(it)
        }

    }


        fun saveButton(view: View){
            val explanation = textEditSave.text.toString()
            
            if (selectedBitmap != null){

                val smallBitmap = bitmapMinimize(selectedBitmap!!,300)
                val outputStream = ByteArrayOutputStream()
                smallBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
                val byteArray = outputStream.toByteArray()
                try {
                    context?.let {
                        val database = it.openOrCreateDatabase("Topics",Context.MODE_PRIVATE,null)
                        database.execSQL("CREATE TABLE IF NOT EXISTS $topicName (id INTEGER PRIMARY KEY, issuename VARCHAR,topicexplanation VARCHAR,image BLOB)")
                        //topic[spinner2.selectedItemPosition]database.execSQL("INSERT INTO ")
                    }

                }catch (e : Exception){
                    e.printStackTrace()
                }



            }



        }

        fun imageSelect(view: View){
            activity?.let {
            if (ContextCompat.checkSelfPermission(it.applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                //izin iste
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)

            }else{
                //galeriye git
                val galeryintent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeryintent,2)
            }
            }
        }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

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
                context?.let {
                    if (selectedPicture != null) {
                        if (Build.VERSION.SDK_INT >= 28) {
                            val source =
                                ImageDecoder.createSource(it.contentResolver, selectedPicture!!)
                            selectedBitmap = ImageDecoder.decodeBitmap(source)
                            imageEditSave.setImageBitmap(selectedBitmap)
                        }else{
                            selectedBitmap = MediaStore.Images.Media.getBitmap(it.contentResolver,selectedPicture)
                            imageEditSave.setImageBitmap(selectedBitmap)

                        }
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








