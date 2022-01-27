package com.ringga.myetowa.model

import com.google.gson.annotations.SerializedName

data class  UserRespon(
    @SerializedName("id") val id: Int,
    @SerializedName("id_bet") val id_bet: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("no_phone") val no_phone: String,
    @SerializedName("image") val image: String,
    @SerializedName("devisi") val devisi: String
)

data class Key(
    @SerializedName("bet_id") val bet_id: String,
    @SerializedName("exp") val exp: String,
    @SerializedName("token") val token: String
)

data class BaseLoginRespon<T> (
    @SerializedName("stts") val stts : Boolean,
    @SerializedName("msg") val msg : String,
    @SerializedName("key")val key: Key,
    @SerializedName("app") val app : String,
    @SerializedName("data") val data : T,
)

data class BaseRespon(
    @SerializedName("stts") val stts : Boolean,
    @SerializedName("msg") val msg : String,
)

data class BaseDataRespon<T>(
    @SerializedName("stts") val stts : Boolean,
    @SerializedName("msg") val msg : String,
    @SerializedName("data") val data : T,
)

data class CekOut(
    @SerializedName("id") val id : Int,
    @SerializedName("badge") val badge: String,
    @SerializedName("plan") val plan: String,
    @SerializedName("end_time") val end_time: String,
    @SerializedName("destination") val destination: String,
    @SerializedName("remarks") val remarks :String ,
    @SerializedName("stts_form") val stts_form: String,
    @SerializedName("from") val from: String,
    @SerializedName("to") val to: String,
    @SerializedName("approved_by") val approved_by: String,
    @SerializedName("date_out") val date_out: String,
    @SerializedName("date_in") val date_in: String,
    @SerializedName("update") val update: String,
    @SerializedName("create") val create: String,
    @SerializedName("is_active") val is_active: String,
    @SerializedName("name") val name: String,

)


