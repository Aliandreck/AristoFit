package com.aliandreck.aristofit.ui.screens.products

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.aliandreck.aristofit.navigation.ROUT_EDIT_PRODUCT
import com.aliandreck.aristofit.viewmodel.ProductViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminProductListScreen(navController: NavController, viewModel: ProductViewModel) {
    val productList by viewModel.allProducts.observeAsState(emptyList())
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
                        Text(
                            "AristoFit",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(Color(0xFFDD2A7B)),
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(ROUT_ADD_PRODUCT)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Product",
                                tint = Color.White
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
                    placeholder = { Text("Search...") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF8134AF),
                        unfocusedBorderColor = Color.DarkGray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.LightGray,
                        cursorColor = Color(0xFF8134AF)
                    )
                )
            }
        },
        containerColor = Color(0xFFF58529)
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF58529))
                .padding(12.dp)
        ) {
            items(filteredProducts) { product ->
                ProductItem(navController, product, viewModel)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AdminProductItem(navController: NavController, product: Product, viewModel: ProductViewModel) {
    val painter = rememberAsyncImagePainter(model = product.imagePath?.let { Uri.parse(it) })
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {
                if (product.id != 0) {
                    navController.navigate(ROUT_EDIT_PRODUCT)
                }
            },
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
                    color = Color(0xFF8134AF)
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
                            val smsIntent = Intent(Intent.ACTION_SENDTO)
                            smsIntent.data = Uri.parse("smsto:${product.phone}")
                            smsIntent.putExtra(
                                "sms_body",
                                "Hello I like your product '${product.name}'. Can we talk?"
                            )
                            context.startActivity(smsIntent)
                        }
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Message", tint = Color.White)
                    }
                    IconButton(
                        onClick = { navController.navigate(ROUT_EDIT_PRODUCT) }

                    ) {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = "Fix Product",
                            tint = Color.White
                        )
                    }

                }
            }
        }
    }
}
