package com.example.composerediexpress.screens

import android.annotation.SuppressLint
import android.view.animation.RotateAnimation
import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composerediexpress.R
import com.example.composerediexpress.datastore.DataStoreManager
import com.example.composerediexpress.datastore.DestinationDetails
import com.example.composerediexpress.datastore.OriginDetails
import com.example.composerediexpress.datastore.PackageDetails
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main() {
    val navController = rememberNavController()
    val menuList = listOf("Home", "Wallet", "Track", "Profile")
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(bottomBar = {
        NavigationBar() {
            menuList.forEachIndexed { index, item ->
                NavigationBarItem(selected = selectedItem == index, onClick = {
                    selectedItem = index

                    when(index) {
                        0 -> navController.navigate("HomeNavItem") {
                            popUpTo("HomeNavItem")
                            launchSingleTop = true

                        }
                        1 -> navController.navigate("WalletNavItem") {
                            popUpTo("WalletNavItem")
                            launchSingleTop = true
                        }
                        2 -> navController.navigate("TrackNavItem") {
                            popUpTo("TrackNavItem")
                            launchSingleTop = true
                        }
                        3 -> navController.navigate("ProfileNavItem") {
                            popUpTo("ProfileNavItem")
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    when(index) {
                        0 -> Icon(painterResource(id = R.drawable.nav_home), "Home")
                        1 -> Icon(painterResource(id = R.drawable.nav_wallet), "Wallet")
                        2 -> Icon(painterResource(id = R.drawable.nav_track), "Track")
                        3 -> Icon(painterResource(id = R.drawable.nav_profile), "Profile")
                    }
                },
                label = { Text(text = item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor = MaterialTheme.colorScheme.primary
                ))
            }
        }
    }
    ) { padding ->
        NavHost(navController = navController, startDestination = "HomeNavItem") {
            navigation(startDestination = "Home", route = "HomeNavItem") {
                composable("Home") { Home(padding, navController) }
                composable("Send") { Send(padding, navController) }
                composable("Send2") { Send2(padding, navController) }
                composable("Transaction") { Transaction(padding, navController) }
            }
            navigation(startDestination = "Wallet", route = "WalletNavItem") {
                composable("Wallet") { Wallet(padding) }
            }
            navigation(startDestination = "Track", route = "TrackNavItem") {
                composable("Track") { Tracking() }
            }
            navigation(startDestination = "Profile", route = "ProfileNavItem") {
                composable("Profile") { Profile(padding, navController) }
                composable("Notification") { Notification(navController, padding) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(padding: PaddingValues, navController: NavController) {
    var searchInput by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(padding)
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
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 20.dp)) {
            Row(
                Modifier
                    .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(160.dp),
                    elevation = CardDefaults.cardElevation(
                        4.dp
                    )
                ) {
                    Column(Modifier.fillMaxWidth()) {
                        Icon(
                            painterResource(id = R.drawable.customer_care_icon),
                            contentDescription = "customer care card icon",
                            modifier = Modifier.padding(start = 12.dp, top = 22.dp),
                            tint = Color(0xFF0560FA)
                        )
                        Text(
                            text = "Customer Care",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF0560FA),
                            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp)
                        )
                        Text(
                            text = "Our customer care service line is available from 8 -9pm week days and 9 - 5 weekends - tap to call us today",
                            fontSize = 7.45.sp,
                            color = Color(0xFF3A3A3A),
                            modifier = Modifier
                                .padding(top = 5.dp, start = 12.dp, end = 12.dp, bottom = 40.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(23.dp))
                Box(modifier = Modifier
                    .weight(0.5f)
                    .clickable {
                        navController.navigate("Send")
                    }) {
                Card(
                    modifier = Modifier
                        .height(160.dp),
                    elevation = CardDefaults.cardElevation(
                        4.dp
                    )
                ) {
                    Column(Modifier.fillMaxWidth()) {
                        Icon(
                            painterResource(id = R.drawable.send_a_package_icon),
                            contentDescription = "send a package icon",
                            modifier = Modifier.padding(start = 12.dp, top = 22.dp),
                            tint = Color(0xFF0560FA)
                        )
                        Text(
                            text = "Send a package",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF0560FA),
                            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp)
                        )
                        Text(
                            text = "Request for a driver to pick up or deliver your package for you",
                            fontSize = 7.45.sp,
                            color = Color(0xFF3A3A3A),
                            modifier = Modifier
                                .padding(top = 5.dp, start = 12.dp, end = 12.dp, bottom = 40.dp)
                        )
                    }
                }
            }
            }
            Spacer(modifier = Modifier.height(24.dp))
            //Row 2
            Row(
                Modifier
                    .fillMaxSize(),
            ) {
                Card(modifier = Modifier
                    .weight(0.5f)
                    .height(160.dp),
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
                Card(modifier = Modifier
                    .weight(0.5f)
                    .height(160.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(padding: PaddingValues, navController: NavController) {
    var visibleBalance by remember { mutableStateOf(true) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Profile",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA7A7A7))
                },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

            )
        )
    }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(padding)
            .verticalScroll(rememberScrollState())) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp), elevation = CardDefaults.cardElevation(10.dp)) {}

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, bottom = 10.dp, top = 36.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painterResource(id = R.drawable.avatar),
                            contentDescription = "Avatar",
                            modifier = Modifier.size(60.dp)
                        )
                        Column(modifier = Modifier.padding(start = 12.dp)) {
                            Text(
                                text = "Ken Nwaeze",
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF3A3A3A)
                            )
                            Row() {
                                Text(
                                    text = "Current balance: ",
                                    fontSize = 12.sp,
                                    color = Color(0xFF3A3A3A)
                                )
                                if (visibleBalance) {
                                    Text(
                                        text = "N10,712:00",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF0560FA)
                                    )
                                } else {
                                    Text(
                                        text = "*****",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF0560FA))
                                }
                            }
                        }
                    }
                    IconButton(onClick = {
                        visibleBalance = !visibleBalance
                    }) {
                        if (visibleBalance) {
                            Icon(painterResource(id = R.drawable.eye_invisible),
                                contentDescription = "Hidden balance icon")
                        } else {
                            Icon(painterResource(id = R.drawable.eye_visible),
                                contentDescription = "Hidden balance icon")
                        }
                    }
                }
                //Items
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)) {
                    ProfileCard(title = "Edit Profile",
                        description = "Name, phone no, address, email ...",
                        icon = R.drawable.nav_profile,
                        onClick = {

                        })
                    ProfileCard(title = "Statements & Reports",
                        description = "Download transaction details, orders, deliveries",
                        icon = R.drawable.statements,
                        onClick = {

                        })
                    ProfileCard(title = "Notification Settings",
                        description = "mute, unmute, set location & tracking setting",
                        icon = R.drawable.notification_menu_item,
                        onClick = {
                            navController.navigate("Notification")
                        })
                    ProfileCard(title = "Card & Bank account settings",
                        description = "change cards, delete card details",
                        icon = R.drawable.wallet_profile,
                        onClick = {

                        })
                    ProfileCard(title = "Referrals",
                        description = "check no of friends and earn",
                        icon = R.drawable.referrals,
                        onClick = {

                        })
                    ProfileCard(title = "About Us",
                        description = "know more about us, terms and conditions",
                        icon = R.drawable.about,
                        onClick = {

                        })
                    LogOutCard(title = "Log Out",
                        icon = R.drawable.logout,
                        onClick = {

                        })
                }
            }
        }
    }
}

