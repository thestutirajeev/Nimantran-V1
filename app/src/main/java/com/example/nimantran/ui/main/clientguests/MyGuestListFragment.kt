package com.example.nimantran.ui.main.clientguests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentMyGuestListBinding

class MyGuestListFragment : Fragment() {
    private var _binding: FragmentMyGuestListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyGuestListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}