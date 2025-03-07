package com.example.composerediexpress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composerediexpress.screens.ForgotPassword
import com.example.composerediexpress.screens.Home
import com.example.composerediexpress.screens.LogIn
import com.example.composerediexpress.screens.Main
import com.example.composerediexpress.screens.Onboard
import com.example.composerediexpress.screens.SignUp
import com.example.composerediexpress.screens.SplashScreen
import com.example.composerediexpress.screens.Verification
import com.example.composerediexpress.ui.theme.ComposeRediExpressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val dataStoreManager = DataStoreManager(this@MainActivity)
        setContent {
            ComposeRediExpressTheme(false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Splash") {
                        composable("Splash") { SplashScreen(navController) }
                        composable("Onboard") { Onboard(navController) }
                        composable("SignUp") { SignUp(navController) }
                        composable("LogIn") { LogIn(navController/*, dataStoreManager*/) }
                        composable("ForgotPassword") { ForgotPassword(navController) }
                        composable("Verification") { Verification(navController) }
                        composable("Main") { Main() }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeRediExpressTheme {
        Greeting("Android")
    }
}