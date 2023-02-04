package com.alpharays.chatup.util.composables.nav1

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpharays.chatup.R
import kotlinx.coroutines.delay
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.alpharays.chatup.viewmodels.OtpViewModel

@Composable
fun OtpComp(navController: NavController, number: String?, verificationId: String?, token: String?) {
    val scrollState = rememberScrollState()
    var otpFieldState by remember { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()
    var show by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val otpViewModel = viewModel<OtpViewModel>()
    LaunchedEffect(key1 = Unit) {
        delay(32000)
        show = true
    }

    Log.i("OTP IS", verificationId.toString())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { padding ->
        Modifier.padding(1.dp)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .scrollable(scrollState, Orientation.Vertical)
        ) {
            Text(
                text = "Otp Verification",
                fontSize = 30.sp,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 40.dp)
            )
            val modifier = Modifier
                .size(330.dp)
                .padding(0.dp, 0.dp, 0.dp, 25.dp)
            Image(
                painterResource(id = R.drawable.otp_image),
                contentDescription = "Otp Image",
                modifier = modifier
            )
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(0.dp, 0.dp, 0.dp, 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFE1FFF0),
                            shape = RoundedCornerShape(1.dp)
                        )
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Enter Otp sent to $number",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 5.dp)
                    )
                    OutlinedTextField(
                        value = otpFieldState,
                        label = {
                            Text("Received Otp")
                        },
                        onValueChange = {
                            if (it.length <= 6) {
                                otpFieldState = it
                            }
                        },
                        singleLine = true,
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 10.dp)
                    )
                    if (show) {
                        Text(
                            text = "Resend Otp",
                            modifier = Modifier
                                .padding(10.dp, 0.dp, 0.dp, 15.dp)
                                .clickable(enabled = true) {
                                    Toast
                                        .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                                        .show()
                                },
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF2962FF),
                        )
                    }
                    // Login User
                    Button(
                        onClick = {
                            verifyUser(navController,context,verificationId,otpFieldState,otpViewModel)
                            otpFieldState = ""
                        },
                        modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 5.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Login", color = Color(0xFF000000), fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

private fun verifyUser(
    navController: NavController,
    context: Context,
    otp: String?,
    otpFieldState: String,
    otpViewModel: OtpViewModel
) {
    otpViewModel.verifyOtp(navController, context, otp!!, otpFieldState)
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {

}