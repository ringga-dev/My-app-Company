package com.ringga.myetowa.ui.auth

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.ringga.myetowa.R
import com.ringga.myetowa.data.utils.snackbar
import com.ringga.myetowa.databinding.ActivityAuthBinding
import com.ringga.myetowa.ui.auth.fm.LoginFragment
import com.ringga.myetowa.ui.auth.fm.LoginScanFragment

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private var scaledown: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scaledown = AnimationUtils.loadAnimation(
            this,
            R.anim.scale_down_button
        )

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, LoginFragment.newInstance())
                .commitNow()
        }

        binding.btnCekConetion.setOnClickListener { view ->
            snackbarView(view)
        }

        binding.btnLogin.setOnClickListener {
            binding.btnLogin.startAnimation(scaledown)

            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, LoginFragment.newInstance())
                .commitNow()
        }

        binding.btnRegister.setOnClickListener {
            binding.btnRegister.startAnimation(scaledown)
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, LoginScanFragment())
                .commitNow()
        }
    }

    private fun snackbarView(view: View) {
        val ConnectionManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = ConnectionManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            snackbar("Network Available", view)
            Handler().postDelayed({
                val cm =
                    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                // Network Capabilities of Active Network
                val nc = cm.getNetworkCapabilities(cm.activeNetwork)
                // DownSpeed in MBPS
                val downSpeed = (nc?.linkDownstreamBandwidthKbps)!! / 1000
                // UpSpeed  in MBPS
                val upSpeed = (nc.linkUpstreamBandwidthKbps) / 1000
                // Toast to Display DownSpeed and UpSpeed
                snackbar("Up To: $upSpeed Mbps \nDown Speed: $downSpeed Mbps", view)
            }, 2000)
        } else {
            snackbar("Network Not Available", view)
        }
    }
}