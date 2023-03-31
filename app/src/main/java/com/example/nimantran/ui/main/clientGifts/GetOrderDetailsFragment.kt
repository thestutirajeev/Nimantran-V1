package com.example.nimantran.ui.main.clientGifts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentGetOrderDetailsBinding

class GetOrderDetailsFragment : Fragment() {
    private var _binding: FragmentGetOrderDetailsBinding? = null
    private val binding get() = _binding!!
    private val myGiftsViewModel: MyGiftsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGetOrderDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

}