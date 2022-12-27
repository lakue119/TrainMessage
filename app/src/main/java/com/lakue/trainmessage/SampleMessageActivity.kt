package com.lakue.trainmessage

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
    }

    fun setRecyclerView() {
        messageAdapter = MessageAdapter()
        binding.run {
            rvMessage.adapter = messageAdapter
        }
    }
}