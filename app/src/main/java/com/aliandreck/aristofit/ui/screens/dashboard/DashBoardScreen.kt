package com.aliandreck.aristofit.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aliandreck.aristofit.ui.theme.RoyalPurple1
import com.aliandreck.aristofit.R
import com.aliandreck.aristofit.navigation.ROUT_ABOUT
import com.aliandreck.aristofit.navigation.ROUT_CONTACT
import com.aliandreck.aristofit.navigation.ROUT_ITEM
import com.aliandreck.aristofit.navigation.ROUT_PRODUCT_LIST
import com.aliandreck.aristofit.navigation.ROUT_SERVICE
import com.aliandreck.aristofit.navigation.ROUT_START

@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D0D)) // Deep cyber background
    ) {
        // Gradient Header with Logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(RoyalPurple1, Color(0xFF111111))
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.dvlogo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "ARISTOFIT",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Cyan
                )
                Text(
                    text = "WELCOME!",
                    fontSize = 13.sp,
                    color = Color.LightGray
                )
            }
        }

        val buttons = listOf(
            Triple(R.drawable.start, "Start", ROUT_START),
            Triple(R.drawable.product, "Products", ROUT_PRODUCT_LIST),
            Triple(R.drawable.contacts, "Contacts", ROUT_CONTACT),
            Triple(R.drawable.about, "About", ROUT_ABOUT),
            Triple(R.drawable.services, "Service", ROUT_SERVICE),
            Triple(R.drawable.item, "Item", ROUT_ITEM)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(buttons) { (icon, label, route) ->
                NeonTile(icon, label) {
                    navController.navigate(route)
                }
            }
        }
    }
}

@Composable
fun NeonTile(icon: Int, label: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(14.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF3A3737), Color(0xFF3F3F3F))
                )
            )
            .border(2.dp, Color(0xFF7F00FF), RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .shadow(10.dp, shape = RoundedCornerShape(14.dp), ambientColor = Color.Cyan)
            .padding(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.1f))
                    .border(2.dp, Color.Cyan, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    modifier = Modifier.size(34.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = label.uppercase(),
                color = Color.Cyan,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashBoardScreenPreview(){
    DashboardScreen(navController= rememberNavController())
}