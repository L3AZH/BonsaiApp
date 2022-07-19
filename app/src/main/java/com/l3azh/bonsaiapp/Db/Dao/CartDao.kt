package com.l3azh.bonsaiapp.Db.Dao

import androidx.room.*
import com.l3azh.bonsaiapp.Db.Entity.CartEntity

@Dao
interface CartDao {

    @Insert
    fun insertItem(vararg item: CartEntity)

    @Update
    fun updateItem(item: CartEntity)

    @Delete
    fun deleteItem(item: CartEntity)

    @Query(value = "SELECT * FROM CartEntity")
    fun getAllItem(): List<CartEntity>

    @Query(value = "SELECT * FROM CartEntity WHERE uuidTree = :uuidTree")
    fun findItemById(uuidTree: String): CartEntity?

}