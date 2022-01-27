package com.ringga.myetowa.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ringga.myetowa.data.api.RetrofitClient
import com.ringga.myetowa.data.utils.SingleLiveEvent
import com.ringga.myetowa.model.BaseLoginRespon
import com.ringga.myetowa.model.UserRespon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {
    private var state: SingleLiveEvent<UserState> = SingleLiveEvent()
    private var api = RetrofitClient.instance
    private var user = MutableLiveData<BaseLoginRespon<UserRespon>>()

    fun login(email: String, password: String) {
        state.value = UserState.IsLoding(true)
        api.login(email, password).enqueue(object : Callback<BaseLoginRespon<UserRespon>> {
            override fun onResponse(
                call: Call<BaseLoginRespon<UserRespon>>,
                response: Response<BaseLoginRespon<UserRespon>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() as BaseLoginRespon<UserRespon>
                    if (body.stts == true) {
                        state.value = UserState.SuccessLogin(body)
                    } else {
                        state.value = UserState.Failed(body.msg)
                    }
                } else {
                    state.value = UserState.Failed(response.body()!!.msg)
                }
                state.value = UserState.IsLoding(false)
            }

            override fun onFailure(call: Call<BaseLoginRespon<UserRespon>>, t: Throwable) {
                state.value = UserState.Error(t.message!!)
                state.value = UserState.IsLoding(false)
            }

        })
    }

//    fun register(badge:String,fullname:String,username:String,email: String,devisi:String,pangkat:String,alamat:String,stts:String,no_phone:String,password: String) {
//        api.registerUser(badge, fullname, username, email,devisi, pangkat, alamat, stts ,no_phone,password).enqueue(object : Callback<BaseRegisterRespon> {
//            override fun onResponse(
//                call: Call<BaseRegisterRespon>,
//                response: Response<BaseRegisterRespon>
//            ) {
//                if (response.isSuccessful) {
//                    val body = response.body() as BaseRegisterRespon
//                    state.value = UserState.SuccessRegister(body)
//                } else {
//                    state.value = UserState.Failed("terjadi kesalahan saat register")
//                }
//
//                state.value = UserState.IsLoding(false)
//            }
//
//            override fun onFailure(call: Call<BaseRegisterRespon>, t: Throwable) {
//                state.value = UserState.Error(t.message!!)
//            }
//
//        })
//    }


    fun getState() = state

    fun getUserData() = user
}

sealed class UserState {
    data class Error(var err: String) : UserState()
    data class ShoewToals(var message: String) : UserState()
    data class Validate(
        var name: String? = null,
        var email: String? = null,
        var password: String? = null
    ) : UserState()

    data class IsLoding(var state: Boolean = false) : UserState()
    data class SuccessLogin(var data: BaseLoginRespon<UserRespon>) : UserState()
//    data class SuccessRegister(var data: BaseRegisterRespon) : UserState()
    data class Failed(var message: String) : UserState()
    object Reset : UserState()
}