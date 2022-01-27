package com.ringga.myetowa.ui.auth.fm

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CaptureManager
import com.ringga.myetowa.R
import com.ringga.myetowa.data.utils.ValidationEmail
import com.ringga.myetowa.data.utils.ValidationPassword
import com.ringga.myetowa.data.utils.toast
import com.ringga.myetowa.databinding.CostumScanBinding
import com.ringga.myetowa.databinding.FragmentLoginScanBinding
import com.ringga.myetowa.ui.auth.AuthViewModel
import com.ringga.myetowa.ui.auth.UserState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject


class LoginScanFragment : Fragment() {
    companion object {
        fun newInstance() = LoginScanFragment()
    }

    private lateinit var authViewModel: AuthViewModel
    private lateinit var includeBinding: CostumScanBinding
    private var _binding: FragmentLoginScanBinding? = null
    private val binding get() = _binding!!

    lateinit var mTTS: TextToSpeech
    private lateinit var captureManager: CaptureManager
    private var torchState: Boolean = false

    private var scanContinuousState: Boolean = false
    private lateinit var scanContinuousBG: Drawable
    lateinit var beepManager: BeepManager
    private var lastScan = Date()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        _binding = FragmentLoginScanBinding.inflate(inflater, container, false)
        includeBinding = CostumScanBinding.bind(binding.root)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        authViewModel.getState().observer(this, Observer {
            handleUiState(it)
        })

        mTTS = TextToSpeech(activity?.applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTTS.language = Locale("id", "ID")
            }
        })

        binding.barcodeView.setOnClickListener {
            binding.barcodeView.cameraSettings.focusMode
        }
        captureManager = CaptureManager(requireActivity(), binding.barcodeView)
        captureManager.initializeFromIntent(activity?.intent, savedInstanceState)

        beepManager = BeepManager(requireActivity())
        beepManager.isVibrateEnabled = true




        val callback = object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result?.let {
                    val current = Date()
                    beepManager.playBeepSoundAndVibrate()

                    lastScan = current

                    animateBackground()
                    scanContinuousState = !scanContinuousState
                    binding.barcodeView.barcodeView.stopDecoding()
                    toast(requireContext(), it.text)

                }
            }

            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
            }
        }


        if (!scanContinuousState) {
            scanContinuousState = !scanContinuousState
            binding.barcodeView.decodeContinuous(callback)
        } else {
            scanContinuousState = !scanContinuousState
            binding.barcodeView.barcodeView.stopDecoding()
        }

        includeBinding.switchFlashlight.setOnClickListener {
            if (torchState) {
                torchState = false
                binding.barcodeView.setTorchOff()
                includeBinding.switchFlashlight.setBackgroundResource(R.drawable.bg_senter_off)
            } else {
                includeBinding.switchFlashlight.setBackgroundResource(R.drawable.bg_senter_on)
                torchState = true
                binding.barcodeView.setTorchOn()
            }
        }


    }

    private fun handleUiState(it: UserState) {
        when (it) {

            is UserState.Error -> {
//                isloding(false)
                toast(requireContext(), it.err)
            }
            is UserState.ShoewToals -> toast(requireContext(), it.message)
            is UserState.Failed -> {
//                isloding(false)
                toast(requireContext(), it.message)
            }

            is UserState.SuccessLogin -> {
//                SharedPrefManager.getInstance(requireContext()).saveUser(it.data.data)
//                PreferencesToken.setToken(requireContext(), it.data.token)
                Toast.makeText(requireContext(), it.data.toString(), Toast.LENGTH_SHORT).show()

//                startActivity(Intent(requireContext(), HomeActivity::class.java))
                activity?.finish()
            }
//            is UserState.IsLoding -> isloding(it.state)
        }
    }

    override fun onPause() {
        super.onPause()
        captureManager.onPause()
        if (mTTS.isSpeaking) {
            //if speaking then stop
            mTTS.stop()
            //mTTS.shutdown()
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        captureManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        captureManager.onDestroy()
    }

    private fun animateBackground() {
        val colorFrom = resources.getColor(R.color.purple_700)
        val colorTo = resources.getColor(R.color.teal_200)
        val colorAnimation =
            ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 250 // milliseconds

//        colorAnimation.addUpdateListener { animator ->
//            binding.txtResultContinuous.setBackgroundColor(
//                animator.animatedValue as Int
//            )
//        }
        colorAnimation.start()
    }

}