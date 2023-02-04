package com.alpharays.chatup.util.composables.nav2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.alpharays.chatup.util.composables.nav1.ScreenA
import com.alpharays.chatup.viewmodels.HomeViewModel

@Composable
fun HomeComp(navController: NavController) {
    val homeViewModel = viewModel<HomeViewModel>()

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            Button(onClick = {
                homeViewModel.signOut(navController)
            }) {
                Text(
                    text = "Hello",
                    color = Color.Green,
                    fontSize = 18.sp
                )
            }
        }
    }
}