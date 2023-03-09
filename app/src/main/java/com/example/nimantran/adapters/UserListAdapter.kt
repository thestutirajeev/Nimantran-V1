package com.example.nimantran.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nimantran.databinding.ItemUserListBinding
import com.example.nimantran.R
import com.example.nimantran.models.Client


class UserListAdapter(
    private val context: Context,
    private val cardListener: (Client) -> Unit
) : ListAdapter<Client, UserListAdapter.ViewHolder>(ClientDiffUtil()) {
    class ViewHolder(
        private val binding: ItemUserListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            client: Client,
            cardListener: (Client) -> Unit
        ) {
            binding.client = client
            binding.cardViewUserList.setOnClickListener {
                cardListener(client)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserListBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), cardListener)
}

class ClientDiffUtil : DiffUtil.ItemCallback<Client>() {
    override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
        return oldItem == newItem
    }

    //
    //
    override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
        return oldItem.name == newItem.name
    }
}