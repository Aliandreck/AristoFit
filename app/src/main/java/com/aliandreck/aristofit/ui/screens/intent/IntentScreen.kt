@file:OptIn(ExperimentalMaterial3Api::class)

package com.aliandreck.aristofit.ui.screens.intent

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aliandreck.aristofit.R
import com.aliandreck.aristofit.navigation.ROUT_HOME
import com.aliandreck.aristofit.navigation.ROUT_MORE
import com.aliandreck.aristofit.ui.theme.RoyalPurple1

@Composable
fun IntentScreen(navController: NavController) {
    val mContext = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "ACTIONS",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RoyalPurple1,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Share app */ }) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = "Share",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { navController.navigate(ROUT_MORE) }) {
                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = "More",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                modifier = Modifier.shadow(elevation = 12.dp)
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF4C1F96),
                tonalElevation = 5.dp
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.iconhome),
                            contentDescription = "Home",
                            tint = Color.Black
                        )
                    },
                    label = { Text("Home", color = Color.White) },
                    selected = false,
                    onClick = {
                        navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.more),
                            contentDescription = "More",
                            tint = Color.Black
                        )
                    },
                    label = { Text("More", color = Color.White) },
                    selected = false,
                    onClick = {
                        navController.navigate(ROUT_MORE)
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.settings),
                            contentDescription = "Settings",
                            tint = Color.Black
                        )
                    },
                    label = { Text("Settings", color = Color.White) },
                    selected = false,
                    onClick = {
                        val settingsIntent = Intent(android.provider.Settings.ACTION_SETTINGS)
                        mContext.startActivity(settingsIntent)
                    }
                )
            }
        },
        containerColor = Color(0xFF0A0A0A)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- All your previous content stays the same below ---
            // Section Header
            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White.copy(alpha = 0.8f),
                    letterSpacing = 1.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun IntentScreenPreview() {
    IntentScreen(rememberNavController())
}
