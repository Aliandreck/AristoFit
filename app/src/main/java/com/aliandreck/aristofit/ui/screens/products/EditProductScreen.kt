package com.aliandreck.aristofit.ui.screens.products

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aliandreck.aristofit.R
import com.aliandreck.aristofit.navigation.ROUT_ADD_PRODUCT
import com.aliandreck.aristofit.navigation.ROUT_PRODUCT_LIST
import com.aliandreck.aristofit.ui.theme.RoyalPurple1
import com.aliandreck.aristofit.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(
    productId: Int?,
    navController: NavController,
    viewModel: ProductViewModel
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val productList by viewModel.allProducts.observeAsState(emptyList())
    val product = remember(productList) { productList.find { it.id == productId } }

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showMenu by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(product) {
        product?.let {
            name = it.name
            price = it.price.toString()
            imageUri = it.imagePath?.let { path -> Uri.parse(path) }
        }
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
                Toast.makeText(context, "Image Selected!", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "EDIT STORED FITS",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp,
                            color = Color.Black
                        )
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = RoyalPurple1,
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu", tint = Color.Black)
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier.background(Color(0xFF1E1E1E))
                    ) {
                        DropdownMenuItem(
                            text = { Text("View Collection", color = Color.White) },
                            onClick = {
                                navController.navigate(ROUT_PRODUCT_LIST)
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Add New Item", color = Color.White) },
                            onClick = {
                                navController.navigate(ROUT_ADD_PRODUCT)
                                showMenu = false
                            }
                        )
                    }
                },
                modifier = Modifier.shadow(12.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF58529))
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (product != null) {
                Box(
                    modifier = Modifier
                        .size(240.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (imageUri == null) Color(0xFF1E1E1E) else Color.Transparent)
                        .border(2.dp, RoyalPurple1.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = "Product Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.camera),
                                contentDescription = "Pick Image",
                                tint = RoyalPurple1,
                                modifier = Modifier.size(48.dp)
                            )
                            Text("Tap to change product image", color = Color.White.copy(alpha = 0.7f))
                        }
                    }
                }

                Button(
                    onClick = { imagePicker.launch("image/*") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E1E1E),
                        contentColor = RoyalPurple1
                    ),
                    border = BorderStroke(1.dp, RoyalPurple1.copy(alpha = 0.5f))
                ) {
                    Text("CHANGE IMAGE")
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Product Name") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.img),
                                contentDescription = "Name"
                                , tint = RoyalPurple1
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        ),
                        textStyle = LocalTextStyle.current.copy(color = Color.White),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        label = { Text("Price (Ksh)", color = Color.White.copy(alpha = 0.7f)) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.doo),
                                contentDescription = "Price",
                                tint = RoyalPurple1
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (showError) {
                    Text("Please fill all fields", color = Color.Red, modifier = Modifier.padding(top = 8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        focusManager.clearFocus()
                        val updatedPrice = price.toDoubleOrNull()
                        if (name.isBlank() || updatedPrice == null || imageUri == null) {
                            showError = true
                        } else {
                            showError = false
                            viewModel.updateProduct(
                                product.copy(
                                    name = name,
                                    price = updatedPrice,
                                    imagePath = imageUri.toString()
                                )
                            )
                            Toast.makeText(context, "Item updated in STORE!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RoyalPurple1,
                        contentColor = Color.Black
                    ),
                    elevation = ButtonDefaults.buttonElevation(8.dp, 4.dp)
                ) {
                    Text("UPDATE STORED ITEM", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.error),
                        contentDescription = "Error",
                        tint = RoyalPurple1,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Item not found in vault", color = Color.White)
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.width(200.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RoyalPurple1,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("RETURN TO STORE")
                    }
                }
            }
        }
    }
}

