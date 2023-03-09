package com.example.nimantran.ui.admin.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nimantran.R
import com.example.nimantran.adapters.UserListAdapter
import com.example.nimantran.databinding.FragmentUserListBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val userListViewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userListViewModel.getClients(db) // fetch data only
        userListViewModel.clients.observe(viewLifecycleOwner) { clients ->
            if (clients.isNotEmpty()) {
                binding.recyclerViewUserList.adapter =
                    UserListAdapter(requireActivity(), {
                        userListViewModel.selectClient(it)
                    })

                (binding.recyclerViewUserList.adapter as UserListAdapter).submitList(
                    clients
                )
            } else {
                binding.recyclerViewUserList.visibility = View.GONE
            }
            if (binding.swipeRefreshLayoutUserList.isRefreshing) {
                binding.swipeRefreshLayoutUserList.isRefreshing = false
            }
        }
    }

    companion object {
        const val COLL_CLIENTS = "clients"
    }
}