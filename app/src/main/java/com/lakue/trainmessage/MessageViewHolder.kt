package com.lakue.trainmessage

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lakue.trainmessage.databinding.ViewMessageBinding

class MessageViewHolder(
    private val binding: ViewMessageBinding,
    val listener: SampleMessageActivity.OnMessageListener
) : RecyclerView.ViewHolder(binding.root) {
    val Tag = MessageViewHolder::class.java.name

    init {
        binding.root.setOnClickListener {
            listener.onSendMessage(binding.tvMessage.text.toString())
        }
    }
    fun bind(message: String) {
        binding.tvMessage.text = message
    }
}