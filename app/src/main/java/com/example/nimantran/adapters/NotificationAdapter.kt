package com.example.nimantran.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nimantran.databinding.ItemNotificationListBinding
import com.example.nimantran.models.Notification

class NotificationAdapter (
    private val context: Context,
    private val listener: (Notification
            ) -> Unit
) : ListAdapter<Notification, NotificationAdapter.ViewHolder>(NotificationDiffUtil()) {
    class ViewHolder(
        private val binding: ItemNotificationListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: Notification) {
            binding.notification = notification
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotificationListBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { listener(getItem(position)) }
    }
}


class NotificationDiffUtil : DiffUtil.ItemCallback<Notification>() {
    override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
        return oldItem == newItem
    }

    //
    //
    override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
        return oldItem.date == newItem.date
    }
}
