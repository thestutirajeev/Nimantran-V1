package com.example.nimantran.ui.main.clientGiftOrders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nimantran.R
import com.example.nimantran.adapters.GiftAdapter
import com.example.nimantran.databinding.FragmentMyGiftsBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyGiftsFragment : Fragment() {
    private var _binding: com.example.nimantran.databinding.FragmentMyGiftsBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val myGiftsViewModel: MyGiftsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyGiftsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myGiftsViewModel.getMyGifts(db) // fetch data only
        myGiftsViewModel.myGifts.observe(viewLifecycleOwner) { gifts ->
            if (gifts.isNotEmpty()) {
                binding.recyclerViewMyGifts.adapter = GiftAdapter(requireActivity()) {
                    myGiftsViewModel.selectMyGift(it)
                    findNavController().navigate(R.id.action_myGiftsFragment_to_myGiftDetailsFragment)
                }
                (binding.recyclerViewMyGifts.adapter as GiftAdapter).submitList(gifts)
            } else {
                binding.recyclerViewMyGifts.visibility = View.GONE
            }
            if (binding.swipeRefreshLayoutMyGifts.isRefreshing) {
                binding.swipeRefreshLayoutMyGifts.isRefreshing = false
            }
        }

        binding.imageViewMyOrders.setOnClickListener {
            findNavController().navigate(R.id.action_myGiftsFragment_to_myOrdersFragment)
        }

    }

    companion object {
        const val COLL_MY_GIFTS = "gifts"
    }
}