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
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_save.*
import kotlinx.android.synthetic.main.activity_save_topic.*
import kotlinx.android.synthetic.main.fragment_edit_save.*
import java.io.ByteArrayOutputStream


class SaveActivity : AppCompatActivity() {
    var selectedPicture : Uri? = null
    var selectedBitmap : Bitmap? =null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)
    }

    fun buttonSave(view: View){
        val getintent = intent
        val topicName = getintent.getStringExtra("topicname")
        println("kaydet butonuna tıklandığında topic name : $topicName")
        val topicIssue = getintent.getStringExtra("issuename").toString()
        val topicExplanation = textSave.text.toString()

        if (selectedBitmap != null){

            val smallBitmap = bitmapMinimize(selectedBitmap!!,300)
            val outputStream = ByteArrayOutputStream()
            smallBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val byteArray = outputStream.toByteArray()


            try {
                val database = this.openOrCreateDatabase("Topics", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS topics (id INTEGER PRIMARY KEY,topicName VARCHAR, topicIssue VARCHAR,topicExplanation VARCHAR,image BLOB)")
                val sqlString = "INSERT INTO topics (topicName,topicIssue,topicExplanation,image) VALUES (?,?,?,?)"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1,topicName)
                statement.bindString(2,topicIssue)
                statement.bindString(3,topicExplanation)
                statement.bindBlob(4,byteArray)
                statement.execute()
                val cursor = database.rawQuery("SELECT * FROM topics",null)
                val topicName = cursor.getColumnIndex("topicName")

                while (cursor.moveToNext()) {

                    println(cursor.getString(topicName).toString())
                }
                cursor.close()

            }catch (e : Exception){
                e.printStackTrace()
            }

            val intent = Intent(this@SaveActivity,SaveTopicActivity::class.java)
            startActivity(intent)

        }
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