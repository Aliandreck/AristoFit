package com.aliandreck.aristofit.repository

import android.content.Context
import com.aliandreck.aristofit.data.ProductDatabase
import com.aliandreck.aristofit.model.Product

class ProductRepository(context: Context) {
    private val productDao = ProductDatabase.getDatabase(context).productDao()

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    fun getAllProducts() = productDao.getAllProducts()

    suspend fun deleteProduct(product: Product) = productDao.deleteProduct(product)
}