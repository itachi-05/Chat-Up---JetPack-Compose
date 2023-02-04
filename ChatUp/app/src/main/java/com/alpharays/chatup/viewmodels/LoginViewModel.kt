package com.alpharays.chatup.viewmodels

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.alpharays.chatup.util.composables.nav1.ScreenA
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var auth: FirebaseAuth

    fun sendOtp(navController: NavController, context: Context, number: String){
        auth = FirebaseAuth.getInstance()

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(context as Activity)
            .setCallbacks(
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                        signInWithPhoneAuthCredential(context,credential)
                    }
                    override fun onVerificationFailed(e: FirebaseException) {
                        if (e is FirebaseAuthInvalidCredentialsException) {
                            Log.i("failedOtpVerification",e.toString())
                            Toast.makeText(context, "Invalid Number, try again", Toast.LENGTH_SHORT).show()
                        } else if (e is FirebaseTooManyRequestsException) {
                            Log.i("TooManyRequests","SMS quota for the project exceeded")
                            Toast.makeText(context, "Error: Contact Developer", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onCodeSent( verificationId: String, token: PhoneAuthProvider.ForceResendingToken ) {
                        navController.navigate(ScreenA.OtpScreenA.withArgs(number,verificationId,token.toString()))
                    }
                }
            )
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}