package com.example.nimantran.ui.admin.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentOrderListBinding

class OrderListFragment : Fragment() {

    private lateinit var binding: FragmentOrderListBinding
    private var _binding: FragmentOrderListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}