package com.lakue.trainmessage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lakue.message
import com.lakue.trainmessage.databinding.ViewMessageBinding

class MessageAdapter: RecyclerView.Adapter<MessageViewHolder>() {
    val Tag = MessageAdapter::class.java.name

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ViewMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(message[position])
    }

    override fun getItemCount() = message.size
}