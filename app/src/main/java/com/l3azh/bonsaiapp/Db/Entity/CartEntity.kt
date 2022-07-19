package com.l3azh.bonsaiapp.Db.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CartEntity (
    @PrimaryKey
    var uuidTree:String,
    @ColumnInfo
    var name:String,
    @ColumnInfo
    var description:String,
    @ColumnInfo
    var price:Double,
    @ColumnInfo
    var picture:ByteArray,
    @ColumnInfo
    var uuidType:String,
    @ColumnInfo
    var nameType:String,
    @ColumnInfo
    var quantity:Int
)