package com.alpharays.chatup.viewmodels

import android.app.Activity
import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import com.alpharays.chatup.util.MainActivity.Companion.prefs
import com.alpharays.chatup.util.composables.nav1.ScreenA
import com.alpharays.chatup.util.composables.nav2.ScreenB
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class OtpViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var auth: FirebaseAuth

    fun verifyOtp(
        navController: NavController,
        context: Context,
        otp: String,
        typedOtp: String
    ) {
        auth = FirebaseAuth.getInstance()
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(otp, typedOtp)
        signInWithPhoneAuthCredential(navController, context, credential)
//        functionForResentOtp(number, context)
    }

    private fun signInWithPhoneAuthCredential(
        navController: NavController,
        context: Context,
        credential: PhoneAuthCredential
    ) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    // Navigate to Home Page
                    with (prefs.edit()) {
                        putBoolean("is_logged_in", true)
                        commit()
                    }
                    navController.navigate(ScreenB.HomeScreenB.route)
                    Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Log.d(
                        ContentValues.TAG,
                        "signInWithPhoneAuthCredential: ${task.exception.toString()}"
                    )
                    Toast.makeText(context, " ${task.exception.toString()}", Toast.LENGTH_SHORT)
                        .show()
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(context, "Enter valid Verification Code", Toast.LENGTH_SHORT)
                            .show()
                    }
                    // Update UI
                }
            }
    }

    private fun functionForResentOtp(
        navController: NavController,
        number: String,
        context: Context
    ) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)
            .setTimeout(30L, TimeUnit.SECONDS)
            .setActivity(context as Activity)
            .setCallbacks(
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        signInWithPhoneAuthCredential(navController, context, credential)
                    }

                    override fun onVerificationFailed(e: FirebaseException) {
                        if (e is FirebaseAuthInvalidCredentialsException) {
                            Log.i("failedOtpVerification", e.toString())
                            Toast.makeText(context, "Invalid Otp, try again", Toast.LENGTH_SHORT)
                                .show()
                        } else if (e is FirebaseTooManyRequestsException) {
                            Log.i("TooManyRequests", "SMS quota for the project exceeded")
                            Toast.makeText(context, "Error: Contact Developer", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onCodeSent(
                        verificationId: String,
                        token: PhoneAuthProvider.ForceResendingToken
                    ) {
//                        navController.navigate(Screen.OtpScreen.withArgs(number,token.toString()))
                        Log.i("OTP_PAGE", "CODE SENT ALREADY - VERIFICATION AREA")
                    }
                }
            )
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}