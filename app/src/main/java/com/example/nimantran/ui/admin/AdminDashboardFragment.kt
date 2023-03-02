package com.example.nimantran.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentAdminDashboardBinding

class AdminDashboardFragment : Fragment() {
    private lateinit var binding: FragmentAdminDashboardBinding
    private var _binding: FragmentAdminDashboardBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.userManagement.setOnClickListener {
            findNavController().navigate(R.id.action_adminDashboardFragment_to_userListFragment)
        }

        binding.giftItemManagement.setOnClickListener {
            findNavController().navigate(R.id.action_adminDashboardFragment_to_giftListFragment)
        }

    }

    companion object {

    }
}