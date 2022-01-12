package com.vixonnen.timerinspirationpointtesttask.presentation.view_model

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerFragmentViewModel : ViewModel() {

    private val _scopeOne = MutableLiveData(0)
    val scopeOne: LiveData<Int> = _scopeOne

    private val _scopeTwo = MutableLiveData(0)
    val scopeTwo: LiveData<Int> = _scopeTwo

    private val _timerValue = MutableLiveData(134000L)
    val timerValue: LiveData<Long> = _timerValue

    private var startTimerValue = 134000L
    private lateinit var countDownTimer: CountDownTimer

    fun increaseScopeOne() {
        var oldValue = _scopeOne.value
        oldValue = oldValue?.plus(1)
        _scopeOne.value = oldValue
    }

    fun increaseScopeTwo() {
        var oldValue = _scopeTwo.value
        oldValue = oldValue?.plus(1)
        _scopeTwo.value = oldValue
    }

    fun decreaseScopeOne() {
        var oldValue = _scopeOne.value
        if (oldValue != null) {
            if (oldValue < 1)
                Unit
            else
                oldValue = oldValue.minus(1)

        }
        _scopeOne.value = oldValue
    }

    fun decreaseScopeTwo() {
        var oldValue = _scopeTwo.value
        if (oldValue != null) {
            if (oldValue < 1)
                Unit
            else
                oldValue = oldValue.minus(1)
        }
        _scopeTwo.value = oldValue
    }

    private fun countdown() {
        countDownTimer = object : CountDownTimer(startTimerValue, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timerValue.value = millisUntilFinished
            }

            override fun onFinish() {
                ///ожидание 3 секунды и заного таймер
                viewModelScope.launch {
                    delay(3000)
                    start()
                }
            }
        }
    }

    fun startTimer() {
        countdown()
        countDownTimer.start()
    }

    fun stopTimer() {
        countDownTimer.cancel()
        startTimerValue = _timerValue.value!!.toLong()
    }

    //int -> correct time
    fun createTimeLabel(time: Long) : String {
        val min = time / 1000 / 60
        val sec = time / 1000 % 60
        var timeLabel = "$min:"
        if (sec < 10) {
            timeLabel += "0"
        }
        timeLabel += sec
        return timeLabel
    }
}