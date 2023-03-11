package com.example.nimantran.ui.admin.gift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nimantran.R
import com.example.nimantran.adapters.GiftAdapter
import com.example.nimantran.databinding.FragmentGiftListBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GiftListFragment : Fragment() {
    private var _binding: FragmentGiftListBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val giftListViewModel: GiftListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gift_list, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        giftListViewModel.getGifts(db) // fetch data only
        giftListViewModel.gifts.observe(viewLifecycleOwner) { gifts ->
            if (gifts.isNotEmpty()) {
                binding.recyclerViewGiftList.adapter = GiftAdapter(requireActivity()) {
                    giftListViewModel.selectGift(it)
                    findNavController().navigate(R.id.action_giftListFragment_to_addGiftFragment)
                }
                (binding.recyclerViewGiftList.adapter as GiftAdapter).submitList(gifts)
            } else {
                binding.recyclerViewGiftList.visibility = View.GONE
            }
            if (binding.swipeRefreshLayoutGiftList.isRefreshing) {
                binding.swipeRefreshLayoutGiftList.isRefreshing = false
            }
        }
        // swipe to refresh
        binding.swipeRefreshLayoutGiftList.setOnRefreshListener {
            giftListViewModel.getGifts(db)
        }

        binding.fabAddGift.setOnClickListener { findNavController().navigate(R.id.action_giftListFragment_to_addGiftFragment) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val COLL_GIFTS = "Gifts"
    }
}