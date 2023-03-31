package com.example.nimantran.ui.main.addcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.nimantran.databinding.FragmentSelectGuestForCardBinding
import com.google.firebase.firestore.FirebaseFirestore

class SelectGuestForCardFragment : Fragment() {
    private var _binding: FragmentSelectGuestForCardBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val cardViewModel: TemplateCardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectGuestForCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}