package com.example.nimantran.ui.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GiftListViewModel: ViewModel() {
    private val _gifts = MutableLiveData<List<Gift>>()
}