package com.example.nimantran.ui.main.clientGuests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.user.Guest
import com.google.firebase.firestore.FirebaseFirestore


class AddMyGuestViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _isSaved = MutableLiveData(false)
    val isSaved: MutableLiveData<Boolean> = _isSaved

    fun saveGuest(
        db: FirebaseFirestore,
        name: String,
        phone: String,
        address: String,
        id: String,
    )  {
        _isLoading.value = true

        if (!validateGuest(name, phone, address, id)) {
            _isLoading.value = false
            _isSaved.value = false
        } else {
            val guest = Guest()
            db.collection(AddMyGuestFragment.COLL_MY_GUESTS).add(guest).addOnSuccessListener {
                _isLoading.value = false
                _isSaved.value = true
            }.addOnFailureListener {
                _isLoading.value = false
                _isSaved.value = false
            }.addOnCanceledListener {
                _isLoading.value = false
                _isSaved.value = false
            }
        }
    }

    private fun validateGuest(
        name: String,
        phone: String,
        address: String,
        id: String,
    ): Boolean {
        return name.isNotEmpty() && phone.isNotEmpty() && id.isNotEmpty()
    }
}