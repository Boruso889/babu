package com.example.composerediexpress.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview (showBackground = true)
@Composable
fun SignUp() {
    var name by remember { mutableStateOf("") }
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

    fun Modifier.textField() = this.then(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            //.border(width = 1.dp, color = Color(0xFFA7A7A7), shape = RoundedCornerShape(4.dp))
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 20.dp, horizontal = 24.dp)) {
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
            textAlign = TextAlign.Center,
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
        OutlinedTextField(value = name, onValueChange = {
            name = it
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
        OutlinedTextField(value = name, onValueChange = {
            name = it
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
        OutlinedTextField(value = name, onValueChange = {
            name = it
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
        OutlinedTextField(value = name, onValueChange = {
            name = it
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

        Row(Modifier.fillMaxWidth().padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checkboxState, onCheckedChange = {
                checkboxState = !checkboxState
            },
                modifier = Modifier.size(14.dp)
            )
            Column(Modifier.fillMaxWidth().padding(start = 10.dp)) {
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
    }
}