package com.app.followup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.AlertDialog
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FollowUpApp()
        }
    }
}

@Composable
fun FollowUpApp() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = { Text("Follow Up") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Replace with your own action",
                        actionLabel = "Action"
                    )
                }
            }) {
                Text("Action")
            }
        }
    ) { innerPadding ->
        NotificationAccessCheck(context, innerPadding)
    }
}

@Composable
fun NotificationAccessCheck(context: Context, innerPadding: PaddingValues) {
    val isAccessEnabled = remember { isNotificationServiceEnabled(context) }

    if (!isAccessEnabled.value) {
        ShowNotificationAccessDialog(context)
    }
}

@Composable
fun ShowNotificationAccessDialog(context: Context) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Notification Access Required") },
            text = { Text("Notification access is required for Follow Up to function properly.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
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
                        openDialog.value = false
                        (context as? Activity)?.finish()
                    }
                ) {
                    Text("Exit App")
                }
            }
        )
    }
}

fun isNotificationServiceEnabled(context: Context): MutableState<Boolean> {
    val enabledNotificationListeners =
        Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
    return mutableStateOf(enabledNotificationListeners?.contains(context.packageName) == true)
}

@Preview(showBackground = true)
@Composable
fun PreviewFollowUpApp() {
    FollowUpApp()
}