package com.example.nimantran.ui.admin.notification

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.admin.Notification
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ReadNotificationViewModel: ViewModel() {
    private val _notification = MutableLiveData<Notification>()
    val notification: MutableLiveData<Notification> = _notification

    fun getMyNotification(db: FirebaseFirestore) {
        loadMyNotification(db)
    }

    private fun loadMyNotification(db: FirebaseFirestore) {
        // fetch data from firebase firestore
        db.collection(ReadNotificationFragment.COLL_NOTIFICATIONS).get().addOnFailureListener {
            Log.e("ReadNotificationViewModel", "Error fetching notifications ${it.message}")
        }.addOnCanceledListener {
            Log.e("ReadNotificationViewModel", "Cancelled fetching notifications")
        }.addOnSuccessListener {
            val notificationsLoaded = it.toObjects(Notification::class.java)
//            _notification.value = notificationsLoaded
            Log.d("ReadNotificationViewModel", "Notification loaded ${notificationsLoaded.size}")
        }
    }
}