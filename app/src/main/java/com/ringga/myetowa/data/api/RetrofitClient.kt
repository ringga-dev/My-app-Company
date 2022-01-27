package com.ringga.myetowa.data.api
/*=================== T H A N K   Y O U ===================*/
/*============= TELAH MENGUNAKAN CODE SAYA ================*/
            /* https://github.com/ringga-dev */
/*=========================================================*/
/*     R I N G G A   S E P T I A  P R I B A D I            */
/*=========================================================*/
import android.util.Base64
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val AUTH =
        "Basic " + Base64.encodeToString("ringga:123456".toByteArray(), Base64.NO_WRAP)
    /**
     * melakukan koneksi ke url server
     * */
//base url di sesuaika dengan alamat dari web server itu sendiri
    const val BASE_URL = "http://192.168.0.144/mobile/public/"

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Api::class.java)
    }
}