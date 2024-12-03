package com.icarus.recycle_app.ui.study_game.views

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView

class CountdownDialog(context: Context) : Dialog(context) {

    private val handler = Handler(Looper.getMainLooper())
    private val countdownText: TextView
    private var countdownValue = 3

    interface CountDownListener {
        fun onComplete()
    }

    private var _countDownListener: CountDownListener? = null
    var countDownListener: CountDownListener?
        get() = _countDownListener
        set(value) {
            _countDownListener = value
        }


    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setDimAmount(0.7f)

        setCancelable(false)

        val layout = LinearLayout(context)
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        layout.gravity = Gravity.CENTER

        countdownText = TextView(context)
        countdownText.textSize = 300f
        countdownText.setTextColor(Color.WHITE)
        layout.addView(countdownText)

        setContentView(layout)

        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)


        startCountdown()
    }

    private fun startCountdown() {
        countdownText.text = countdownValue.toString()

        if (countdownValue > 0) {
            handler.postDelayed({
                countdownValue--
                startCountdown()
            }, 1000)
        } else {
            window?.setDimAmount(0.0f)
            dismiss()
            countDownListener?.onComplete()
        }
    }
}