package com.example.composerediexpress.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.composerediexpress.components.LargeButton

fun Modifier.textField() = this.then(
    Modifier
        .fillMaxWidth()
        .padding(top = 8.dp)
    //.border(width = 1.dp, color = Color(0xFFA7A7A7), shape = RoundedCornerShape(4.dp))
)



@OptIn(ExperimentalMaterial3Api::class)
@Preview (showBackground = true)
@Composable
fun SignUp() {
    var inputName by remember { mutableStateOf("") }
    var inputPhone by remember { mutableStateOf("") }
    var inputAddress by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    var checkboxState by remember { mutableStateOf(false) }

    val colorTextField = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = Color.Transparent,
        textColor = Color(0xFF3A3A3A),
        placeholderColor = Color(0xFFCFCFCF),
        focusedBorderColor = Color(0xFFA7A7A7),
        unfocusedBorderColor = Color(0xFFA7A7A7),
        disabledBorderColor = Color(0xFFA7A7A7),
        cursorColor = Color(0xFFA7A7A7)
    )


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 20.dp, horizontal = 24.dp)
        .verticalScroll(rememberScrollState())) {
        Text(
            text = "Create an account",
            fontSize = 24.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF3A3A3A),
            textAlign = TextAlign.Center)
        Text(
            text = "Complete the sign up process to get started",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFA7A7A7),
            modifier = Modifier.padding(top = 8.dp)
        )

        //Name
        Text(
            text = "Full Name",
            fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFA7A7A7),
                textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 30.dp))
        OutlinedTextField(value = inputName, onValueChange = {
            inputName = it
        },
            modifier = Modifier.textField(),
            placeholder = {
                Text(
                    text = "Name",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFCFCFCF))
            },
            colors = colorTextField
        )
        //Phone Number
        Text(
            text = "Phone Number",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFA7A7A7),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp))
        OutlinedTextField(value = inputPhone, onValueChange = {
            inputPhone = it
        },
            modifier = Modifier.textField(),
            placeholder = {
                Text(
                    text = "000000000000",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFCFCFCF))
            },
            colors = colorTextField
        )
        //Email Address
        Text(
            text = "Email Address",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFA7A7A7),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp))
        OutlinedTextField(value = inputAddress, onValueChange = {
            inputAddress = it
        },
            modifier = Modifier.textField(),
            placeholder = {
                Text(
                    text = "***********@mail.com",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFCFCFCF))
            },
            colors = colorTextField
        )
        //Password
        Text(
            text = "Password",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFA7A7A7),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp))
        OutlinedTextField(value = inputPassword, onValueChange = {
            inputPassword = it
        },
            modifier = Modifier.textField(),
            placeholder = {
                Text(
                    text = "**********",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFCFCFCF))
            },
            colors = colorTextField,
            trailingIcon = {
                IconButton(onClick = {  }) {
                    Icon(painterResource(id = R.drawable.eye_slash), contentDescription = "eye slash")
                }
            }
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checkboxState, onCheckedChange = {
                checkboxState = !checkboxState
            },
                modifier = Modifier.size(14.dp),
                colors = CheckboxDefaults.colors(
                    uncheckedColor = MaterialTheme.colorScheme.primary
                )
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)) {
                Text(
                    text = "By ticking this box, you agree to our",
                    fontSize = 12.sp,
                    color = Color(0xFFA7A7A7))
                Text(
                    text = "Terms and conditions and private policy",
                    fontSize = 12.sp,
                    color = Color(0xFFEBBC2E))
            }
        }
        //Button
        LargeButton(
            text = "SignUp",
            Modifier
                .padding(top = 64.dp)
                .clickable(checkboxState) {

                }
        )
        //Text
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
                color = Color(0xFF0560FA),
            modifier = Modifier
                .padding(start = 1.dp)
                .clickable(
                    enabled = checkboxState
                ) {

                })
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "or sign in using",
                fontSize = 14.sp,
                color = Color(0xFFA7A7A7),
                textAlign = TextAlign.Center)
            Icon(painterResource(id = R.drawable.google),
                contentDescription = "google icon",
                modifier = Modifier.padding(top = 8.dp),
                tint = MaterialTheme.colorScheme.secondary)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview (showBackground = true)
@Composable
fun LogIn() {
    val colorTextField = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = Color.Transparent,
        textColor = Color(0xFF3A3A3A),
        placeholderColor = Color(0xFFCFCFCF),
        focusedBorderColor = Color(0xFFA7A7A7),
        unfocusedBorderColor = Color(0xFFA7A7A7),
        disabledBorderColor = Color(0xFFA7A7A7),
        cursorColor = Color(0xFFA7A7A7)
    )

    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    var checkboxState by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .verticalScroll(rememberScrollState())) {
        Text(
            text = "Welcome Back",
            fontSize = 24.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF3A3A3A))
        Text(
            text = "Fill in your email and password to continue",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFA7A7A7),
            modifier = Modifier.padding(top = 8.dp)
        )

        //Email
        Text(
            text = "Email Address",
            fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFA7A7A7),
        modifier = Modifier.padding(top = 48.dp))
        Text(
            text = "***********@mail.com",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFA7A7A7),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp))

        OutlinedTextField(value = inputEmail, onValueChange = {
            inputEmail = it
        },
            modifier = Modifier.textField(),
            placeholder = {
                Text(
                    text = "**********",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFCFCFCF))
            },
            colors = colorTextField
        )
        //Password
        Text(
            text = "Password",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFA7A7A7),
        modifier = Modifier.padding(top = 24.dp))
        OutlinedTextField(value = inputPassword, onValueChange = {
            inputPassword = it
        },
            modifier = Modifier.textField(),
            placeholder = {
                Text(
                    text = "**********",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFCFCFCF))
            },
            colors = colorTextField,
            trailingIcon = {
                IconButton(onClick = {  }) {
                    Icon(painterResource(id = R.drawable.eye_slash), contentDescription = "eye slash")
                }
            }
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row() {
                Checkbox(checked = checkboxState, onCheckedChange = {
                    checkboxState = !checkboxState
                },
                    modifier = Modifier.size(14.dp),
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = MaterialTheme.colorScheme.primary
                    ))
                Text(
                    text = "Remember password",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA7A7A7),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Text(
                text = "Forgot Password?",
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF0560FA),
                modifier = Modifier.clickable {

                })
        }
        //Button
        LargeButton(text = "Log in", modifier = Modifier
            .padding(top = 170.dp)
            .clickable(enabled = checkboxState) {

            })
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Already have an account?",
                fontSize = 14.sp,
                color = Color(0xFFA7A7A7))
            Text(
                text = "Sign Up",
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF0560FA),
                modifier = Modifier.clickable(enabled = checkboxState) {

            })
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "or log in using",
                fontSize = 14.sp,
                color = Color(0xFFA7A7A7),
                textAlign = TextAlign.Center
            )
            Icon(painter = painterResource(id = R.drawable.google),
                contentDescription = "google icon",
                modifier = Modifier.padding(top = 8.dp),
                tint = MaterialTheme.colorScheme.secondary)
        }

    }
}