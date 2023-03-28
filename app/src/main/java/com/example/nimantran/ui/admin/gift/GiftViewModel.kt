package com.example.nimantran.ui.admin.gift

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.admin.Gift
import com.example.nimantran.ui.admin.gift.GiftListFragment.Companion.COLL_GIFTS
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class GiftViewModel : ViewModel() {
    private val _gifts = MutableLiveData<List<Gift>>()
    val gifts: LiveData<List<Gift>> = _gifts

    private val _selectedGift = MutableLiveData<Gift>()
    val selectedGift: LiveData<Gift> = _selectedGift

    private val _isLoading = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _isSaved = MutableLiveData(false)
    val isSaved: MutableLiveData<Boolean> = _isSaved

    private val _isImgUploaded = MutableLiveData(false)
    val isImgUploaded: MutableLiveData<Boolean> = _isImgUploaded

    private val _downloadUri = MutableLiveData<Uri>()
    val downloadUri: MutableLiveData<Uri> = _downloadUri

    fun saveGift(
        db: FirebaseFirestore,
        item: String,
        price: String,
        quantity: String,
        description: String
    ) {
        _isLoading.value = true

        if (!validateGift(item, quantity, description, price)) {
            _isLoading.value = false
            _isSaved.value = false
        } else {
            val gift =
                Gift(item, description, quantity = quantity.toInt(), price = price.toDouble())
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

    private fun validateGift(
        item: String,
        quantity: String,
        description: String,
        price: String
    ): Boolean {
        return item.isNotEmpty() && quantity.isNotEmpty() && description.isNotEmpty() && price.isNotEmpty()
    }

    fun getGifts(db: FirebaseFirestore) {
        loadGifts(db)
    }

    private fun loadGifts(db: FirebaseFirestore) {
        // fetch data from firebase firestore
        db.collection(COLL_GIFTS).get().addOnFailureListener {
            Log.e("GiftViewModel", "Error fetching gifts ${it.message}")
        }.addOnCanceledListener {
            Log.e("GiftViewModel", "Cancelled fetching gifts")
        }.addOnSuccessListener {
            val giftLoaded = it.toObjects(Gift::class.java)
            _gifts.value = giftLoaded
            Log.d("GiftViewModel", "Gifts loaded ${giftLoaded.size}")
        }
    }

    fun selectGift(gift: Gift) {
        _selectedGift.value = gift
    }

    @SuppressLint("Range")
    fun getFileNameFromUri(context: Context, uri: Uri): String? {
        val fileName: String?
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        fileName = cursor?.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        cursor?.close()
        return fileName
    }

    fun uploadToFirebase(context: Context, storage: FirebaseStorage, imageUri: Uri?) {
        val storageRef = storage.reference
        imageUri?.let { uri ->
            val filename = context.let { getFileNameFromUri(it, uri) }
            val fileRef = storageRef.child(filename.toString())
            val uploadTask = fileRef.putFile(imageUri)
            uploadTask.addOnFailureListener {
                _isImgUploaded.value = false
                it.message?.let {

                }
            }.continueWithTask { task ->
                if (!task.isSuccessful) {
                    _isImgUploaded.value = false
                }
                fileRef.downloadUrl
            }.addOnCompleteListener { task2 ->
                if (task2.isSuccessful) {
                    _downloadUri.value = task2.result
                    _isImgUploaded.value = true

                } else {
                    _isImgUploaded.value = false
                }
            }
        }
    }


}