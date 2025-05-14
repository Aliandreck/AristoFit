package com.aliandreck.aristofit.ui.screens.more

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aliandreck.aristofit.R
import com.aliandreck.aristofit.ui.theme.RoyalPurple1
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow

// Define this at the top of your file or in your theme colors
val RoyalPurple1 = Color(0xFF9C27B0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen(navController: NavController) {
    val mContext = LocalContext.current
    val scrollState = rememberScrollState()

    // Safe handling of image resources
    val backIcon = painterResource(id = R.drawable.back) // Make sure this exists
    val bannerImage = painterResource(id = R.drawable.banner) // Make sure this exists
    val starIcon = painterResource(id = R.drawable.starfilled) // Make sure this exists

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A))
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            // Premium App Bar
            TopAppBar(
                title = {
                    Text(
                        "EXCLUSIVES",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp,
                            color = Color.Black
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
                        modifier = Modifier.size(48.dp) // Added size constraint
                    ) {
                        Icon(
                            painter = backIcon,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /* Navigate to cart */ },
                        modifier = Modifier.size(48.dp) // Added size constraint
                    ) {
                        BadgedBox(badge = {
                            Badge(containerColor = Color.Red) {
                                Text("3", color = Color.White)
                            }
                        }) {
                            Icon(
                                Icons.Default.ShoppingCart,
                                contentDescription = "Cart",
                                tint = Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                modifier = Modifier.shadow(elevation = 12.dp)
            )

            // Hero Banner with Floating Action Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            ) {
                Image(
                    painter = bannerImage,
                    contentDescription = "Exclusive Drops",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f)
                                ),
                                startY = 0.4f
                            )
                        )
                )

                // Floating Favorite Button
                FloatingActionButton(
                    onClick = { /* Toggle favorite */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp),
                    containerColor = Color.White,
                    contentColor = Color.Red
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite"
                    )
                }

                // Banner Text
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "LIMITED COLLECTION",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = RoyalPurple1,
                            letterSpacing = 2.sp
                        )
                    )
                    Text(
                        text = "Fall/Winter 2023",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    )
                }
            }

            // Search Bar
            var search by remember { mutableStateOf("") }
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                shape = RoundedCornerShape(16.dp),
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = RoyalPurple1
                    )
                },
                placeholder = {
                    Text(
                        "Search exclusive items...",
                        color = Color.White.copy(alpha = 0.6f)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = RoyalPurple1,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                    cursorColor = RoyalPurple1,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.6f)
                ),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White
                )
            )

            // Categories Section
            Text(
                text = "CATEGORIES",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = RoyalPurple1,
                    letterSpacing = 1.sp
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Horizontal Scroll Categories
            val categories = listOf("Tux", "Shirts", "Accessories", "more...")
            LazyRow(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(category = category)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Featured Products Grid
            Text(
                text = "CURATED SELECTION",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = RoyalPurple1,
                    letterSpacing = 1.sp
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Lazy Grid for Featured Products
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(4) { index ->
                    ProductCard(
                        title = when(index) {
                            0 -> "Limited"
                            1 -> "Half"
                            2 -> "Loafers"
                            else -> "Nothing Here"
                        },
                        price = "Ksh ${listOf(2500, 4500, 12000, 1800)[index]}",
                        originalPrice = "Ksh ${listOf(3000, 5000, 15000, 2200)[index]}",
                        rating = 4,
                        imageRes = when(index) {
                            0 -> R.drawable.shirt
                            1 -> R.drawable.halfct
                            2 -> R.drawable.loaf
                            else -> R.drawable.cleaning
                        },
                        onItemClick = { /* Navigate to detail */ }
                    )
                }
            }

            // Purchase CTA
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    try {
                        val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                        simToolKitLaunchIntent?.let { mContext.startActivity(it) }
                    } catch (e: Exception) {
                        // Handle error
                        Toast.makeText(mContext, "Could not open payment app", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RoyalPurple1,
                    contentColor = Color.Black
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 4.dp
                )
            ) {
                Text(
                    "SECURE YOUR Space",
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
fun CategoryChip(category: String) {
    Card(
        onClick = {  },
        modifier = Modifier.width(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(
            width = 1.dp,
            color = RoyalPurple1.copy(alpha = 0.5f)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = category,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProductCard(
    title: String,
    price: String,
    originalPrice: String,
    rating: Int,
    imageRes: Int,
    onItemClick: () -> Unit
) {
    val imagePainter = painterResource(id = imageRes)

    Card(
        onClick = onItemClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A1A)
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            // Product Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Discount Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            color = RoyalPurple1,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "LIMITED",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                }
            }

            // Product Info
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    repeat(rating) {
                        Icon(
                            painter = painterResource(R.drawable.starfilled),
                            contentDescription = "Star",
                            tint = RoyalPurple1,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                if (originalPrice.isNotEmpty()) {
                    Text(
                        text = originalPrice,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.5f),
                            textDecoration = TextDecoration.LineThrough
                        )
                    )
                }

                Text(
                    text = price,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Black,
                        color = RoyalPurple1
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoreScreenPreview() {
    MoreScreen(rememberNavController())
}