package com.example.nimantran.ui.admin.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentSendNotificationBinding

class SendNotificationFragment : Fragment() {

    private lateinit var binding: FragmentSendNotificationBinding
    private var _binding: FragmentSendNotificationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
    }
}