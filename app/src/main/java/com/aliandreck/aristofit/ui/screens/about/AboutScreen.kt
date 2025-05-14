package com.aliandreck.aristofit.ui.screens.about

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aliandreck.aristofit.R

@Composable
fun AboutScreen(navController: NavController) {
    val backgroundColor = Color(0xFFFC913C)
    val primaryColor = Color(0xFF8134AF)
    val secondaryColor = Color(0xFFDD2A7B)
    val glassBackground = Brush.verticalGradient(
        listOf(Color.White.copy(alpha = 0.05f), Color.White.copy(alpha = 0.02f))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Gradient Title
        val animatedColor by animateColorAsState(
            targetValue = primaryColor,
            label = "animatedHeader"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(primaryColor, primaryColor.copy(alpha = 0.6f))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                text = "About",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Logo
        Image(
            painter = painterResource(id = R.drawable.dvlogo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(2.dp, secondaryColor, RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Description with glass effect
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(glassBackground)
                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "AristoFit is a premium mobile app designed for the modern gentleman who values sophistication, luxury, and impeccable tailoring.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = secondaryColor,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Why Choose Section
        Text(
            text = "Why Choose US?",
            style = MaterialTheme.typography.titleLarge.copy(
                color = primaryColor,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            FeatureItem(icon = Icons.Default.ThumbUp, text = "100% Authentic Products", color = secondaryColor)
            FeatureItem(icon = Icons.Default.ShoppingCart, text = "Worldwide Shipping", color = secondaryColor)
            FeatureItem(icon = Icons.Default.Lock, text = "Secure Checkout", color = secondaryColor)
            FeatureItem(icon = Icons.Default.Star, text = "Exclusives", color = secondaryColor)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Contact
        Text(
            text = "Contact Us",
            style = MaterialTheme.typography.titleLarge.copy(
                color = primaryColor,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Email Card
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.04f)),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { /* Handle email click */ }
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = secondaryColor,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "support@aristofit.com",
                    style = MaterialTheme.typography.bodyLarge.copy(color = secondaryColor)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Version & Copyright
        Text(
            text = "App Version 1.0.0",
            style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray)
        )
        Text(
            text = "© 2023 DripVault. All rights reserved.",
            style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray)
        )

        Spacer(modifier = Modifier.height(36.dp))

        // Back Button
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                contentColor = Color.White
            ),
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "Back to Home", fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun FeatureItem(icon: ImageVector, text: String, color: Color) {
    Card(
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.03f))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(color = color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(navController = rememberNavController())
}
