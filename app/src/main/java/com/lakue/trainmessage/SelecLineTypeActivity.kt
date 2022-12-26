package com.lakue.trainmessage

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lakue.trainInfo
import com.lakue.trainmessage.databinding.ActivitySelectLineTypeBinding

class SelecLineTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectLineTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        } catch (ignore:IllegalStateException) {
        }
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLineTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trainLines = trainInfo.map {
            it.name
        }

        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = trainInfo.size-1
        binding.numberPicker.displayedValues = trainLines.toTypedArray()
        binding.numberPicker.wrapSelectorWheel = false
        binding.numberPicker.setOnLongPressUpdateInterval(8000)

        binding.tvSelect.setOnClickListener {
            val position = binding.numberPicker.value
            val train = trainInfo[position]

            setResult(Activity.RESULT_OK, Intent().apply { putExtra("result", train) })
            finish()
        }
    }

    fun onFinish(view: View){
        finish()
    }
}