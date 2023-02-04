package com.alpharays.chatup.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import com.alpharays.chatup.util.MainActivity
import com.alpharays.chatup.util.composables.nav1.ScreenA
import com.google.firebase.auth.FirebaseAuth


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var auth: FirebaseAuth

    fun signOut(navController: NavController) {
        with (MainActivity.prefs.edit()) {
            putBoolean("is_logged_in", false)
            commit()
        }
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        navController.navigate(ScreenA.LoginScreenA.route)
    }
}