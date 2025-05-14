package com.aliandreck.aristofit.data


import androidx.lifecycle.LiveData
import androidx.room.*
import com.aliandreck.aristofit.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<Product>>

    @Insert
    suspend fun insertProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)
}