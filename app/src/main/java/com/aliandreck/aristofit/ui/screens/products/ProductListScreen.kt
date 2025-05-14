package com.aliandreck.aristofit.ui.screens.products

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aliandreck.aristofit.model.Product
import com.aliandreck.aristofit.navigation.ROUT_ADD_PRODUCT
import com.aliandreck.aristofit.navigation.ROUT_HOME
import com.aliandreck.aristofit.navigation.ROUT_PRODUCT_LIST
import com.aliandreck.aristofit.viewmodel.ProductViewModel
@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavController, viewModel: ProductViewModel) {
    // Observe the LiveData or Flow from the ViewModel
    val productList by viewModel.allProducts.observeAsState(emptyList())
    var showMenu by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    // Filter products based on the search query
    val filteredProducts = productList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text("Stored Fits", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(Color(0xFF1A1A1A)),
                    actions = {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu", tint = Color.White)
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Home") },
                                onClick = {
                                    navController.navigate(ROUT_HOME)
                                    showMenu = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Add Product") },
                                onClick = {
                                    navController.navigate(ROUT_ADD_PRODUCT)
                                    showMenu = false
                                }
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    placeholder = { Text("Search fire drip...") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF833AB4),
                        unfocusedBorderColor = Color.DarkGray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.LightGray,
                        cursorColor = Color(0xFF833AB4)
                    )
                )
            }
        },
        containerColor = Color(0xFF121212)
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(12.dp)
        ) {
            // Iterate over the filtered products and pass them to the ProductItem composable
            items(filteredProducts) { product ->
                ProductItem(navController, product, viewModel)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ProductItem(navController: NavController, product: Product, viewModel: ProductViewModel) {
    val painter = rememberAsyncImagePainter(model = product.imagePath?.let { Uri.parse(it) })
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F1F1F)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = product.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Ksh ${product.price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF833AB4)
                )
                Text(
                    text = "Seller: ${product.phone}",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    IconButton(
                        onClick = {
                            val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("smsto:${product.phone}")
                                putExtra(
                                    "sms_body",
                                    "Hello I like your product '${product.name}'. Can we talk?"
                                )
                            }
                            context.startActivity(smsIntent)
                        }
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Message", tint = Color.White)
                    }

                    IconButton(onClick = { generatePremiumPDF(context, product) }) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Download PDF", tint = Color.White)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
fun generatePremiumPDF(context: Context, product: Product) {
    // Placeholder: implement PDF export logic here later
}
