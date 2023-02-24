package com.example.nimantran.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimantran.databinding.FragmentNotificationListBinding

class NotificationListFragment : Fragment() {

    private lateinit var binding: FragmentNotificationListBinding
    private var _binding: FragmentNotificationListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

    }
}