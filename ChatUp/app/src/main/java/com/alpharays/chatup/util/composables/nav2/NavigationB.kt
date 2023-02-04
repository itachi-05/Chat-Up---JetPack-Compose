package com.alpharays.chatup.util.composables.nav2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alpharays.chatup.util.composables.nav1.LoginComp
import com.alpharays.chatup.util.composables.nav1.ScreenA

@Composable
fun NavigationB() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenB.HomeScreenB.route) {
        composable(route = ScreenB.HomeScreenB.route) {
            HomeComp(
                navController = navController
            )
        }
        composable(route = ScreenA.LoginScreenA.route) {
            LoginComp(navController = navController)
        }
    }
}
