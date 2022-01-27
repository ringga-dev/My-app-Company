package com.ringga.myetowa.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ringga.myetowa.data.api.RetrofitClient
import com.ringga.myetowa.data.utils.SingleLiveEvent
import com.ringga.myetowa.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var state: SingleLiveEvent<UserHomeState> = SingleLiveEvent()
    private var api = RetrofitClient.instance



    fun getCekOut(stts: String) {
        state.value = UserHomeState.IsLoding(true)

        api.getDataCekOut(stts).enqueue(object :Callback<BaseDataRespon<List<CekOut>>>{
            override fun onResponse(
                call: Call<BaseDataRespon<List<CekOut>>>,
                response: Response<BaseDataRespon<List<CekOut>>>
            ) {
                Log.e("error data", response.body().toString())
                if (response.body()!!.stts == true){
                    state.value = UserHomeState.LoadCekOut(response.body()!!.data)
                }else{
                    state.value = UserHomeState.Failed(response.body()!!.msg)
                }
                state.value = UserHomeState.IsLoding(false)
            }

            override fun onFailure(call: Call<BaseDataRespon<List<CekOut>>>, t: Throwable) {
                state.value = UserHomeState.IsLoding(false)
                state.value = UserHomeState.Failed(t.message.toString())
            }
        })
    }

    fun approveUser(id: String, name: String) {
        state.value = UserHomeState.IsLoding(true)
        api.setApprove(name,id).enqueue(
            object :Callback<BaseRespon>{
                override fun onResponse(call: Call<BaseRespon>, response: Response<BaseRespon>) {
                   if (response.body()?.stts == true){
                       state.value = UserHomeState.Error(response.body()!!.msg)
                   }else{
                       state.value = UserHomeState.Error(response.body()!!.msg)
                   }
                    state.value = UserHomeState.IsLoding(true)
                }

                override fun onFailure(call: Call<BaseRespon>, t: Throwable) {
                    state.value = UserHomeState.Error(t.message.toString())
                    state.value = UserHomeState.IsLoding(false)
                }
            }
        )
    }



    fun getState() = state

}

sealed class UserHomeState {
    data class Error(var err: String) : UserHomeState()
    data class ShoewToals(var message: String) : UserHomeState()

    data class IsLoding(var state: Boolean = false) : UserHomeState()
    data class LoadCekOut(var data: List<CekOut>) : UserHomeState()
    data class Failed(var message: String) : UserHomeState()
    object Reset : UserHomeState()
}