package com.alpharays.chatup.util.composables.nav1

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.alpharays.chatup.R
import com.alpharays.chatup.viewmodels.LoginViewModel
import kotlinx.coroutines.*


@Composable
fun LoginComp(navController: NavController) {
    val scrollState = rememberScrollState()
    var phoneNumberFieldState by remember { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val loginViewModel = viewModel<LoginViewModel>()
    var showProgressBar by remember { mutableStateOf(false) }
    var buttonEnabled = true

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
                text = "Chat Up",
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp)
            )
            val modifier = Modifier.size(320.dp)
            Image(
                painterResource(id = R.drawable.login_image),
                contentDescription = "Login Image",
                modifier = modifier
            )
            if (showProgressBar) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(5.dp)
                )
            }
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFE1FFF0),
                            shape = RoundedCornerShape(1.dp)
                        )
                        .padding(0.dp, 0.dp, 0.dp, 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
//                     Enter phone no. to register or login
                    OutlinedTextField(
                        modifier = Modifier.padding(10.dp,5.dp,10.dp,5.dp),
                        value = phoneNumberFieldState,
                        label = {
                            Text("Enter your Phone number")
                        },
                        onValueChange = {
                            if (it.length <= 10) {
                                phoneNumberFieldState = it
                            }
                        },
                        singleLine = true,
                        leadingIcon = {
                            Text(text = "+91", color = Color(0xFF000000), fontSize = 18.sp)
                        },
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    // send otp button
                    Button(
                        onClick = {
                            coroutineScope.launch(Dispatchers.Main) {
                                // Perform heavy work on background thread
                                withContext(Dispatchers.Default) {
                                    // no heavy work
                                }
                                // Change screen
                                var str: String = phoneNumberFieldState
                                if (str.length != 10) Toast.makeText(
                                    context,
                                    "Enter Valid Number",
                                    Toast.LENGTH_SHORT
                                ).show()
                                else {
                                    buttonEnabled = false
                                    showProgressBar = true
                                    str = "+91$phoneNumberFieldState"
//                                    navController.navigate(ScreenA.OtpScreenA.withArgs("1",str,"0"))
                                    loginViewModel.sendOtp(navController, context, str)
//                                    showProgressBar = false
                                }
                            }
                        },
                        enabled = buttonEnabled,
                        modifier = Modifier.padding(5.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Submit", color = Color(0xFF000000), fontSize = 16.sp)
                    }
                }
            }
        }
    }
}