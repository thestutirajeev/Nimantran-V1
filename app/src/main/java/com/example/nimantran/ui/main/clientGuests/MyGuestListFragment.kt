package com.example.nimantran.ui.main.clientGuests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nimantran.R
import com.example.nimantran.adapters.MyGuestListAdapter
import com.example.nimantran.databinding.FragmentMyGuestListBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyGuestListFragment : Fragment() {
    private var _binding: FragmentMyGuestListBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val myGuestListViewModel: MyGuestViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_guest_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myGuestListViewModel.getGuests(db) // fetch data only
        myGuestListViewModel.guests.observe(viewLifecycleOwner) { guests ->
            if (guests.isNotEmpty()) {
                binding.recyclerViewMyGuestList.adapter =
                    MyGuestListAdapter(requireActivity(), {
                        myGuestListViewModel.selectGuest(it)
                        val dir = MyGuestListFragmentDirections.actionMyGuestListFragmentToEditGuestFragment(it.id)
                        findNavController().navigate(dir)
                    })

                (binding.recyclerViewMyGuestList.adapter as MyGuestListAdapter).submitList(
                    guests
                )
            } else {
                binding.recyclerViewMyGuestList.visibility = View.GONE
            }
            if (binding.swipeRefreshLayoutMyGuestList.isRefreshing) {
                binding.swipeRefreshLayoutMyGuestList.isRefreshing = false
            }
        }


        binding.fabMyGuestList.setOnClickListener{


        }


        myGuestListViewModel.guests.observe(viewLifecycleOwner) { guests ->
            if (guests.isNotEmpty()) {
                binding.recyclerViewMyGuestList.adapter =
                    MyGuestListAdapter(requireActivity(), {
                        myGuestListViewModel.selectGuest(it)
                    })

                (binding.recyclerViewMyGuestList.adapter as MyGuestListAdapter).submitList(
                    guests
                )
            } else {
                binding.recyclerViewMyGuestList.visibility = View.GONE
            }
            if (binding.swipeRefreshLayoutMyGuestList.isRefreshing) {
                binding.swipeRefreshLayoutMyGuestList.isRefreshing = false
            }
        }

        binding.swipeRefreshLayoutMyGuestList.setOnRefreshListener {
            myGuestListViewModel.getGuests(db)
        }

        binding.cardViewAddGuest.setOnClickListener{
            val dir = MyGuestListFragmentDirections.actionMyGuestListFragmentToAddMyGuestFragment()
            findNavController().navigate(dir)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val COLL_MY_GUESTS = "myGuests"
    }
}