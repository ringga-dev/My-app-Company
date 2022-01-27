package com.ringga.myetowa.ui.home.fm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ringga.myetowa.R
import com.ringga.myetowa.data.adapter.CekOutAdapter
import com.ringga.myetowa.data.utils.toast
import com.ringga.myetowa.databinding.FragmentApproveBinding
import com.ringga.myetowa.databinding.FragmentNotApproveBinding
import com.ringga.myetowa.ui.home.HomeViewModel
import com.ringga.myetowa.ui.home.UserHomeState


class NotApproveFragment : Fragment() {



    companion object {
        fun newInstance() = NotApproveFragment()
    }

    private lateinit var authViewModel: HomeViewModel
    private var _binding: FragmentNotApproveBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        authViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        _binding = FragmentNotApproveBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecler()
        authViewModel.getState().observer(this, Observer {
            handleUiState(it)
        })


        authViewModel.getCekOut("not_approve")
    }

    private fun handleUiState(it: UserHomeState) {
        when (it) {

            is UserHomeState.Error -> {
//                isloding(false)
                toast(requireContext(), it.err)
            }
            is UserHomeState.ShoewToals -> toast(requireContext(), it.message)
            is UserHomeState.Failed -> {
//                isloding(false)
                toast(requireContext(), it.message)
            }

            is UserHomeState.LoadCekOut -> {
                binding.rvNotApprove.adapter?.let { adapter ->
                    if (adapter is CekOutAdapter) {
                        adapter.setLagu(it.data)
                    }
                }

            }
//            is UserState.IsLoding -> isloding(it.state)
        }
    }

    private fun setupRecler() {

        binding.rvNotApprove.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            adapter = CekOutAdapter(mutableListOf(), requireContext(), requireFragmentManager())

        }
    }

}