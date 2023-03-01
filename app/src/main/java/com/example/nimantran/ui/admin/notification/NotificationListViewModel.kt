package com.example.nimantran.ui.admin.notification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.Notification
import com.google.firebase.firestore.FirebaseFirestore

class NotificationListViewModel : ViewModel() {
    private val _notifications = MutableLiveData<List<Notification>>()
    val notifications: LiveData<List<Notification>> = _notifications

    private val _selectedNotification = MutableLiveData<Notification>()
    val selectedNotification: LiveData<Notification> = _selectedNotification

    fun getNotifications(db: FirebaseFirestore){
        loadNotifications(db)
    }

    private fun loadNotifications(db: FirebaseFirestore){
        // fetch data from firebase firestore
        db.collection(NotificationListFragment.COLL_NOTIFICATIONS).get().addOnFailureListener {
            Log.e("NotificationListViewModel", "Error fetching notifications ${it.message}")
        }.addOnCanceledListener {
            Log.e("NotificationListViewModel", "Cancelled fetching notifications")
        }.addOnSuccessListener {
            val notificationsLoaded = it.toObjects(Notification::class.java)
            _notifications.value = notificationsLoaded
            Log.d("NotificationListViewModel", "Notification loaded ${notificationsLoaded.size}")
        }
    }

    fun selectNotification(notification: Notification){
        _selectedNotification.value = notification
    }
}