package com.example.nimantran.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.navigation.fragment.findNavController
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentGetDetailsBinding

class GetDetailsFragment : Fragment() {
    private var _binding: FragmentGetDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewPrivacyPolicy.setOnClickListener {
            findNavController().navigate(R.id.action_getDetailsFragment_to_privacyPolicyFragment)
        }
        binding.buttonSave.setOnClickListener {
            if(binding.checkBoxPrivacyPolicy.isChecked == false){
                makeText(requireContext(), "Please accept the privacy policy", Toast.LENGTH_SHORT).show()
            }
            else{
                //code to save the details
            }
        }
    }

    companion object {
    }
}