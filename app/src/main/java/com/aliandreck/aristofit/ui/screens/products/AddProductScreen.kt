package com.aliandreck.aristofit.ui.screens.products

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.input.KeyboardCapitalization
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
fun AddProductScreen(navController: NavController, viewModel: ProductViewModel) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showMenu by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    // Image Picker Launcher
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                imageUri = it
                Log.d("ImagePicker", "Selected image URI: $it")
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "ADD YOUR STYLE",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp,
                            color = Color.Black
                        )
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
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
                            tint = Color.Black
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "Menu",
                            tint = Color.Black
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier.background(Color(0xFF1E1E1E))
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "View Collection",
                                    color = Color.White
                                )
                            },
                            onClick = {
                                navController.navigate(ROUT_PRODUCT_LIST)
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Add New Item",
                                    color = Color.White
                                )
                            },
                            onClick = {
                                navController.navigate(ROUT_ADD_PRODUCT)
                                showMenu = false
                            }
                        )
                    }
                },
                modifier = Modifier.shadow(elevation = 12.dp)
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
            // Product Image Picker
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (imageUri == null) Color(0xFF1E1E1E) else Color.Transparent
                    )
                    .border(
                        width = 2.dp,
                        color = RoyalPurple1.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { imagePicker.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUri),
                        contentDescription = "Selected Image",
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
                        Text(
                            "Tap to add product image",
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            // Form Fields
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Product Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            "Product Name",
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.img),
                            contentDescription = "Name",
                            tint = RoyalPurple1,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = RoyalPurple1,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                        cursorColor = RoyalPurple1,
                    ),

                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true
                    ),
                    singleLine = true
                )

                // Product Price
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = {
                        Text(
                            "Price (Ksh)",
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.doo),
                            contentDescription = "Price",
                            tint = RoyalPurple1
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = RoyalPurple1,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                        cursorColor = RoyalPurple1,
                    ),

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true
                )

                // Contact Phone
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = {
                        Text(
                            "Contact Number",
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Phone,
                            contentDescription = "Phone",
                            tint = RoyalPurple1
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = RoyalPurple1,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                        cursorColor = RoyalPurple1,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    singleLine = true
                )

            }

            // Error Message
            if (showError) {
                Text(
                    text = "Please fill all fields and select an image",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button
            Button(
                onClick = {
                    focusManager.clearFocus()

                    val priceValue = price.toDoubleOrNull()

                    if (name.isBlank() || price.isBlank() || phone.isBlank() || imageUri == null || priceValue == null) {
                        showError = true
                    } else {
                        showError = false
                        viewModel.addProduct(
                            name = name.trim(),
                            price = priceValue,
                            phone = phone.trim(),
                            imageUri = imageUri.toString()
                        )
                        navController.navigate(ROUT_PRODUCT_LIST)
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
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 4.dp
                )
            ) {
                Text(
                    "ADD TO VAULT",
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}