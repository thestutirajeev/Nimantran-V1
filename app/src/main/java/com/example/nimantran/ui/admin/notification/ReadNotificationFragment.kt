package com.example.nimantran.ui.admin.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.nimantran.databinding.FragmentReadNotificationBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore

class ReadNotificationFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentReadNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val readNotificationViewModel: ReadNotificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReadNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    companion object{
        const val COLL_NOTIFICATIONS = "notifications"
    }
}