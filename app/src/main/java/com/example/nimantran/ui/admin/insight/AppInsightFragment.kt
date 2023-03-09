package com.example.nimantran.ui.admin.insight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.nimantran.R
import com.example.nimantran.R.layout.fragment_app_insight
import com.example.nimantran.databinding.FragmentAppInsightBinding

class AppInsightFragment : Fragment() {

    private var _binding: FragmentAppInsightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, fragment_app_insight, container, false)
        return binding.root
    }

    companion object {
    }
}