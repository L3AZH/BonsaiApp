package com.l3azh.bonsaiapp.Model

class TreeTypeState(
    val uuid:String,
    val name:String,
    val description:String
) {
    override fun toString(): String {
        return name
    }
}