package com.example.nimantran.ui.main.clientGuests

import android.accounts.Account
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.user.Guest
import com.google.firebase.firestore.FirebaseFirestore
import contacts.core.*
import contacts.core.entities.EventEntity

class MyGuestViewModel : ViewModel() {
    private val _guests = MutableLiveData<List<Guest>>()
    val guests: MutableLiveData<List<Guest>> = _guests

    private val _selectedGuest = MutableLiveData<Guest?>()
    val selectedGuest: MutableLiveData<Guest?> = _selectedGuest

    private val _isLoading = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _isSaved = MutableLiveData(false)
    val isSaved: MutableLiveData<Boolean> = _isSaved



    fun getGuests(db: FirebaseFirestore) {
        loadGuests(db)
        deselectGuest()
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

/*
    val contacts = Contacts(context)
        .query()
        .where {
            (Name.GivenName startsWith "leo") and
                    (ContactsContract.CommonDataKinds.Email.Address { endsWith("gmail.com") or endsWith("hotmail.com") }) and
                    (Address.Country equalToIgnoreCase "us") and
                    (Event { (Date lessThan Date().toWhereString()) and (Type equalTo EventEntity.Type.BIRTHDAY) }) and
                    (Contact.Options.Starred equalTo true) and
                    (ContactsContract.CommonDataKinds.Nickname.Name equalTo "DarEdEvil") and
                    (ContactsContract.CommonDataKinds.Organization.Company `in` listOf("facebook", "FB")) and
                    (ContactsContract.CommonDataKinds.Note.Note.isNotNullOrEmpty())
        }
        .accounts(
            Account("john.doe@gmail.com", "com.google"),
            Account("john.doe@myspace.com", "com.myspace"),
        )
        .include { setOf(
            Contact.Id,
            Contact.DisplayNamePrimary,
            ContactsContract.CommonDataKinds.Phone.Number
        ) }
        .orderBy(ContactsFields.DisplayNamePrimary.desc())
        .offset(0)
        .limit(5)
        .find()

  */
}