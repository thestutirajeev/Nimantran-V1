package com.example.nimantran.ui.admin.notification

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentReadNotificationBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore

class ReadNotificationFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentReadNotificationBinding? = null
    private val args: ReadNotificationFragmentArgs by navArgs()
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val notificationListViewModel: NotificationListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_read_notification, container, false)
        binding.viewModel = notificationListViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: ${args.subject}")
        Log.d("TAGX", "onViewCreated: ${notificationListViewModel.selectedNotification.value}")
        notificationListViewModel.selectedNotification.observe(viewLifecycleOwner) {

        }
    }
    
    companion object{
        const val COLL_NOTIFICATIONS = "notifications"
    }
}