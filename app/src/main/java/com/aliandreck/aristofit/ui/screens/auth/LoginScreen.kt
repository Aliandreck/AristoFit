package com.aliandreck.aristofit.ui.screens.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aliandreck.aristofit.R
import com.aliandreck.aristofit.navigation.ROUT_ADMIN_PRODUCT_LIST
import com.aliandreck.aristofit.navigation.ROUT_DASHBOARD
import com.aliandreck.aristofit.navigation.ROUT_REGISTER
import com.aliandreck.aristofit.ui.theme.RichBlack
import com.aliandreck.aristofit.ui.theme.RoyalPurple
import com.aliandreck.aristofit.ui.theme.Silver
import com.aliandreck.aristofit.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(authViewModel) {
        authViewModel.loggedInUser = { user ->
            if (user == null) {
                Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            } else {
                if (user.role == "admin") {
                    navController.navigate(ROUT_ADMIN_PRODUCT_LIST)
                } else {
                    navController.navigate(ROUT_DASHBOARD)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RichBlack)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            RoyalPurple.copy(alpha = 0.2f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with fade-in logo and text
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(tween(1000)),
                exit = fadeOut(tween(800))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Fade-in logo
                    Image(
                        painter = painterResource(id = R.drawable.dvlogo), // Change this to your actual logo file name
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .size(90.dp)
                            .padding(bottom = 12.dp)
                    )
                    Text(
                        text = "ARISTOFIT",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp,
                        color = RoyalPurple
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Welcome Back!",
                        fontSize = 18.sp,
                        color = Silver
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text("Email", color = Silver)
                },
                leadingIcon = {
                    Icon(Icons.Filled.Email, contentDescription = "Email", tint = RoyalPurple)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = RoyalPurple.copy(alpha = 0.7f),
                    focusedBorderColor = Silver,
                    unfocusedLabelColor = Silver.copy(alpha = 0.8f),
                    focusedLabelColor = Color.White,
                    cursorColor = Silver
                ),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text("Password", color = Silver)
                },
                leadingIcon = {
                    Icon(Icons.Filled.Lock, contentDescription = "Password", tint = RoyalPurple)
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = if (passwordVisible) painterResource(R.drawable.visibility)
                            else painterResource(R.drawable.visibilityoff),
                            contentDescription = "Toggle visibility",
                            tint = Silver
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = RoyalPurple.copy(alpha = 0.7f),
                    focusedBorderColor = Silver,
                    unfocusedLabelColor = Silver.copy(alpha = 0.8f),
                    focusedLabelColor = Color.White,
                    cursorColor = Silver
                ),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Login Button
            Button(
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                    } else {
                        authViewModel.loginUser(email, password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .shadow(8.dp, RoundedCornerShape(12.dp), spotColor = RoyalPurple),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RoyalPurple,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 4.dp
                )
            ) {
                Text(
                    "Welcome!",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Register Link
            TextButton(
                onClick = { navController.navigate(ROUT_REGISTER) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    "No account yet? Register now",
                    color = Silver,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

