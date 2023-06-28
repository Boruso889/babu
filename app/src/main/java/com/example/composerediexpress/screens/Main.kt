package com.example.composerediexpress.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composerediexpress.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview (showBackground = true)
@Composable
fun Home() {
    var searchInput by remember { mutableStateOf("") }


    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())) {
        //Search
        TextField(value = searchInput, onValueChange = {
            searchInput = it
        },
        placeholder = {
            Text(
                text = "Search services",
                fontSize = 12.sp,
                color = Color(0xFFA7A7A7))
        },
        trailingIcon = {
               Icon(painter = painterResource(id = R.drawable.search), contentDescription = "search icon")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .height(90.dp)
            .background(Color(0xFF0560FA), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 17.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = "Hello Ken",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF))
                    Text(
                        text = "We trust you are having a great time",
                        fontSize = 12.sp,
                        color = Color(0xFFFFFFFF))
                }
                IconButton(onClick = {  }) {
                    Icon(painter = painterResource(id = R.drawable.notification),
                        contentDescription = "notification icon",
                        tint = Color.White
                    )
                }
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Special for you",
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFEC8000),
                modifier = Modifier)
            IconButton(onClick = {  }, modifier = Modifier.size(14.dp)) {
                Icon(painterResource(id = R.drawable.arrow_square_right),
                    contentDescription = "arrow square right",
                    tint = Color(0xFFEC8000))
            }
        }
        Row(
            Modifier
                .padding(top = 7.dp)
                .fillMaxWidth()
                .height(64.dp)) {
            Box(modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()) {
                Image(painter = painterResource(id = R.drawable.special_card_1),
                    contentDescription = "Tech meetup",
                    Modifier.fillMaxSize())
            }
            Spacer(modifier = Modifier.width(12.dp))
            Box(modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()) {
                Image(painter = painterResource(id = R.drawable.special_card_2),
                    contentDescription = "Love revolution",
                    Modifier.fillMaxSize())
            }
        }

        Text(
            text = "What would you like to do",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF0560FA),
        modifier = Modifier.padding(top = 30.dp))
        Column(Modifier.fillMaxWidth().padding(top = 12.dp)) {
            Row(
                Modifier
                    .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(modifier = Modifier.weight(0.5f).height(160.dp),
                    elevation = CardDefaults.cardElevation(
                        4.dp
                    )) {
                    Column(Modifier.fillMaxWidth()) {
                        Icon(painterResource(id = R.drawable.customer_care_icon),
                            contentDescription = "customer care card icon",
                            modifier = Modifier.padding(start = 12.dp, top = 22.dp),
                        tint = Color(0xFF0560FA))
                        Text(
                            text = "Customer Care",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF0560FA),
                            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp))
                        Text(
                            text = "Our customer care service line is available from 8 -9pm week days and 9 - 5 weekends - tap to call us today",
                            fontSize = 7.45.sp,
                            color = Color(0xFF3A3A3A),
                            modifier = Modifier
                                .padding(top = 5.dp, start = 12.dp, end = 12.dp, bottom = 40.dp))
                    }
                }
                Spacer(modifier = Modifier.width(23.dp))
                Card(modifier = Modifier
                    .weight(0.5f).height(160.dp),
                    elevation = CardDefaults.cardElevation(
                        4.dp
                    )) {
                    Column(Modifier.fillMaxWidth()) {
                        Icon(painterResource(id = R.drawable.send_a_package_icon),
                            contentDescription = "send a package icon",
                            modifier = Modifier.padding(start = 12.dp, top = 22.dp),
                            tint = Color(0xFF0560FA))
                        Text(
                            text = "Send a package",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF0560FA),
                            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp))
                        Text(
                            text = "Request for a driver to pick up or deliver your package for you",
                            fontSize = 7.45.sp,
                            color = Color(0xFF3A3A3A),
                            modifier = Modifier
                                .padding(top = 5.dp, start = 12.dp, end = 12.dp, bottom = 40.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            //Row 2
            Row(
                Modifier
                    .fillMaxSize(),
            ) {
                Card(modifier = Modifier.weight(0.5f).height(160.dp),
                    elevation = CardDefaults.cardElevation(
                        4.dp
                    )) {
                    Column(Modifier.fillMaxWidth()) {
                        Icon(painterResource(id = R.drawable.fund_your_wallet),
                            contentDescription = "Fund your wallet icon",
                            modifier = Modifier.padding(start = 12.dp, top = 22.dp),
                            tint = Color(0xFF0560FA))
                        Text(
                            text = "Fund your wallet",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF0560FA),
                            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp))
                        Text(
                            text = "To fund your wallet is as easy as ABC, make use of our fast technology and top-up your wallet today",
                            fontSize = 7.45.sp,
                            color = Color(0xFF3A3A3A),
                            modifier = Modifier
                                .padding(top = 5.dp, start = 12.dp, end = 12.dp, bottom = 40.dp))
                    }
                }
                Spacer(modifier = Modifier.width(23.dp))
                Card(modifier = Modifier.weight(0.5f).height(160.dp),
                    elevation = CardDefaults.cardElevation(
                        4.dp
                    )) {
                    Column(Modifier.fillMaxWidth()) {
                        Icon(painterResource(id = R.drawable.book_a_rider),
                            contentDescription = "Book a rider icon",
                            modifier = Modifier.padding(start = 12.dp, top = 22.dp),
                            tint = Color(0xFF0560FA))
                        Text(
                            text = "Book a rider",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF0560FA),
                            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp))
                        Text(
                            text = "Search for available rider within your area",
                            fontSize = 7.45.sp,
                            color = Color(0xFF3A3A3A),
                            modifier = Modifier
                                .padding(top = 5.dp, start = 12.dp, end = 12.dp, bottom = 40.dp))
                    }
                }
            }
    }
    }
}