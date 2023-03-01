package com.example.nimantran.ui.admin.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentAddNotificationBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp

class AddNotificationFragment : Fragment() {

    private var _binding: FragmentAddNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val viewModel = AddNotificationViewModel() by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_notification, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModelAddNotification = viewModel

        viewModel.isSaved.observe(viewLifecycleOwner) { state ->
            if (state) {
                findNavController().navigateUp() // Navigate back to NotificationListFragment
            } else {
                binding.addNotificationContainer.isEnabled = true
                //buttonSendTo
                //buttonSendAll
            }
        }

        binding.apply {
            buttonSendTo.setOnClickListener {
                addNotificationContainer.isEnabled = false
                val subject = editTextNotificationSubject.text.toString().trim()
                val body = editTextNotificationBody.text.toString().trim()
                val to = "Few users"
                val date = Timestamp(System.currentTimeMillis())
                buttonSendTo.text = "Sending..."
                viewModel.sendNotificationTo(db, subject, body, to, date)  // Send notification to Firestore
            }
            buttonSendAll.setOnClickListener {
                addNotificationContainer.isEnabled = false
                val subject = editTextNotificationSubject.text.toString().trim()
                val body = editTextNotificationBody.text.toString().trim()
                val to = "All users"
                val date = Timestamp(System.currentTimeMillis())
                buttonSendAll.text = "Sending..."
                viewModel.sendNotificationToAll(db, subject, body, to, date)  // Send notification to Firestore
            }
        }
    }
}