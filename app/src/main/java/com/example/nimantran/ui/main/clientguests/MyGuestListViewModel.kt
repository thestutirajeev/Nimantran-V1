package com.example.nimantran.ui.main.clientguests

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.user.Guest
import com.google.firebase.firestore.FirebaseFirestore

class MyGuestListViewModel : ViewModel() {
    private val _guests = MutableLiveData<List<Guest>>()
    val guests: MutableLiveData<List<Guest>> = _guests

    private val _selectedGuest = MutableLiveData<Guest?>()
    val selectedGuest: MutableLiveData<Guest?> = _selectedGuest

    fun getGuests(db: FirebaseFirestore) {
        loadGuests(db)
        _selectedGuest.value = null
    }

    private fun loadGuests(db: FirebaseFirestore) {
        // fetch data from firebase firestore
        db.collection(MyGuestListFragment.COLL_GUESTS).get().addOnFailureListener {
            Log.e("MyGuestListViewModel", "Error fetching guests ${it.message}")
        }.addOnCanceledListener {
            Log.e("MyGuestListViewModel", "Cancelled fetching guests")
        }.addOnSuccessListener {
            val guestsLoaded = it.toObjects(Guest::class.java)
            _guests.value = guestsLoaded
            Log.d("MyGuestListViewModel", "Guest loaded ${guestsLoaded.size}")
        }
    }

    fun selectGuest(guest: Guest) {
        _selectedGuest.value = guest
    }

    fun deselectGuest() {
        _selectedGuest.value = null
    }

}