package com.aliandreck.aristofit.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aliandreck.aristofit.R
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale


@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    val newArrivals = listOf(
        NewArrival("Shirt-White", R.drawable.shirt),
        NewArrival("Half Coat", R.drawable.halfct),
        NewArrival("Loafers", R.drawable.loaf),
        NewArrival("ACCESSORIES", R.drawable.chain)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFF58529), Color(0xFFDD2A7B))
                )
            )
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with animated glow
        Text(
            text = "AristoFit",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Black,
                color = Color(0xFF8134AF),
                shadow = Shadow(
                    color = Color(0xFF8134AF).copy(alpha = 0.3f),
                    blurRadius = 20f
                ),
                letterSpacing = 3.sp
            ),
            modifier = Modifier.padding(top = 40.dp, bottom = 8.dp)
        )

        Text(
            text = "True Luxury",
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White.copy(alpha = 0.8f),
                letterSpacing = 1.sp
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        // FEATURED DROP
        Text(
            text = "FEATURED",
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color(0xFF8134AF),
                letterSpacing = 2.sp
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp, bottom = 12.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(280.dp)
                .shadow(
                    elevation = 25.dp,
                    shape = RoundedCornerShape(24.dp),
                    spotColor = Color(0xFF8134AF)
                ),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF58529)
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.limited),
                    contentDescription = "Limited Edition Drop",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.9f)
                            ),
                            startY = 0.4f
                        )
                    )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Golden Men",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    )
                    Text(
                        text = "Limited to 50 Pairs Worldwide",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { /* Navigate */ },
                        modifier = Modifier.width(150.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8134AF),
                            contentColor = Color.Black
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 4.dp
                        )
                    ) {
                        Text("RESERVE NOW", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // NEW ARRIVALS
        Text(
            text = "NEW ARRIVALS",
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color(0xFF673AB7),
                letterSpacing = 2.sp
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp, bottom = 12.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(newArrivals) { item ->
                ProductCard(item = item.name, price = "Ksh ${(1500..9500).random()}", imageRes = item.imageRes)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // NAVIGATION BUTTONS
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NavigationButton(
                icon = Icons.Default.Info,
                text = "ABOUT ARISTOFIT",
                onClick = { navController.navigate("about") }
            )
            NavigationButton(
                icon = Icons.Default.Email,
                text = "CONTACT ARISTOFIT",
                onClick = { navController.navigate("contact") }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // CART FLOATING BUTTON
        FloatingActionButton(
            onClick = { navController.navigate("cart") },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 24.dp),
            containerColor = Color(0xFF8134AF),
            contentColor = Color.Black
        ) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
            Badge(
                modifier = Modifier.offset((-12).dp, 12.dp),
                containerColor = Color.Red
            ) {
                Text("2") // replace with dynamic cart count
            }
        }
    }
}

data class NewArrival(val name: String, val imageRes: Int)

@Composable
fun ProductCard(item: String, price: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        ),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "$item Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 100f
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    text = price,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF8134AF)
                    )
                )
            }
        }
    }
}

@Composable
fun NavigationButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color(0xFF8134AF)
        ),
        border = BorderStroke(
            width = 1.5.dp,
            color = Color(0xFFF58529).copy(alpha = 0.6f)
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}