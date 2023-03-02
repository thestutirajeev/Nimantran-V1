package com.example.nimantran.ui.admin.gift

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentAddGiftBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddGiftFragment : Fragment() {
    private var _binding: FragmentAddGiftBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val viewModel: AddGiftViewModel by viewModels()

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
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_gift, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModelAddGift = viewModel

        viewModel.isSaved.observe(viewLifecycleOwner) { state ->
            if (state) {
                findNavController().navigateUp() // Navigate back to GiftListFragment
            } else {
                binding.addGiftContainer.isEnabled = true
                binding.buttonSaveGift.text = "Save"
            }
        }

        binding.apply {
            buttonSaveGift.setOnClickListener {
                addGiftContainer.isEnabled = false
                val item = editTextItemName.text.toString().trim()
                val price = editTextItemPrice.text.toString().trim()
                val quantity = editTextItemQuantity.text.toString().trim()
                val description = editTextItemDescription.text.toString().trim()
                buttonSaveGift.text = "Saving..."
                viewModel.saveGift(db, item, price, quantity, description)  // Save gift to Firestore
            }
        }
    }
}