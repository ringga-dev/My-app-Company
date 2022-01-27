package com.ringga.myetowa.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.annotations.SerializedName
import com.ringga.myetowa.R
import com.ringga.myetowa.data.adapter.CekOutAdapter
import com.ringga.myetowa.data.utils.toast
import com.ringga.myetowa.databinding.ActivityDitailDataBinding

class DitailDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDitailDataBinding
    private lateinit var authViewModel: HomeViewModel
    private var status: Boolean? = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDitailDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val extras = intent.extras
        binding.name.text = ": ${extras?.getString("name")}"
        binding.badge.text = ": ${extras?.getString("badge")}"
        binding.plan.text = ": ${extras?.getString("plan")}"
        binding.endTime.text = ": ${extras?.getString("end_time")}"
        binding.destination.text = ": ${extras?.getString("destination")}"
        binding.remarks.text = ": ${extras?.getString("remarks")}"
        binding.sttsForm.text = ": ${extras?.getString("stts_form")}"
        binding.from.text = ": ${extras?.getString("from")}"
        binding.to.text = ": ${extras?.getString("to")}"
        binding.approvedBy.text = ": ${extras?.getString("approved_by")}"

        if (extras?.getString("approved_by") == "" || extras?.getString("approved_by") == null){
            status= false
        }else{
            status = true
        }
//        binding.dateOut.text = ": ${extras?.getString("date_out")}"
//        binding.dateIn.text = ": ${extras?.getString("date_in")}"
        binding.update.text = ": ${extras?.getString("update")}"

        authViewModel.getState().observer(this, Observer {
            handleUiState(it)
        })
        fungsi()
        binding.btnApprove.setOnClickListener {
            if (status == true){
                status= false
            }else{
                status = true
            }
            authViewModel.approveUser(extras?.getInt("id").toString(), "nama")
            fungsi()
        }
    }

    private fun fungsi(){
        if (status == false){
            binding.btnApprove.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_false))
        }else{
            binding.btnApprove.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_true))
        }

    }
    private fun handleUiState(it: UserHomeState) {
        when (it) {

            is UserHomeState.Error -> {
//                isloding(false)
                toast(this, it.err)
            }
            is UserHomeState.ShoewToals -> toast(this, it.message)
            is UserHomeState.Failed -> {
//                isloding(false)
                toast(this, it.message)
            }

            is UserHomeState.LoadCekOut -> {


            }
//            is UserState.IsLoding -> isloding(it.state)
        }
    }
}