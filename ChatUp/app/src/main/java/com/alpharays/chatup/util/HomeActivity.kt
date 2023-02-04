package com.alpharays.chatup.util

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alpharays.chatup.util.composables.nav2.NavigationB
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {}
        auth = FirebaseAuth.getInstance()
        setContent {
            NavigationB()
        }
    }

    override fun onBackPressed() {
        val isLoggedIn = MainActivity.prefs.getBoolean("is_logged_in", false)
        if (isLoggedIn && auth.currentUser != null) {
            Log.i("AA","00")
            // Do nothing
        } else {
            Log.i("AA","11")
            super.onBackPressed()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    NavigationB()
}