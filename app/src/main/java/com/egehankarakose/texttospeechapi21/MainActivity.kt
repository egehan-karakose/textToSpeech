package com.egehankarakose.texttospeechapi21


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast


import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Collections.addAll
import android.media.MediaPlayer
import retrofit2.adapter.rxjava2.Result.response
import android.os.Environment.getExternalStorageDirectory

import android.os.Environment
import android.provider.MediaStore
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava2.Result.response
import android.util.Log
import com.egehankarakose.texttospeechapi21.Networks.RetroClient
import java.io.*
import java.io.File.separator




class MainActivity : AppCompatActivity() {
    private var mp: MediaPlayer? = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            if (!editText.text.isNullOrEmpty()) {
                callData(editText.text.toString())
            } else {
                print("FAIL")
            }
        }


    }


    private fun callData(src: String) {

        RetroClient.getClient.registrationPost(src, "en-us","mp3",0).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if(response.isSuccessful){
                    playMp3(response.body()!!.bytes())
                }

                Toast.makeText(applicationContext,response.body()!!.contentType().toString(),Toast.LENGTH_SHORT).show()

            }


        })

    }

    private fun playMp3(mp3SoundByteArray: ByteArray) {
        try {
            val tempMp3 = File.createTempFile("audio", "mp3", cacheDir)
            tempMp3.deleteOnExit()
            val fos = FileOutputStream(tempMp3)
            fos.write(mp3SoundByteArray)
            fos.close()
            mp!!.reset()
            val fis = FileInputStream(tempMp3)
            mp!!.setDataSource(fis.fd)
            mp!!.prepare()
            mp!!.start()

        } catch (ex: IOException) {
            val s = ex.toString()
            ex.printStackTrace()
        }

    }




}
