package com.example.nimantran.ui.admin.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.nimantran.R
import com.example.nimantran.adapters.NotificationAdapter
import com.example.nimantran.databinding.FragmentNotificationListBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NotificationListFragment : Fragment() {
    private var _binding: FragmentNotificationListBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private var notificationListViewModel: NotificationListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationListViewModel.getNotifications(db) // fetch data only
        notificationListViewModel.notifications.observe(viewLifecycleOwner) { notifications ->
            if (notifications.isNotEmpty()) {
                binding.recyclerViewNotificationList.adapter = NotificationAdapter(requireActivity()) {
                    notificationListViewModel.selectNotification(it)
                    findNavController().navigate(R.id.action_notificationListFragment_to_addNotificationFragment)
                }
                (binding.recyclerViewNotificationList.adapter as NotificationAdapter).submitList(notifications)
            } else {
                binding.recyclerViewNotificationList.visibility = View.GONE
            }
            if (binding.swipeRefreshLayoutNotificationList.isRefreshing) {
                binding.swipeRefreshLayoutNotificationList.isRefreshing = false
            }
        }
        // swipe to refresh
        binding.swipeRefreshLayoutNotificationList.setOnRefreshListener {
            notificationListViewModel.getNotifications(db)
        }

        binding.fabAddNotification.setOnClickListener { findNavController().navigate(R.id.action_notificationListFragment_to_addNotificationFragment) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val COLL_NOTIFICATIONS = "Gifts"
    }
}