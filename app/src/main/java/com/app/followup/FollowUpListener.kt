package com.app.followup

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class FollowUpListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        // Implement your logic here
    }

    // You can also override other methods like onNotificationRemoved if needed
}
