package com.example.nimantran.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nimantran.databinding.ItemGiftListBinding
import com.example.nimantran.models.Gift

class GiftAdapter {
    private val context: Context,
    private val listener: (Gift) -> Unit
    ) : ListAdapter<Gift, GiftAdapter.ViewHolder>(GiftDiffUtil()) {
        class ViewHolder(
            private val binding: ItemGiftListBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(gift: Gift) {
                binding.gift = gift
                binding.executePendingBindings()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemGiftListBinding.inflate(
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

    class GiftDiffUtil : DiffUtil.ItemCallback<Gift>() {
        override fun areItemsTheSame(oldItem: Gift, newItem: Gift): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Gift, newItem: Gift): Boolean {
            return oldItem.item == newItem.item
        }
}