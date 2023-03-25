package com.example.nimantran.ui.main.clientGuests

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.nimantran.R
import com.example.nimantran.ui.main.clientGuests.AddMyGuestViewModel
import com.example.nimantran.databinding.FragmentAddMyGuestBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddMyGuestFragment : Fragment() {

    private var _binding: FragmentAddMyGuestBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    //private var addMyGuestViewModel: AddMyGuestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddMyGuestBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
       const val COLL_MY_GUESTS = "myGuests"
    }




}