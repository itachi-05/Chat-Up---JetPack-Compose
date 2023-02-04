package com.alpharays.chatup.util.composables.nav1

sealed class ScreenA(val route: String){
    object LoginScreenA: ScreenA("login_screen")
    object OtpScreenA: ScreenA("otp_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
