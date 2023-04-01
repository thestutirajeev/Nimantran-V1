package com.example.nimantran.ui.main.clientGifts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentSelectMyGuestForGiftBinding
import com.google.firebase.firestore.FirebaseFirestore

class SelectGuestForGiftFragment : Fragment() {
    private var _binding: FragmentSelectMyGuestForGiftBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val myGiftsViewModel: MyGiftsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_my_guest_for_gift, container, false)
        Toast.makeText(context, "Select an option above.", Toast.LENGTH_SHORT).show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSendGift.setOnClickListener {
            findNavController().navigate(R.id.action_selectGuestForGiftFragment_to_getOrderDetailsFragment)
        }
        binding.radioButtonSendToMe.setOnClickListener{
            if(binding.radioButtonSendToMe.isChecked){
                binding.radioButtonSendToGuest.isChecked = false
                //make containerSendToMe visible
                binding.containerSendToMe.visibility = View.VISIBLE
                binding.containerSendToGuest.visibility = View.GONE
                binding.buttonSendGift.isEnabled = true
            }
            else{
                binding.radioButtonSendToGuest.callOnClick()
            }
        }
        binding.radioButtonSendToGuest.setOnClickListener{
            if(binding.radioButtonSendToGuest.isChecked){
                binding.radioButtonSendToMe.isChecked = false
                //make containerSendToMe visible
                binding.containerSendToMe.visibility = View.GONE
                binding.containerSendToGuest.visibility = View.VISIBLE
                binding.buttonSendGift.isEnabled = true
            }
            else{
                binding.radioButtonSendToMe.callOnClick()
            }
        }
    }

    companion object {
    }
}