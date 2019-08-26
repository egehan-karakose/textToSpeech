package com.egehankarakose.texttospeechapi21.Networks
import android.os.StrictMode
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DatasService{

    /*@Headers(
        "X-RapidAPI-Host : restcountries-v1.p.rapidapi.com",
        "X-RapidAPI-Key : bcfd790ff5msh4a0b16ed49cd45dp135391jsn34c95dfe2e86"
    )
*/



    @FormUrlEncoded
    @POST("?key=ff1ae8489c88401b96691db6fa5f18ca")
    fun registrationPost(@Query("src") src:String,
                         @Query("hl") hl:String,
                         @Query("c") c:String,
                         @Field("r") r:Int): Call<ResponseBody>





    @GET("")
    fun getData():Call<String>


}
