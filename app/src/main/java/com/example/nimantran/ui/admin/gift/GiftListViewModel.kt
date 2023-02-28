package com.example.nimantran.ui.admin.gift

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.Gift
import com.example.nimantran.ui.admin.gift.GiftListFragment.Companion.COLL_GIFTS
import com.google.firebase.firestore.FirebaseFirestore

class GiftListViewModel: ViewModel() {
    private val _gifts = MutableLiveData<List<Gift>>()
    val gifts: LiveData<List<Gift>> = _gifts

    private val _selectedGift = MutableLiveData<Gift>()
    val selectedGift: LiveData<Gift> = _selectedGift

    fun getGifts(db: FirebaseFirestore){
        loadGifts(db)
    }

    private fun loadGifts(db: FirebaseFirestore){
        // fetch data from firebase firestore
        db.collection(COLL_GIFTS).get().addOnFailureListener {
            Log.e("GiftListViewModel", "Error fetching gifts ${it.message}")
        }.addOnCanceledListener {
            Log.e("GiftListViewModel", "Cancelled fetching gifts")
        }.addOnSuccessListener {
            val giftLoaded = it.toObjects(Gift::class.java)
            _gifts.value = giftLoaded
            Log.d("GiftListViewModel", "Gifts loaded ${giftLoaded.size}")
        }
    }

    fun selectGift(gift: Gift){
        _selectedGift.value = gift
    }
}