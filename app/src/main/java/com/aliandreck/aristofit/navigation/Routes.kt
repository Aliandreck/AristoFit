package com.aliandreck.aristofit.navigation

//Authentication
const val ROUT_REGISTER = "register"
const val ROUT_LOGIN = "login"
//Authentication End

const val ROUT_HOME = "home"
const val ROUT_ABOUT = "about"
const val ROUT_ITEM = "item"
const val ROUT_START = "start"
const val ROUT_INTENT = "intent"
const val ROUT_MORE = "more"
const val ROUT_DASHBOARD = "dashboard"
const val ROUT_SERVICE = "service"
const val ROUT_SPLASH = "splash"
const val ROUT_CONTACT = "contact"
const val ROUT_CART = "cart"

//Products
const val ROUT_ADD_PRODUCT = "add_product"
const val ROUT_PRODUCT_LIST = "product_list"
const val ROUT_ADMIN_PRODUCT_LIST = "admin_product_list"
const val ROUT_EDIT_PRODUCT = "edit_product/{productId}"

// ✅ Helper function for navigation
fun editProductRoute(productId: Int) = "edit_product/$productId"