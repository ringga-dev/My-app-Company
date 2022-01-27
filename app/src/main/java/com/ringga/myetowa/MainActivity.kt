package com.ringga.myetowa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ringga.myetowa.data.db.SharedPrefManager
import com.ringga.myetowa.databinding.ActivityMainBinding
import com.ringga.myetowa.ui.auth.AuthActivity
import com.ringga.myetowa.ui.home.HomeActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sdf = SimpleDateFormat("dd-M-yyyy")
        val currentDate = sdf.format(Date())

        binding.tvDate.text = "Made with ❤️ from Indonesia \n $currentDate"


    }


    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            if (SharedPrefManager.getInstance(this)!!.isLoggedIn) {
                val intent = Intent(applicationContext, HomeActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
                finish()

            } else {
                startActivity(Intent(baseContext, AuthActivity::class.java))
            }
        }, 4500)
    }

}