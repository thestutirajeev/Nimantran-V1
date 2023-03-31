package com.example.nimantran.ui.main.settings

import android.net.Uri
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.admin.Client
import com.example.nimantran.ui.main.settings.MyProfileFragment.Companion.COLL_CLIENT
import com.google.firebase.firestore.FirebaseFirestore

class MyProfileViewModel : ViewModel(){
    private val _client = MutableLiveData<Client>()
    val client: MutableLiveData<Client> = _client

    private val _selectedClient = MutableLiveData<Client>()
    val selectedClient: MutableLiveData<Client> = _selectedClient

    private val _isLoading = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _isSaved = MutableLiveData(false)
    val isSaved: MutableLiveData<Boolean> = _isSaved

    private val _isImgUploaded = MutableLiveData(false)
    val isImgUploaded: MutableLiveData<Boolean> = _isImgUploaded

    private val _downloadUri = MutableLiveData<Uri>()
    val downloadUri: MutableLiveData<Uri> = _downloadUri

    fun getClient(db: FirebaseFirestore){
        _isLoading.value = true
        db.collection(COLL_CLIENT).document("client").get().addOnSuccessListener {
            _isLoading.value = false
            _client.value = it.toObject(Client::class.java)
        }.addOnFailureListener {
            _isLoading.value = false
        }.addOnCanceledListener {
            _isLoading.value = false
        }
    }

    fun updateClient(db: FirebaseFirestore){
        _isLoading.value = true
        db.collection(COLL_CLIENT).document("client").set(_selectedClient.value!!).addOnSuccessListener {
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

    fun uploadToFirebase(requireActivity: FragmentActivity, storage: Any, uri: Uri?) {
            TODO("Not yet implemented")
    }
}