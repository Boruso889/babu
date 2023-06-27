package com.example.composerediexpress.screens

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composerediexpress.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.splash), "Splash")
    }
    LaunchedEffect(key1 = true) {
        delay(1500)

        navController.popBackStack()
        navController.navigate("Onboard")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Onboard(navController: NavController) {
    val imagesList = listOf(
        R.drawable.onboard1,
        R.drawable.onboard2,
        R.drawable.onboard3
    )
    val titlesList = listOf(
        "Quick Delivery At Your Doorstep",
        "Flexible Payment",
        "Real-time Tracking"
    )
    val descriptionsList = listOf(
        "Enjoy quick pick-up and delivery to your destination",
        "Different modes of payment either before and after delivery without stress",
        "Track your packages/items from the comfort of your home till final destination"
    )

    val pagerState = rememberPagerState()
    var padding by remember { mutableStateOf(50.dp) }
    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        padding = 20.dp
    } else {
        padding = 50.dp
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = padding)
        .verticalScroll(
            rememberScrollState()
        ),
        verticalArrangement = Arrangement.SpaceBetween) {
        HorizontalPager(pageCount = 3, state = pagerState, modifier = Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = imagesList[it]), contentDescription = "image ${it + 1}",
                    modifier = Modifier.size(300.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 50.dp, end = 50.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = titlesList[it],
                        fontSize = 24.sp,
                        fontWeight = FontWeight(700),
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center)
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 50.dp, end = 50.dp, bottom = 10.dp)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = descriptionsList[it],
                        fontSize = 16.sp,
                        color = Color(0xFF3A3A3A),
                        textAlign = TextAlign.Center)
                }
            }
        }
        if (pagerState.currentPage == 2) {
            OneButton(navController)
        } else {
            TwoButtons(navController)
        }
    }
}

@Composable
fun TwoButtons(navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Box(modifier = Modifier
            .width(55.36497.dp)
            .height(28.76902.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp))
            .clickable {

            },
            contentAlignment = Alignment.Center) {
            Text(
                text = "Skip",
                fontSize = 9.38.sp,
                fontWeight = FontWeight(700),
                color = MaterialTheme.colorScheme.primary)
        }

        Box(modifier = Modifier
            .width(56.36497.dp)
            .height(28.76902.dp)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Next",
                fontSize = 9.38.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF))
        }
    }
}

@Composable
fun OneButton(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .padding(horizontal = 24.dp)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
                .clickable {
                    navController.popBackStack()
                    navController.navigate("SignUp")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sign Up",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    navController.navigate("SignUp")
            })
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Already have an account?",
                fontSize = 14.sp,
                color = Color(0xFFA7A7A7))
            Text(
                text = "Sign in",
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    navController.navigate("SignIn")
                }
            )
        }
    }
}