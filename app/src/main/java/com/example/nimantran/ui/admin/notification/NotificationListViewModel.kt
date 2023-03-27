package com.example.nimantran.ui.admin.notification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.admin.Notification
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NotificationListViewModel : ViewModel() {
    private val _notifications = MutableLiveData<List<Notification>>()
    val notifications: LiveData<List<Notification>> = _notifications

    private val _selectedNotification = MutableLiveData<Notification?>()
    val selectedNotification: MutableLiveData<Notification?> = _selectedNotification

    fun getNotifications(db: FirebaseFirestore) {
        loadNotifications(db)
        _selectedNotification.value = null
    }

    private fun loadNotifications(db: FirebaseFirestore) {
        // fetch data from firebase firestore
        db.collection(NotificationListFragment.COLL_NOTIFICATIONS).orderBy("date", Query.Direction.DESCENDING)
            .get().addOnSuccessListener {
                val notificationsLoaded = it.toObjects(Notification::class.java)
                _notifications.value = notificationsLoaded
            }.addOnFailureListener {
                Log.e("NotificationListViewModel", "Error getting notifications ${it.message}")
            }.addOnCanceledListener {
                Log.e("NotificationListViewModel", "Cancelled getting notifications")
            }
    }

    fun selectNotification(notification: Notification) {
        _selectedNotification.value = notification
        Log.e("NotificationListViewModel", "Selected notification ${notification.id}")
    }

    fun deselectNotification() {
        _selectedNotification.value = null
    }

    fun deleteNotification(db: FirebaseFirestore, notification: Notification) {
        Log.d("NotificationListViewModel", "Deleting notification ${notification.id}")
        db.collection(NotificationListFragment.COLL_NOTIFICATIONS)
            .whereEqualTo("id", notification.id).get().addOnFailureListener {
                Log.e("NotificationListViewModel", "Error deleting notification ${it.message}")
            }.addOnCanceledListener {
                Log.e("NotificationListViewModel", "Cancelled deleting notification")
            }.addOnSuccessListener {
                try {
                    db.collection(NotificationListFragment.COLL_NOTIFICATIONS)
                        .document(it.documents[0].id).delete()
                } catch (e: Exception) {
                    Log.e("NotificationListViewModel", "Error deleting notification ${e.message}")
                }
            }
    }
}