@Composable
fun ProfileCard(title: String, description: String, icon: Int, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(62.dp)
        .padding(bottom = 12.dp)
        .clickable {
            onClick()
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painterResource(id = icon),
                    "$title icon",
                )
                Column(Modifier.padding(start = 10.dp)) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF3A3A3A))
                    Text(
                        text = description,
                        fontSize = 12.sp,
                        color = Color(0xFFA7A7A7))
                }
            }
                Icon(painterResource(id = R.drawable.arrow_right_profile), title)
        }
    }
}

@Composable
fun LogOutCard(title: String, icon: Int, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(62.dp)
        .clickable {
            onClick
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painterResource(id = icon),
                    "$title icon",
                    tint = Color(0xFFED3A3A)
                )
                Text(
                    text = "Log Out",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3A3A3A),
                modifier = Modifier.padding(start = 8.dp))
                }
            Icon(painterResource(id = R.drawable.arrow_right_profile), title)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notification(navController: NavController, padding: PaddingValues) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Notification",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA7A7A7))
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(painterResource(id = R.drawable.arrow_square_back),
                        contentDescription = "Back",
                    tint = Color(0xFF0560FA))
                }
            }
        )
    }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(padding)
            .verticalScroll(rememberScrollState())) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp), elevation = CardDefaults.cardElevation(10.dp)
            ) {}
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 120.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(painterResource(id = R.drawable.notification),
                    "Notification icon",
                tint = Color(0xFFA7A7A7))
                Text(
                    text = "You have no notifications",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3A3A3A),
                    textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp, start = 10.dp, end = 10.dp, bottom = 50.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Send(padding: PaddingValues, navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Send a package",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFA7A7A7))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(painterResource(id = R.drawable.arrow_square_back),
                            contentDescription = "Back",
                        tint = Color(0xFF0560FA))
                    }
                }
            )
        }
    ) {
        val originAddress = remember { mutableStateOf("") }
        val originState = remember { mutableStateOf("") }
        val originPhone = remember { mutableStateOf("") }
        val originOther = remember { mutableStateOf("") }

        val destinationAddress = remember { mutableStateOf("") }
        val destinationState = remember { mutableStateOf("") }
        val destinationPhone = remember { mutableStateOf("") }
        val destinationOther = remember { mutableStateOf("") }

        val packageItems = remember { mutableStateOf("") }
        val packageWeight = remember { mutableStateOf("") }
        val packageWorth = remember { mutableStateOf("") }

        var instantSelected by remember { mutableStateOf(false) }

        val coroutine = rememberCoroutineScope()
        val ctx = LocalContext.current
        val dataStoreManager = DataStoreManager(ctx)

        LaunchedEffect(key1 = true) {
            dataStoreManager.loadOriginDetails().collect {
                originAddress.value = it.address
                originState.value = it.state
                originPhone.value = it.phoneNumber
                originOther.value = it.other
            }
        }
        LaunchedEffect(key1 = true) {
            dataStoreManager.loadDestinationDetails().collect {
                destinationAddress.value = it.address
                destinationState.value = it.state
                destinationPhone.value = it.phoneNumber
                destinationOther.value = it.other
            }
        }
        LaunchedEffect(key1 = true) {
            dataStoreManager.loadPackageDetails().collect {
                packageItems.value = it.items
                packageWeight.value = it.weight
                packageWorth.value = it.worth
            }
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(padding)
           ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp), elevation = CardDefaults.cardElevation(10.dp)
            ) {}
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, top = 43.dp)
                    .verticalScroll(rememberScrollState())) {
                //Origin Details
                Row(modifier = Modifier.padding(bottom = 5.dp)) {
                    Icon(painterResource(id = R.drawable.origin_details),
                        contentDescription = "Origin Details")
                    Text(
                        text = "Origin Details",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF3A3A3A),
                        modifier = Modifier.padding(start = 8.dp))
                }
                TextFieldSend(value = originAddress, text = "Address", Color(0xFF3A3A3A))
                TextFieldSend(value = originState, text = "State,Country", Color(0xFF3A3A3A))
                TextFieldSend(value = originPhone, text = "Phone number", Color(0xFFA7A7A7))
                TextFieldSend(value = originOther, text = "Others", Color(0xFFA7A7A7))
                //Destination Details
                Row(modifier = Modifier.padding(bottom = 5.dp, top = 39.dp)) {
                    Icon(painterResource(id = R.drawable.destination_details),
                        contentDescription = "Destination Details")
                    Text(
                        text = "Destination Details",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF3A3A3A),
                        modifier = Modifier.padding(start = 8.dp))
                }
                TextFieldSend(value = destinationAddress, text = "Address", Color(0xFF3A3A3A))
                TextFieldSend(value = destinationState, text = "State,Country", Color(0xFF3A3A3A))
                TextFieldSend(value = destinationPhone, text = "Phone number", Color(0xFFA7A7A7))
                TextFieldSend(value = destinationOther, text = "Others", Color(0xFFA7A7A7))
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    Icon(painterResource(id = R.drawable.add_square),
                        contentDescription = "Add")
                    Text(
                        text = "Add destination",
                        fontSize = 12.sp,
                        color = Color(0xFFA7A7A7),
                      modifier = Modifier.padding(start = 6.dp))
                }
                //Package Details
                Text(
                    text = "Package Details",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3A3A3A)
                ,modifier = Modifier.padding(top = 13.dp))
                TextFieldSend(value = packageItems, text = "package items", Color(0xFF3A3A3A))
                TextFieldSend(value = packageWeight, text = "Weight of item(kg)", Color(0xFF3A3A3A))
                TextFieldSend(value = packageWorth, text = "Worth of Items", Color(0xFF3A3A3A))

                Text(
                    text = "Select delivery type",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3A3A3A),
                    modifier = Modifier.padding(top = 39.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 10.dp)) {
                    Card(modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(75.dp)
                        .clickable {
                            coroutine.launch {
                                instantSelected = !instantSelected

                                val originDetails = OriginDetails(
                                    originAddress.value,
                                    originState.value,
                                    originPhone.value,
                                    originOther.value
                                )
                                dataStoreManager.saveOriginDetails(originDetails)

                                val destinationDetails = DestinationDetails(
                                    destinationAddress.value,
                                    destinationState.value,
                                    destinationPhone.value,
                                    destinationOther.value
                                )
                                dataStoreManager.saveDestinationDetails(destinationDetails)

                                val packageDetails = PackageDetails(
                                    packageItems.value,
                                    packageWeight.value,
                                    packageWorth.value
                                )
                                dataStoreManager.savePackageDetails(packageDetails)

                                navController.navigate("Send2")
                            }
                        },
                        colors = CardDefaults.cardColors(
                            containerColor = if (instantSelected) Color(0xFF0560FA) else Color.White
                        ),
                    elevation = CardDefaults.cardElevation(4.dp)) {
                        Column(Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(id = R.drawable.clock),
                                contentDescription = "Instant delivery",
                            modifier = Modifier.padding(top = 13.dp),
                            tint = if (instantSelected) Color.White else Color(0xFFA7A7A7))
                            Text(
                                text = "Instant delivery",
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                color = if (instantSelected) Color.White else Color(0xFFA7A7A7),
                                modifier = Modifier.padding(top = 10.dp, bottom = 12.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .height(75.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(4.dp)) {
                        Column(Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(id = R.drawable.simple_line_icons_calender),
                                contentDescription = "Scheduled delivery",
                                modifier = Modifier.padding(top = 13.dp),
                            tint = Color(0xFFA7A7A7))
                            Text(
                                text = "Scheduled delivery",
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFFA7A7A7),
                                modifier = Modifier.padding(top = 10.dp, bottom = 12.dp))
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Send2(padding: PaddingValues, navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Send a package",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFA7A7A7))
                }
            )
        }
    ) {
        val dataStoreManager = DataStoreManager(LocalContext.current)

        var originAddress by remember { mutableStateOf("") }
        var originState by remember { mutableStateOf("") }
        var originNumber by remember { mutableStateOf("") }
        var originOther by remember { mutableStateOf("") }

        var destinationAddress by remember { mutableStateOf("") }
        var destinationState by remember { mutableStateOf("") }
        var destinationNumber by remember { mutableStateOf("") }
        var destinationOther by remember { mutableStateOf("") }

        var packageItems by remember { mutableStateOf("") }
        var packageWeight by remember { mutableStateOf("") }
        var packageWorth by remember { mutableStateOf("") }

        LaunchedEffect(key1 = true) {
            dataStoreManager.loadOriginDetails().collect {
                originAddress = it.address
                originState = it.state
                originNumber = it.phoneNumber
                originOther = it.other
            }
        }
        LaunchedEffect(key1 = true) {
            dataStoreManager.loadDestinationDetails().collect {
                destinationAddress = it.address
                destinationState = it.state
                destinationNumber = it.phoneNumber
                destinationOther = it.other
            }
        }
        LaunchedEffect(key1 = true) {
            dataStoreManager.loadPackageDetails().collect {
                packageItems = it.items
                packageWeight = it.weight
                packageWorth = it.worth
            }
        }


        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(padding)) {
            Card(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(10.dp)) {}
            
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )) {
                Text(
                    text = "Package Information",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF0560FA),
                modifier = Modifier.padding(top = 24.dp))
                Text(
                    text = "Origin details",
                    fontSize = 12.sp,
                    color = Color(0xFF3A3A3A),
                    modifier = Modifier.padding(top = 8.dp))
                Text(
                    text = "$originAddress, $originState",
                    fontSize = 12.sp,
                    color = Color(0xFFA7A7A7),
                    modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = originNumber,
                    fontSize = 12.sp,
                    color = Color(0xFFA7A7A7),
                    modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = "Destination details",
                    fontSize = 12.sp,
                    color = Color(0xFF3A3A3A),
                    modifier = Modifier.padding(top = 8.dp))
                Text(
                    text = "$destinationAddress, $destinationState",
                    fontSize = 12.sp,
                    color = Color(0xFFA7A7A7),
                    modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = destinationNumber,
                    fontSize = 12.sp,
                    color = Color(0xFFA7A7A7),
                    modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = "Other details",
                    fontSize = 12.sp,
                    color = Color(0xFF3A3A3A),
                    modifier = Modifier.padding(top = 8.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Package Items",
                        fontSize = 12.sp,
                        color = Color(0xFFA7A7A7))
                    Text(
                        text = packageItems,
                        fontSize = 12.sp,
                        color = Color(0xFFEC8000),
                        textAlign = TextAlign.Right)
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Weight of items",
                        fontSize = 12.sp,
                        color = Color(0xFFA7A7A7))
                    Text(
                        text = packageWeight,
                        fontSize = 12.sp,
                        color = Color(0xFFEC8000),
                        textAlign = TextAlign.Right)
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Tracking Number",
                        fontSize = 12.sp,
                        color = Color(0xFFA7A7A7))
                    Text(
                        text = "R-7458-4567-4434-5854",
                        fontSize = 12.sp,
                        color = Color(0xFFEC8000),
                        textAlign = TextAlign.Right)
                }
                Divider(modifier = Modifier.padding(top = 24.dp))
                Text(
                    text = "Charges",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF0560FA))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Delivery Charges",
                        fontSize = 12.sp,
                        color = Color(0xFFA7A7A7))
                    Text(
                        text = "N2,500.00",
                        fontSize = 12.sp,
                        color = Color(0xFFEC8000),
                        textAlign = TextAlign.Right)
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Instant delivery",
                        fontSize = 12.sp,
                        color = Color(0xFFA7A7A7))
                    Text(
                        text = "N300.00",
                        fontSize = 12.sp,
                        color = Color(0xFFEC8000),
                        textAlign = TextAlign.Right)
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Tax and Service Charges",
                        fontSize = 12.sp,
                        color = Color(0xFFA7A7A7))
                    Text(
                        text = "N200.00",
                        fontSize = 12.sp,
                        color = Color(0xFFEC8000),
                        textAlign = TextAlign.Right)
                }
                Divider(Modifier.padding(top = 9.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Package total",
                        fontSize = 12.sp,
                        color = Color(0xFFA7A7A7))
                    Text(
                        text = "N3000.00",
                        fontSize = 12.sp,
                        color = Color(0xFFEC8000),
                        textAlign = TextAlign.Right)
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 46.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                    OutlinedButton(onClick = {
                        navController.navigateUp()
                    },
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color(0xFF0560FA))
                    ) {
                        Text(
                            text = "Edit package",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF0560FA))
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Button(onClick = {
                        navController.navigate("Transaction")
                    },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0560FA),
                    )
                    ) {
                        Text(
                            text = "Make payment",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldSend(value: MutableState<String>, text: String, textColor: Color) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)) {
        TextField(value = value.value, onValueChange = {
            value.value = it
        },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                textColor = textColor
            ),
            placeholder = {
                Text(
                    text = text,
                    fontSize = 12.sp,
                    color = Color(0xFFCFCFCF),
                    lineHeight = 0.sp)
            }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp), elevation = CardDefaults.cardElevation(4.dp)
        ) {}
    }
}

