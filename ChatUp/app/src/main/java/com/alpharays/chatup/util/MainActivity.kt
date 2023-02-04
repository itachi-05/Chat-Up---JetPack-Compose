package com.alpharays.chatup.util

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alpharays.chatup.util.composables.nav1.NavigationA
import com.alpharays.chatup.util.composables.nav2.NavigationB
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var prefs: SharedPreferences
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {}
        setContent {
            prefs = PreferenceManager.getDefaultSharedPreferences(this)
            auth = FirebaseAuth.getInstance()
            val isLoggedIn = prefs.getBoolean("is_logged_in", false)
            if (isLoggedIn) {
                Log.i("Logged_In",auth.currentUser?.phoneNumber.toString())
                NavigationB()
            } else {
                Log.i("Not_Logged_In",auth.currentUser?.phoneNumber.toString())
                NavigationA()
            }
        }
    }

    override fun onBackPressed() {
        val isLoggedIn = prefs.getBoolean("is_logged_in", false)
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
fun LoginPreview() {
    NavigationA()
}