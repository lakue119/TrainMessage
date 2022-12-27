package com.lakue.trainmessage

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lakue.trainmessage.databinding.ViewMessageBinding

class MessageViewHolder(
    private val binding: ViewMessageBinding
) : RecyclerView.ViewHolder(binding.root) {
    val Tag = MessageViewHolder::class.java.name

    fun bind(message: String) {
        binding.tvMessage.text = message
    }
}