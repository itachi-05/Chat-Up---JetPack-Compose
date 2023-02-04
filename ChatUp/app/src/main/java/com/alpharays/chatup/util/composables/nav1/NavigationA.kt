package com.alpharays.chatup.util.composables.nav1

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alpharays.chatup.util.composables.nav2.HomeComp
import com.alpharays.chatup.util.composables.nav2.ScreenB

@Composable
fun NavigationA() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenA.LoginScreenA.route) {
        composable(route = ScreenA.LoginScreenA.route) {
            LoginComp(navController = navController)
        }
        composable(
            route = ScreenA.OtpScreenA.route + "/{phoneNumber}/{verificationId}/{token}",
            arguments = listOf(
                navArgument("phoneNumber") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("verificationId") {
                    type = NavType.StringType
                    defaultValue = "12345"
                    nullable = true
                },
                navArgument("token") {
                    type = NavType.StringType
                    defaultValue = "12345"
                    nullable = true
                }
            )
        ) { entry ->
            OtpComp(navController = navController,
                number = entry.arguments?.getString("phoneNumber"),
                verificationId = entry.arguments?.getString("verificationId"),
                token = entry.arguments?.getString("token")
            )
        }
        composable(
            route = ScreenB.HomeScreenB.route
        ) {
            HomeComp(navController = navController)
        }
    }
}