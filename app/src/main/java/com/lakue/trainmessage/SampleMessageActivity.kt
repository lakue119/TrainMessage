package com.lakue.trainmessage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.lakue.trainmessage.databinding.ActivitySampleMessageBinding

class SampleMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleMessageBinding
    private lateinit var messageAdapter: MessageAdapter

    val listener = object : OnMessageListener{
        override fun onSendMessage(message: String) {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(
                    "message", message
                ) }
            )
            finish()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
    }

    fun setRecyclerView() {
        messageAdapter = MessageAdapter(listener)
        binding.run {
            rvMessage.adapter = messageAdapter
        }
    }

    interface OnMessageListener {
        fun onSendMessage(message: String)
    }
}