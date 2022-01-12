package com.vixonnen.timerinspirationpointtesttask.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.vixonnen.timerinspirationpointtesttask.presentation.view_model.TimerFragmentViewModel
import com.vixonnen.timerinspirationpointtesttask.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    private val viewModel: TimerFragmentViewModel by lazy {
        ViewModelProvider(this)[TimerFragmentViewModel::class.java]
    }

    private var _binding: FragmentTimerBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTimerBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initializeObservers()
        initializeButtons()
    }

    private fun initializeObservers() {
        viewModel.scopeOne.observe(viewLifecycleOwner) { valueInt ->
            mBinding.scopeOne.text = valueInt.toString()
        }

        viewModel.scopeTwo.observe(viewLifecycleOwner) { valueInt ->
            mBinding.scopeTwo.text = valueInt.toString()
        }

        viewModel.timerValue.observe(viewLifecycleOwner) { timerValue ->
            mBinding.timerTv.text = viewModel.createTimeLabel(timerValue)
        }
    }

    private fun initializeButtons() {
        mBinding.addScopeOneIbtn.setOnClickListener {
            viewModel.increaseScopeOne()
        }

        mBinding.removeScopeOneIbtn.setOnClickListener {
            viewModel.decreaseScopeOne()
        }

        mBinding.addScopeTwoIbtn.setOnClickListener {
            viewModel.increaseScopeTwo()
        }

        mBinding.removeScopeTwoIbtn.setOnClickListener {
            viewModel.decreaseScopeTwo()
        }

        mBinding.startBtn.setOnClickListener {
            mBinding.startBtn.visibility = View.INVISIBLE
            mBinding.stopBtn.visibility = View.VISIBLE
            mBinding.timerTv.setTextColor(Color.WHITE)
            viewModel.startTimer()
        }

        mBinding.stopBtn.setOnClickListener {
            mBinding.startBtn.visibility = View.VISIBLE
            mBinding.stopBtn.visibility = View.INVISIBLE
            mBinding.timerTv.setTextColor(Color.BLACK)
            viewModel.stopTimer()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.scopeOne.value
    }

    companion object {
        fun newInstance() = TimerFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}