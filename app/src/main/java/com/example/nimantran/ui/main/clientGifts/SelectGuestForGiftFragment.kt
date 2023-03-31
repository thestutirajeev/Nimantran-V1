package com.example.nimantran.ui.main.clientGifts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentSelectMyGuestForGiftBinding
import com.google.firebase.firestore.FirebaseFirestore

class SelectGuestForGiftFragment : Fragment() {
    private var _binding: FragmentSelectMyGuestForGiftBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val myGiftsViewModel: MyGiftsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_my_guest_for_gift, container, false)
        return binding.root
    }

    companion object {
    }
}