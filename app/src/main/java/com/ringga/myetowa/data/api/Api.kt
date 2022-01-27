package com.ringga.myetowa.data.api
/*=================== T H A N K   Y O U ===================*/
/*============= TELAH MENGUNAKAN CODE SAYA ================*/
/* https://github.com/ringga-dev */
/*=========================================================*/
/*     R I N G G A   S E P T I A  P R I B A D I            */
/*=========================================================*/

import com.ringga.myetowa.model.*
import retrofit2.Call
import retrofit2.http.*

interface Api {
    /**
     * file ini berfungsi untuk menyimpan semua enpoin dari api
     * */

    //login
    @FormUrlEncoded
    @POST("UserApi/login_api")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<BaseLoginRespon<UserRespon>>

    //login scan
    @FormUrlEncoded
    @POST("UserApi/login_bet")
    fun loginScan(
        @Field("id_bet") bet_id: String
    ): Call<BaseLoginRespon<UserRespon>>

    /** get data cekOut  */
    @FormUrlEncoded
    @POST("userApi/get_cek_out")
    fun getDataCekOut(
        @Field("stts") stts: String
    ):Call<BaseDataRespon<List<CekOut>>>

    @FormUrlEncoded
    @POST("userApi/set_approve")
    fun setApprove(
        @Field("name") name: String,
        @Field("id") id: String
    ):Call<BaseRespon>
}