@Composable
fun Transaction(padding: PaddingValues, navController: NavController) {
    var load by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = true) {
        delay(2000)
        load = false
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(vertical = 10.dp)
        .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)) {
            if (load) TransactionLoad() else TransactionSuccess()
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 140.dp)
                .height(46.dp),
                shape = RoundedCornerShape(4.dp),
                onClick = {
                    navController.navigate("TrackNavItem")
            }) {
                Text(
                    text = "Track my item",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center)
            }
            OutlinedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(46.dp),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, Color(0xFF0560FA)),
                onClick = {
                    navController.popBackStack()
                    navController.navigate("Home")
                }) {
                Text(
                    text = "Go back to homepage",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF0560FA),
                    textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun TransactionLoad() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        RotationAnim()
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 130.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Your rider is on the way to your destination",
                fontSize = 14.sp,
                color = Color(0xFF3A3A3A))
            Text(
                text = "Tracking Number R-7458-4567-4434-5854",
                fontSize = 14.sp,
                color = Color(0xFF3A3A3A),
                modifier = Modifier.padding(top = 8.dp))
        }
    }

}

@Composable
fun RotationAnim() {
    val inflateTransition = rememberInfiniteTransition()

    val angle by inflateTransition.animateFloat(
        initialValue = 720f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(2000))
    )

    Image(painterResource(id = R.drawable.load), contentDescription = "Load",
        modifier = Modifier
            .size(120.dp)
            .rotate(angle))
}

