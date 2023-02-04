package com.alpharays.chatup.util

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alpharays.chatup.util.composables.nav2.NavigationB

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {}
        setContent {
            NavigationB()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    NavigationB()
}