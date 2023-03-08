package com.example.nimantran.ui.main.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentAppPreferencesBinding

class AppPreferencesFragment : Fragment() {

    private lateinit var binding: FragmentAppPreferencesBinding
    private var _binding: FragmentAppPreferencesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppPreferencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

    }
}