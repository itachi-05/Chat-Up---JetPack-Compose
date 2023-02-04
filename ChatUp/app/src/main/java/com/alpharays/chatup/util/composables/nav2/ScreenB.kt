package com.alpharays.chatup.util.composables.nav2

sealed class ScreenB(val route: String){
    object HomeScreenB: ScreenB("home_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
