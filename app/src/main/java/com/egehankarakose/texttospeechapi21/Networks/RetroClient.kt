package com.egehankarakose.texttospeechapi21.Networks


import android.os.StrictMode
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClient{





    val getClient:DatasService
        get(){


            val interceptor = HttpLoggingInterceptor()

            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()

                .addInterceptor(Interceptor { chain ->

                    val original = chain.request()

                    //header

                    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                    StrictMode.setThreadPolicy(policy)

                    val request = original.newBuilder()

                        .header("X-RapidAPI-Host", "voicerss-text-to-speech.p.rapidapi.com")

                        .header(
                            "X-RapidAPI-Key",
                            "bcfd790ff5msh4a0b16ed49cd45dp135391jsn34c95dfe2e86"
                        )
                        .header("Content-Type", "application/x-www-form-urlencoded")



                        .build()

                    return@Interceptor chain.proceed(request)

                })

                .addInterceptor(interceptor)

                .build()



            return Retrofit.Builder()
                .baseUrl("https://voicerss-text-to-speech.p.rapidapi.com/")
                .client(client)
                .addConverterFactory(gsonConverter)
                .build().create(DatasService::class.java)




        }


    val gsonConverter: GsonConverterFactory
        get() {

            var mGsonConverter = GsonConverterFactory
                .create(GsonBuilder()
                    .setLenient()
                    .disableHtmlEscaping()
                    .create())

            return mGsonConverter
        }




}