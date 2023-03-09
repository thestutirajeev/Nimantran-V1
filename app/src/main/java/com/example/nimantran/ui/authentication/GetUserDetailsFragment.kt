package com.example.nimantran.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nimantran.MainActivity
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentGetUserDetailsBinding
import com.example.nimantran.models.Client
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.UUID

class GetUserDetailsFragment : Fragment() {
    private var _binding: FragmentGetUserDetailsBinding? = null
    private val binding get() = _binding!!
    private val prefs by lazy { requireActivity().getSharedPreferences("prefs", 0) }
    private lateinit var auth: FirebaseAuth
    private lateinit var userId: String
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fill the fields if user is already logged in
        val currUser = auth.currentUser
        if (currUser != null) {
            userId = currUser.uid
            currUser.phoneNumber?.let { binding.TextViewSetPhone.setText(it) }

        }

        binding.textViewPrivacyPolicy.setOnClickListener {
            findNavController().navigate(R.id.action_getDetailsFragment_to_privacyPolicyFragment)
        }
        binding.buttonSave.setOnClickListener {
            if (binding.checkBoxPrivacyPolicy.isChecked == false) {
                makeText(
                    requireContext(),
                    "Please accept the privacy policy",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                userId = currUser!!.uid
                val name = binding.TextViewSetName.text.toString()
                val phone = binding.TextViewSetPhone.text.toString()
                val gender = when (binding.genderGroup.checkedRadioButtonId) {
                    R.id.radioFemale -> "Female"
                    R.id.radioMale -> "Male"
                    else -> "Unknown"
                }
                // add validations
                if (name.isEmpty()) {
                    binding.TextViewSetName.error = "Please enter your name"
                    binding.TextViewSetName.requestFocus()
                    return@setOnClickListener
                }
                if (phone.isEmpty()) {
                    binding.TextViewSetPhone.error = "Please enter your phone number"
                    binding.TextViewSetPhone.requestFocus()
                    return@setOnClickListener
                }
                val client=Client(userId, name, phone, gender)
                db.collection(COLL_USER).document(userId).set(client).addOnSuccessListener {
                    prefs.edit().putBoolean("isFirstTime", false).apply()
                    // jump to main activity
                    startActivity(Intent(activity, MainActivity::class.java))
                    requireActivity().finish()
                }.addOnFailureListener {
                    makeText(
                        requireContext(),
                        "Something went wrong. Please try again later ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

    companion object {
        const val COLL_USER = "clients"
    }
}