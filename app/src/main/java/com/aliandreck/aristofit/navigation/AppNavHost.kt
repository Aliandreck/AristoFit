package com.aliandreck.aristofit.navigation


import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aliandreck.aristofit.data.UserDatabase
import com.aliandreck.aristofit.repository.UserRepository
import com.aliandreck.aristofit.ui.screens.about.AboutScreen
import com.aliandreck.aristofit.ui.screens.auth.LoginScreen
import com.aliandreck.aristofit.ui.screens.auth.RegisterScreen
import com.aliandreck.aristofit.ui.screens.contact.ContactScreen
import com.aliandreck.aristofit.ui.screens.dashboard.DashboardScreen
import com.aliandreck.aristofit.ui.screens.home.HomeScreen
import com.aliandreck.aristofit.ui.screens.intent.IntentScreen
import com.aliandreck.aristofit.ui.screens.items.ItemScreen
import com.aliandreck.aristofit.ui.screens.more.MoreScreen
import com.aliandreck.aristofit.ui.screens.products.AddProductScreen
import com.aliandreck.aristofit.ui.screens.products.AdminProductListScreen
import com.aliandreck.aristofit.ui.screens.products.EditProductScreen
import com.aliandreck.aristofit.ui.screens.products.ProductListScreen
import com.aliandreck.aristofit.ui.screens.service.ServiceScreen
import com.aliandreck.aristofit.ui.screens.splash.SplashScreen
import com.aliandreck.aristofit.ui.screens.start.StartScreen
import com.aliandreck.aristofit.viewmodel.AuthViewModel
import com.aliandreck.aristofit.viewmodel.ProductViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH,
    productViewModel: ProductViewModel = viewModel()
) {
    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    )
    {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_ITEM) {
            ItemScreen(navController)
        }
        composable(ROUT_START) {
            StartScreen(navController)
        }
        composable(ROUT_INTENT) {
            IntentScreen(navController)

        }
        composable(ROUT_MORE) {
            MoreScreen(navController)

        }
        composable(ROUT_DASHBOARD) {
            DashboardScreen(navController)
        }
        composable(ROUT_SERVICE) {
            ServiceScreen(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_CONTACT) {
            ContactScreen(navController)
        }



        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }
        // PRODUCTS
        composable(ROUT_ADD_PRODUCT) {
            AddProductScreen(navController, productViewModel)
        }

        composable(ROUT_PRODUCT_LIST) {
            ProductListScreen(navController, productViewModel)
        }
        composable(ROUT_ADMIN_PRODUCT_LIST) {
            AdminProductListScreen(navController, productViewModel)
        }

        composable(
            route = ROUT_EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                EditProductScreen(productId, navController, productViewModel)
            }
        }
    }
}

