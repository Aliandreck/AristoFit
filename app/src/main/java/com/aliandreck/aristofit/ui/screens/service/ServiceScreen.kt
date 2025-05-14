package com.aliandreck.aristofit.ui.screens.service

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aliandreck.aristofit.R
import com.aliandreck.aristofit.ui.theme.RoyalPurple1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceScreen(navController: NavController) {
    val services = listOf(
        Service(
            title = "Exclusive Styling",
            description = "Our premium stylists curate the latest trends to suit your unique style.",
            imageRes = R.drawable.styling
        ),
        Service(
            title = "Sneaker Cleaning",
            description = "Restore your favorite kicks to their original look with our cleaning service.",
            imageRes = R.drawable.cleaning
        ),
        Service(
            title = "1-Hour Delivery",
            description = "Get your order delivered to you within an hour with our express delivery.",
            imageRes = R.drawable.delivery
        ),
        Service(
            title = "VIP Member Events",
            description = "Attend exclusive events for members, featuring early drops and VIP access.",
            imageRes = R.drawable.events
        ),
        Service(
            title = "24/7 Customer Support",
            description = "Our support team is available around the clock to assist you with any queries.",
            imageRes = R.drawable.support
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Our Services") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RoyalPurple1
                )
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFF0A0A0A))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(services) { service ->
                    ServiceCard(service = service, onClick = {
                        // Handle service item click if needed
                        // navController.navigate("serviceDetailRoute")
                    })
                }
            }
        }
    )
}

@Composable
fun ServiceCard(service: Service, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = service.imageRes),
                contentDescription = service.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = 0.5f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    service.title,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    service.description,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                    fontSize = 14.sp
                )
            }
        }
    }
}

data class Service(
    val title: String,
    val description: String,
    val imageRes: Int
)

@Preview(showBackground = true)
@Composable
fun ServiceScreenPreview() {
    ServiceScreen(navController = rememberNavController())
}
