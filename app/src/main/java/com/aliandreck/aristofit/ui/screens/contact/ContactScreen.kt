package com.aliandreck.aristofit.ui.screens.contact

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aliandreck.aristofit.ui.theme.RichBlack
import com.aliandreck.aristofit.ui.theme.RoyalPurple
import com.aliandreck.aristofit.ui.theme.Silver
import com.aliandreck.aristofit.R

@Composable
fun ContactScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RichBlack)
            .padding(horizontal = 32.dp, vertical = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with animated entrance
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(800))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = " CUSTOMER SUPPORT",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Black,
                        color = RoyalPurple,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Your concerns deserve attention!",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Silver,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Contact Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = RoyalPurple
                ),
            colors = CardDefaults.cardColors(
                containerColor = RichBlack.copy(alpha = 0.8f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Email Section
                ContactItem(
                    icon = Icons.Default.Email,
                    title = "Contact Customer Support",
                    detail = "support@aristofit.com",
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Phone Section
                ContactItem(
                    icon = Icons.Default.Phone,
                    title = "Emergency Line",
                    detail = "+254 741 453 345",
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Location Section
                ContactItem(
                    icon = Icons.Default.LocationOn,
                    title = "Location",
                    detail = "Nairobi, Kenya"
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Social Media Links (Add your social icons)
        Text(
            text = "Connect With AristoFit",
            style = MaterialTheme.typography.labelLarge.copy(color = Silver),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(bottom = 36.dp)
        ) {
            // Replace with your actual social icons
            SocialIcon(iconRes = R.drawable.ig2, onClick = {})
            SocialIcon(iconRes = R.drawable.fb2, onClick = {})
            SocialIcon(iconRes = R.drawable.twitter2, onClick = {})
        }

        // Back Button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = RoyalPurple,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 4.dp
            )
        ) {
            Text("Satisfied?", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ContactItem(
    icon: ImageVector,
    title: String,
    detail: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = RoyalPurple,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Silver,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = detail,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
private fun SocialIcon(iconRes: Int, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(40.dp)
            .border(
                width = 1.dp,
                color = RoyalPurple,
                shape = CircleShape
            )
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Silver,
            modifier = Modifier.size(20.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ContactScreenPreview(){
    ContactScreen(rememberNavController())
}