@Composable
fun TransactionSuccess() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painterResource(id = R.drawable.success), contentDescription = "Success",
            modifier = Modifier.size(120.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 130.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Transaction Successful",
                fontSize = 24.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF3A3A3A))
            Text(
                text = "Your rider is on the way to your destination",
                fontSize = 14.sp,
                color = Color(0xFF3A3A3A),
                modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "Tracking Number R-7458-4567-4434-5854",
                fontSize = 14.sp,
                color = Color(0xFF3A3A3A),
                modifier = Modifier.padding(top = 8.dp))
        }
    }

}

@Composable
fun Tracking() {
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Wallet(padding: PaddingValues) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Wallet",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA7A7A7))
            }
        )
    }) {
        var visibleBalance by remember { mutableStateOf(true) }

        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(padding)) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp), elevation = CardDefaults.cardElevation(10.dp)) {}
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, bottom = 10.dp)
                    .verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.height(36.dp))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painterResource(id = R.drawable.avatar),
                            contentDescription = "Avatar",
                            modifier = Modifier.size(60.dp)
                        )
                        Column(modifier = Modifier.padding(start = 12.dp)) {
                            Text(
                                text = "Ken Nwaeze",
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF3A3A3A)
                            )
                            Row() {
                                Text(
                                    text = "Current balance: ",
                                    fontSize = 12.sp,
                                    color = Color(0xFF3A3A3A)
                                )
                                if (visibleBalance) {
                                    Text(
                                        text = "N10,712:00",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF0560FA)
                                    )
                                } else {
                                    Text(
                                        text = "*****",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF0560FA))
                                }
                            }
                        }
                    }
                    IconButton(onClick = {
                        visibleBalance = !visibleBalance
                    }) {
                        if (visibleBalance) {
                            Icon(painterResource(id = R.drawable.eye_invisible),
                                contentDescription = "Hidden balance icon")
                        } else {
                            Icon(painterResource(id = R.drawable.eye_visible),
                                contentDescription = "Hidden balance icon")
                        }
                    }
                }
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp)) {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Top Up",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF3A3A3A),
                        modifier = Modifier.padding(top = 10.dp))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 48.dp, end = 48.dp, top = 12.dp, bottom = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            TopUpItem(text = "Bank", image = R.drawable.wallet_bank)
                            TopUpItem(text = "Transfer", image = R.drawable.wallet_transfer)
                            TopUpItem(text = "Card", image = R.drawable.wallet_card)
                        }
                    }
                }
                Text(
                    text = "Transaction History",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3A3A3A),
                modifier = Modifier.padding(top = 45.dp, bottom = 24.dp))

                TransactionHistoryCard("Delivery fee", "July 7, 2022", "-N3,000.00")
                TransactionHistoryCard("Delivery fee", "July 7, 2022", "-N2,000.00")
                TransactionHistoryCard("Top up", "July 28, 2022", "N10,000.00")
                TransactionHistoryCard("Delivery fee", "July 25, 2022", "-N2,000.00")
            }
        }
    }
}

@Composable
fun TopUpItem(text: String, image: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painterResource(id = image), text)
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color(0xFF3A3A3A))
    }
}

@Composable
fun TransactionHistoryCard(title: String, date: String, sum: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 12.dp)
        .clickable {

        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(0.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.padding(vertical = 4.dp)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3A3A3A))
                Text(
                    text = date,
                    fontSize = 12.sp,
                    color = Color(0xFFA7A7A7))
            }
            Text(
                text = sum,
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = if (sum.startsWith("-")) Color(0xFFED3A3A) else Color(0xFF35B369))
        }
    }
}