package com.app.followup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    private var isAccessEnabled = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FollowUpApp(isAccessEnabled)
        }
    }

    override fun onResume() {
        super.onResume()
        isAccessEnabled.value = isNotificationServiceEnabled(this)
    }

    private fun isNotificationServiceEnabled(context: Context): Boolean {
        val enabledNotificationListeners =
            Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
        return enabledNotificationListeners?.contains(context.packageName) == true
    }
}

@Composable
fun FollowUpApp(isAccessEnabled: MutableState<Boolean>) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = { Text("Follow Up") }) },
        floatingActionButton = { /* ... */ }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (!isAccessEnabled.value) {
                ShowNotificationAccessDialog(context)
            }
        }
    }
}

@Composable
fun ShowNotificationAccessDialog(context: Context) {
    AlertDialog(
        // Do not allow dismissing by tapping outside the dialog.
        onDismissRequest = {
        },
        title = { Text("Notification Access Required") },
        text = { Text("Notification access is required for Follow Up to function properly.") },
        confirmButton = {
            TextButton(
                onClick = {
                    val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                    context.startActivity(intent)
                }
            ) {
                Text("Go to Settings")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    (context as? Activity)?.finish()
                }
            ) {
                Text("Exit App")
            }
        }
    )
}

@Preview(showBackground = true, name = "Access Enabled")
@Composable
fun PreviewFollowUpAppAccessEnabled() {
    val isAccessEnabled = remember { mutableStateOf(true) }
    FollowUpApp(isAccessEnabled)
}

@Preview(showBackground = true, name = "Access Not Enabled")
@Composable
fun PreviewFollowUpAppAccessNotEnabled() {
    val isAccessEnabled = remember { mutableStateOf(false) }
    FollowUpApp(isAccessEnabled)
}