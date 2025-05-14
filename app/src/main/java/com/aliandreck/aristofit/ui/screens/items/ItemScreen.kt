@file:OptIn(ExperimentalMaterial3Api::class)

package com.aliandreck.aristofit.ui.screens.items

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aliandreck.aristofit.R
import com.aliandreck.aristofit.ui.theme.RoyalPurple
import com.aliandreck.aristofit.ui.theme.Silver

data class Product(
    val id: Int,
    val title: String,
    val price: String,
    val imageRes: Int,
    val description: String,
    val category: String
)

val sampleCategories = listOf("Tux", "Shirts", "Accessories", "Half")

val categoryImages = mapOf(
    "Tux" to R.drawable.limited,
    "Shirts" to R.drawable.shirt,
    "Accessories" to R.drawable.chain,
    "Half" to R.drawable.halfct
)

val productNames = mapOf(
    "Tux" to listOf("Timeless", "Lanvin", "Rubinacci", "Sharp", "Already"),
    "Shirts" to listOf("Slik", "Brioni", "Punk", "Glow", "Supima"),
    "Accessories" to listOf("Wristband", "Lace", "Chain", "Neon Backpack", "Pixel Shades"),
    "Half" to listOf("Darkflare", "Mysticodie", "Galaxy Swirl", "Pulse", "Dripzone")
)

val productDescriptions = mapOf(
    "Tux" to listOf(
        "Designed To Impress..",
        "Sleek, durable...Want more??.",
        "The best for the beasts.",
        "Elegance.",
        "Walk like royalty."
    ),
    "Shirts" to listOf(
        "Fuses comfort with bold urban style.",
        "Inspire to inspire.",
        "Dominates the block with style and edge.",
        "Lightweight, breathable for You.",
        "Modern fit feel."
    ),
    "Accessories" to listOf(
        "Level up your fit with this premium piece.",
        "Snapback crafted for legends in the city.",
        "Iced out chain with holographic finish.",
        "Built for fashion and stealth storage.",
        "Sunglasses with polar-drip tech lenses."
    ),
    "Half" to listOf(
        "Black is Bright.",
        "Signature Mystery.",
        "Inspired by the night sky and stardust.",
        "Pulses energy.",
        "Limited edition."
    )
)

val sampleProducts = buildList {
    sampleCategories.forEach { category ->
        val images = categoryImages[category] ?: R.drawable.limited
        val names = productNames[category] ?: listOf()
        val descriptions = productDescriptions[category] ?: listOf()
        repeat(names.size) { index ->
            add(
                Product(
                    id = size,
                    title = names[index],
                    price = "Ksh.${(1500..9000).random()}",
                    imageRes = images,
                    description = descriptions[index],
                    category = category
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf(sampleCategories.first()) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    var selectedBottomItem by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AristoFit", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                },
                actions = {
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = RoyalPurple)
            )
        },
        bottomBar = {

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyRow(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sampleCategories.size) { index ->
                    val category = sampleCategories[index]
                    FilterChip(
                        onClick = { selectedCategory = category },
                        label = { Text(category, fontWeight = FontWeight.Bold) },
                        selected = selectedCategory == category
                    )
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(sampleProducts.filter { it.category == selectedCategory }) { product ->
                    ProductCard(product = product, onClick = { selectedProduct = it })
                }
            }

            selectedProduct?.let {
                ZoomedProductDialog(product = it, onDismiss = { selectedProduct = null })
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick(product) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A)) // Slightly brighter
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .shadow(4.dp, RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(product.title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
                Text(product.price, color = RoyalPurple, fontWeight = FontWeight.SemiBold)
                Text(
                    product.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Silver
                )
            }
        }
    }
}

@Composable
fun ZoomedProductDialog(product: Product, onDismiss: () -> Unit) {
    val scale by animateFloatAsState(targetValue = 1.2f, label = "ZoomIn")

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Close", color = RoyalPurple)
            }
        },
        title = {
            Text(product.title, fontWeight = FontWeight.Bold, fontSize = 22.sp)
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .scale(scale),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(product.price, color = RoyalPurple, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(product.description, color = Silver, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = { /* Add to cart */ }) {
                        Text("Add to Cart")
                    }
                    Button(
                        onClick = { /* Buy */ },
                        colors = ButtonDefaults.buttonColors(containerColor = RoyalPurple)
                    ) {
                        Text("Buy Now", color = Color.White)
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewItemScreen() {
    ItemScreen(navController = rememberNavController())
}
