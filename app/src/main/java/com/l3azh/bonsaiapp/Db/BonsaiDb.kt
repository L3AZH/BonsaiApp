package com.l3azh.bonsaiapp.Db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.l3azh.bonsaiapp.Db.Dao.CartDao
import com.l3azh.bonsaiapp.Db.Entity.CartEntity

@Database(entities = [CartEntity::class], version = 1)
abstract class BonsaiDb:RoomDatabase() {
    abstract fun cartDao():CartDao
}