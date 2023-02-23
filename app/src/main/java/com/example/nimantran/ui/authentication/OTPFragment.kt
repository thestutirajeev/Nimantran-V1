package com.example.nimantran.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.nimantran.MainActivity
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentOtpBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import java.util.concurrent.TimeUnit

class OTPFragment : Fragment() {

    private var _binding: FragmentOtpBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var OTP: String
    private lateinit var resendingToken: ForceResendingToken
    private lateinit var phoneNumber: String
    private lateinit var otp1: EditText
    private lateinit var otp2: EditText
    private lateinit var otp3: EditText
    private lateinit var otp4: EditText
    private lateinit var otp5: EditText
    private lateinit var otp6: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = OTPFragmentArgs.fromBundle(requireArguments())
        phoneNumber = bundle.phone
        OTP = bundle.otp
        //resendingToken = bundle.token
        init()
        addTextChangeListner()
        resendOtpTextViewVisibility()
        binding.progressBar2.visibility = View.INVISIBLE

        binding.buttonOtp.setOnClickListener {
            val typedOTP = otp1.text.toString() + otp2.text.toString() + otp3.text.toString() + otp4.text.toString() + otp5.text.toString() + otp6.text.toString()
            if(typedOTP.isNotEmpty() && typedOTP.length == 6) {
                val credential = PhoneAuthProvider.getCredential(OTP, typedOTP)
                binding.progressBar2.visibility = View.VISIBLE
                signInWithPhoneAuthCredential(credential)

            }else{
                Toast.makeText(context, "Please enter valid OTP", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textViewResendOtp.setOnClickListener {
            resendVerificationCode()
            resendOtpTextViewVisibility()
        }
    }

    private fun resendOtpTextViewVisibility(){
        otp1.setText("")
        otp2.setText("")
        otp3.setText("")
        otp4.setText("")
        otp5.setText("")
        otp6.setText("")
        binding.textViewResendOtp.visibility = View.INVISIBLE
        binding.textViewResendOtp.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            binding.textViewResendOtp.visibility = View.VISIBLE
            binding.textViewResendOtp.isEnabled = true
        }, 60000)
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    binding.progressBar2.visibility = View.INVISIBLE
                    Toast.makeText(activity, "Signed In", Toast.LENGTH_SHORT).show()
                    sendToMain()
                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Toast.makeText(activity, "Sign In Failed", Toast.LENGTH_SHORT).show()
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(activity, "Invalid OTP", Toast.LENGTH_SHORT).show()
                    }
                    // Update UI
                }
            }
    }

    private fun sendToMain() {
        startActivity(Intent(activity, MainActivity::class.java))
        requireActivity().finish()
    }

    private fun addTextChangeListner(){
        otp1.addTextChangedListener(EditTextWatcher(otp1))
        otp2.addTextChangedListener(EditTextWatcher(otp2))
        otp3.addTextChangedListener(EditTextWatcher(otp3))
        otp4.addTextChangedListener(EditTextWatcher(otp4))
        otp5.addTextChangedListener(EditTextWatcher(otp5))
        otp6.addTextChangedListener(EditTextWatcher(otp6))
    }
    private fun init(){
        auth = FirebaseAuth.getInstance()
    }
    inner class EditTextWatcher(private val view: View) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            val text = p0.toString()
            when (view.id) {
                R.id.otpEditText1 -> if (text.length == 1) otp2.requestFocus()
                R.id.otpEditText2 -> if (text.length == 1) otp3.requestFocus() else if (text.isEmpty()) otp1.requestFocus()
                R.id.otpEditText3 -> if (text.length == 1) otp4.requestFocus() else if (text.isEmpty()) otp2.requestFocus()
                R.id.otpEditText4 -> if (text.length == 1) otp5.requestFocus() else if (text.isEmpty()) otp3.requestFocus()
                R.id.otpEditText5 -> if (text.length == 1) otp6.requestFocus() else if (text.isEmpty()) otp4.requestFocus()
                R.id.otpEditText6 -> if (text.isEmpty()) otp5.requestFocus()
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun resendVerificationCode(){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .setForceResendingToken(resendingToken) // ForceResendingToken from callbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Toast.makeText(activity, "Invalid Phone Number", Toast.LENGTH_SHORT).show()
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(activity, "SMS Quota Exceeded", Toast.LENGTH_SHORT).show()
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            // Save verification ID and resending token so we can use them later
            OTP = verificationId
            resendingToken = token
        }
    }

        companion object {
    }
}