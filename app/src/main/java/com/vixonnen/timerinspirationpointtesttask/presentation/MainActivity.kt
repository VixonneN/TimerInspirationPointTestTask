package com.vixonnen.timerinspirationpointtesttask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vixonnen.timerinspirationpointtesttask.R
import com.vixonnen.timerinspirationpointtesttask.presentation.fragment.TimerFragment
import com.vixonnen.timerinspirationpointtesttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, TimerFragment.newInstance())
            .commit()
    }
}