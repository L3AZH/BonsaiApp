package com.l3azh.bonsaiapp.Repository

import com.l3azh.bonsaiapp.Api.Response.BonsaiErrorResponse
import com.l3azh.bonsaiapp.Db.Dao.CartDao
import com.l3azh.bonsaiapp.Db.Entity.CartEntity
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartDao: CartDao) {

    suspend fun getAllItem(
        onSuccess: (List<CartEntity>) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val listItem = cartDao.getAllItem()
            if (listItem.isNotEmpty()) {
                onSuccess(listItem)
            } else {
                onError(
                    BonsaiErrorResponse(
                        404,
                        false,
                        "Not fount any item",
                        BonsaiAppUtils.getCurrentDateString()
                    )
                )
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun getCartItemById(
        uuidTree: String,
        onSuccess: (CartEntity) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val item = cartDao.findItemById(uuidTree)
            if (item == null) {
                onError(
                    BonsaiErrorResponse(
                        404,
                        false,
                        "Not fount any item with $uuidTree uuid",
                        BonsaiAppUtils.getCurrentDateString()
                    )
                )
            } else {
                onSuccess(item)
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun addNewItem(
        newTreeItem: CartEntity,
        onSuccess: () -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            cartDao.insertItem(newTreeItem)
            onSuccess()
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun updateItem(
        updateTreeItem: CartEntity,
        onSuccess: () -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            cartDao.updateItem(updateTreeItem)
            onSuccess()
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun deleteItem(
        deleteTreeItem: CartEntity,
        onSuccess: () -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            cartDao.deleteItem(deleteTreeItem)
            onSuccess()
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun deleteAllItem(
        listItem: List<CartEntity>,
        onSuccess: () -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            listItem.forEach { cartEntity ->
                cartDao.deleteItem(cartEntity)
            }
            onSuccess()
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }
}