package com.example.nimantran.ui.admin.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.admin.Notification
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp

class AddNotificationViewModel : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _isSaved = MutableLiveData(false)
    val isSaved: MutableLiveData<Boolean> = _isSaved

    fun saveNotification(
        db: FirebaseFirestore,
        body: String,
        subject: String
    ) {
        _isLoading.value = true

        if (!validateNotification(body, subject)) {
            _isLoading.value = false
            _isSaved.value = false
        } else {
            val notification = Notification(body, subject)
            db.collection(NotificationListFragment.COLL_NOTIFICATIONS).add(notification).addOnSuccessListener {
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
    private fun validateNotification(
        body: String,
        subject: String,
    ): Boolean {
        return body.isNotEmpty() && subject.isNotEmpty()
    }
}