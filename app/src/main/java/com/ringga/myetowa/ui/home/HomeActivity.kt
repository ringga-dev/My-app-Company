package com.ringga.myetowa.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ringga.myetowa.R
import com.ringga.myetowa.databinding.ActivityHomeBinding
import com.ringga.myetowa.ui.home.fm.ApproveFragment
import com.ringga.myetowa.ui.home.fm.FinisFragment
import com.ringga.myetowa.ui.home.fm.NotApproveFragment
import com.ringga.myetowa.ui.profile.ProfileActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.navigationHome.setOnItemSelectedListener {
            when (it) {
                R.id.approve -> {
                    openMainFragment()
                }

                R.id.not_approve -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.container_home, NotApproveFragment.newInstance())
                    transaction.commit()
                }

                R.id.finis -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.container_home, FinisFragment.newInstance())
                    transaction.commit()
                }

                R.id.profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }

            }
        }
        openMainFragment()
        binding.navigationHome.setItemSelected(R.id.approve,true)
    }
    private fun openMainFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_home, ApproveFragment.newInstance())
        transaction.commit()
    }
}

