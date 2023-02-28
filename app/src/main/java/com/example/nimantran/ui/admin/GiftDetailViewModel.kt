package com.example.nimantran.ui.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.Gift
import com.example.nimantran.ui.admin.GiftListFragment.Companion.COLL_GIFTS
import com.google.firebase.firestore.FirebaseFirestore

class GiftDetailViewModel : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _isSaved = MutableLiveData(false)
    val isSaved: MutableLiveData<Boolean> = _isSaved

    fun saveGift(
        db: FirebaseFirestore,
        item: String,
        price: String,
        quantity: String,
        description: String
    ) {
        _isLoading.value = true

        if (!validateProduct(item, quantity, description, price)) {
            _isLoading.value = false
            _isSaved.value = false
        } else {
            val gift = Gift(item, description, quantity = quantity.toInt(), price = price.toDouble())
            db.collection(COLL_GIFTS).add(gift).addOnSuccessListener {
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

    private fun validateProduct(
        item: String,
        quantity: String,
        description: String,
        price: String
    ): Boolean {
        return item.isNotEmpty() && quantity.isNotEmpty() && description.isNotEmpty() && price.isNotEmpty()
    }
}