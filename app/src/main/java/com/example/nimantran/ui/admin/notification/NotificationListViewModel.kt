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

    private val _selectedNotification = MutableLiveData<Notification?>()
    val selectedNotification: MutableLiveData<Notification?> = _selectedNotification

    fun getNotifications(db: FirebaseFirestore){
        loadNotifications(db)
        _selectedNotification.value = null
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

    fun deleteNotification(db: FirebaseFirestore, notification: Notification){
        db.collection(NotificationListFragment.COLL_NOTIFICATIONS).document(notification.id).delete().addOnFailureListener {
            Log.e("NotificationListViewModel", "Error deleting notification ${it.message}")
        }.addOnCanceledListener {
            Log.e("NotificationListViewModel", "Cancelled deleting notification")
        }.addOnSuccessListener {
            Log.d("NotificationListViewModel", "Notification deleted")
        }
     }
}