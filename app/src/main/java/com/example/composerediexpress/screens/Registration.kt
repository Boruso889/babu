package com.example.composerediexpress.screens

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composerediexpress.R
import com.example.composerediexpress.components.LargeButton

fun Modifier.textField() = this.then(
    Modifier
        .fillMaxWidth()
        .padding(top = 8.dp)
    //.border(width = 1.dp, color = Color(0xFFA7A7A7), shape = RoundedCornerShape(4.dp))
)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController) {
    val ctx = LocalContext.current

    var inputName by remember { mutableStateOf("") }
    var inputPhone by remember { mutableStateOf("") }
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    var checkboxState by remember { mutableStateOf(false) }
    var visualPassword by remember { mutableStateOf(false) }
    var emailValidated by remember { mutableStateOf(false) }

    val colorTextField = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = Color.Transparent,
        textColor = Color(0xFF3A3A3A),
        placeholderColor = Color(0xFFCFCFCF),
        focusedBorderColor = Color(0xFFA7A7A7),
        unfocusedBorderColor = Color(0xFFA7A7A7),
        disabledBorderColor = Color(0xFFA7A7A7),
        cursorColor = Color(0xFFA7A7A7)
    )

    val colorTextFieldError = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = Color.Transparent,
        textColor = Color(0xFF3A3A3A),
        placeholderColor = Color(0xFFCFCFCF),
        focusedBorderColor = Color.Red,
        unfocusedBorderColor = Color.Red,
        disabledBorderColor = Color.Red,
        cursorColor = Color(0xFFA7A7A7)
    )

    var emailTextFieldColor by remember { mutableStateOf(colorTextField) }


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
        OutlinedTextField(value = inputEmail, onValueChange = {
            inputEmail = it

            val pattern = Patterns.EMAIL_ADDRESS
            if (pattern.matcher(inputEmail).matches()) {
                emailValidated = true
                emailTextFieldColor = colorTextField
            } else {
                emailValidated = false
                emailTextFieldColor = colorTextFieldError
            }
        },
            modifier = Modifier.textField(),
            placeholder = {
                Text(
                    text = "***********@mail.com",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFCFCFCF))
            },
            colors = emailTextFieldColor
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
                IconButton(onClick = {
                    visualPassword = !visualPassword
                }) {
                    if (visualPassword) {
                        Icon(painterResource(id = R.drawable.eye_visible),
                            contentDescription = "eye visible icon")
                    } else {
                        Icon(painterResource(id = R.drawable.eye_invisible),
                            contentDescription = "eye invisible icon")
                    }
                }
            },
            visualTransformation = if (visualPassword) VisualTransformation.None else PasswordVisualTransformation()
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
                .clickable(checkboxState && emailValidated) {
                    navController.navigate("LogIn")
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
                .clickable {
                    navController.navigate("LogIn")
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
@Composable
fun LogIn(navController: NavController) {
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    var checkboxState by remember { mutableStateOf(false) }
    var visualPassword by remember { mutableStateOf(false) }
    var emailValidated by remember { mutableStateOf(false) }

    val colorTextField = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = Color.Transparent,
        textColor = Color(0xFF3A3A3A),
        placeholderColor = Color(0xFFCFCFCF),
        focusedBorderColor = Color(0xFFA7A7A7),
        unfocusedBorderColor = Color(0xFFA7A7A7),
        disabledBorderColor = Color(0xFFA7A7A7),
        cursorColor = Color(0xFFA7A7A7)
    )

    val colorTextFieldError = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = Color.Transparent,
        textColor = Color(0xFF3A3A3A),
        placeholderColor = Color(0xFFCFCFCF),
        focusedBorderColor = Color.Red,
        unfocusedBorderColor = Color.Red,
        disabledBorderColor = Color.Red,
        cursorColor = Color(0xFFA7A7A7)
    )

    var emailTextFieldColor by remember { mutableStateOf(colorTextField) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
    ) {
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
        OutlinedTextField(value = inputEmail, onValueChange = {
            inputEmail = it

            val pattern = Patterns.EMAIL_ADDRESS
            if (pattern.matcher(inputEmail).matches()) {
                emailValidated = true
                emailTextFieldColor = colorTextField
            } else {
                emailValidated = false
                emailTextFieldColor = colorTextFieldError
            }
        },
            modifier = Modifier.textField(),
            placeholder = {
                Text(
                    text = "***********@mail.com",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA7A7A7),
                    textAlign = TextAlign.Center)
            },
            colors = emailTextFieldColor
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
                IconButton(onClick = {
                    visualPassword = !visualPassword
                }) {
                    if (visualPassword) {
                        Icon(painterResource(id = R.drawable.eye_visible),
                            contentDescription = "eye visible icon")
                    } else {
                        Icon(painterResource(id = R.drawable.eye_invisible),
                            contentDescription = "eye invisible icon")
                    }
                }
            },
            visualTransformation = if (visualPassword) VisualTransformation.None else PasswordVisualTransformation()
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
                    navController.navigate("ForgotPassword")
                })
        }
        //Button
        LargeButton(text = "Log in", modifier = Modifier
            .padding(top = 170.dp)
            .clickable(enabled = checkboxState && emailValidated) {
                //navController.navigate()
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
                modifier = Modifier.clickable {
                    navController.navigate("SignUp")
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassword(navController: NavController) {
    var inputEmail by remember { mutableStateOf("") }

    var emailValidated by remember { mutableStateOf(false) }

    val colorTextField = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = Color.Transparent,
        textColor = Color(0xFF3A3A3A),
        placeholderColor = Color(0xFFCFCFCF),
        focusedBorderColor = Color(0xFFA7A7A7),
        unfocusedBorderColor = Color(0xFFA7A7A7),
        disabledBorderColor = Color(0xFFA7A7A7),
        cursorColor = Color(0xFFA7A7A7)
    )

    val colorTextFieldError = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = Color.Transparent,
        textColor = Color(0xFF3A3A3A),
        placeholderColor = Color(0xFFCFCFCF),
        focusedBorderColor = Color.Red,
        unfocusedBorderColor = Color.Red,
        disabledBorderColor = Color.Red,
        cursorColor = Color(0xFFA7A7A7)
    )

    var emailTextFieldColor by remember { mutableStateOf(colorTextField) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
        Text(
            text = "Forgot Password",
            fontSize = 24.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF3A3A3A))
        Text(
            text = "Enter your email address",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFA7A7A7))
        //Email
        Text(
            text = "Email Address",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFA7A7A7),
            modifier = Modifier.padding(top = 48.dp)
        )
        OutlinedTextField(value = inputEmail, onValueChange = {
            inputEmail = it

            val pattern = Patterns.EMAIL_ADDRESS
            if (pattern.matcher(inputEmail).matches()) {
                emailValidated = true
                emailTextFieldColor = colorTextField
            } else {
                emailValidated = false
                emailTextFieldColor = colorTextFieldError
            }
        },
            modifier = Modifier.textField(),
            placeholder = {
                Text(
                    text = "***********@mail.com",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA7A7A7),
                    textAlign = TextAlign.Center)
            },
            colors = emailTextFieldColor
        )
        //Button
        LargeButton(text = "Send OTP",
            Modifier
                .padding(top = 64.dp)
                .clickable(emailValidated) {
                    navController.navigate("Verification")
                }
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Remember password? Back to ",
                fontSize = 14.sp,
                color = Color(0xFFA7A7A7))
            Text(
                text = "Sign in",
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF0560FA),
            modifier = Modifier
                .padding(start = 1.dp)
                .clickable {
                    navController.popBackStack()
                    navController.navigateUp()
                })
        }
    }
}

@Composable
fun Verification(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "OTP Verification",
            fontSize = 24.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF3A3A3A))
        Text(
            text = "Enter the 6 digit numbers sent to your email",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFA7A7A7))
        Text(text = "stub", modifier = Modifier.padding(top = 70.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "If you didn’t receive code, ",
                fontSize = 14.sp,
                color = Color(0xFFA7A7A7))
            Text(
                text = "Resend",
                fontSize = 14.sp,
                color = Color(0xFF0560FA),
                modifier = Modifier.padding(start = 1.dp)
            )
        }
        LargeButton(text = "Set New Password",
            Modifier.
                padding(top = 84.dp)
                .clickable {

                }
        )
    }
}