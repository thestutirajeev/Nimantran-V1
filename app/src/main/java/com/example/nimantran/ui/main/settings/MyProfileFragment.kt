package com.example.nimantran.ui.main.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimantran.databinding.FragmentMyProfileBinding

class MyProfileFragment : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding
    private var _binding: FragmentMyProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}