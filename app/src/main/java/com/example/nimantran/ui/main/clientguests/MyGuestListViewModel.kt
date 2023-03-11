package com.example.nimantran.ui.main.clientguests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.Guest

class MyGuestListViewModel : ViewModel() {
    private val _guests = MutableLiveData<List<Guest>>()
}