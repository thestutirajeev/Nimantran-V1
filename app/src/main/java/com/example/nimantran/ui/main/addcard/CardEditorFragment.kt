package com.example.nimantran.ui.main.addcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentCardEditorBinding

class CardEditorFragment : Fragment() {
    private var _binding: FragmentCardEditorBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardEditorBinding.inflate(inflater, container, false)
        return binding.root
    }


}