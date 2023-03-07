package com.example.nimantran.ui.admin.insight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentAppInsightBinding
import com.example.nimantran.databinding.FragmentUserListBinding

class AppInsightFragment : Fragment() {

    private lateinit var binding: FragmentAppInsightBinding
    private var _binding: FragmentAppInsightBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppInsightBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}