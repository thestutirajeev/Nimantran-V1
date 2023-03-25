package com.example.nimantran.ui.admin.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimantran.databinding.FragmentReadNotificationBinding
import com.google.firebase.firestore.FirebaseFirestore

class ReadNotificationFragment : Fragment() {
    private var _binding: FragmentReadNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore

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