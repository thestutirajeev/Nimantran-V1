package com.example.nimantran.ui.main.clientGiftOrders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentMyGiftOrdersBinding

class MyGiftOrdersFragment : Fragment() {
    private var _binding: FragmentMyGiftOrdersBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyGiftOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

    }
}