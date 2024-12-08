// File: data/InventoryRepository.kt
package com.example.androidappjsr.data

import kotlinx.coroutines.flow.Flow

class InventoryRepository(private val inventoryDao: InventoryDao) {
    fun getAllItems(): Flow<List<InventoryItem>> = inventoryDao.getAllItems()

    fun getItemsByCategory(category: String): Flow<List<InventoryItem>> =
        inventoryDao.getItemsByCategory(category)

    suspend fun getItemById(id: String): InventoryItem? = inventoryDao.getItemById(id)

    suspend fun insertItem(item: InventoryItem) = inventoryDao.insertItem(item)

    suspend fun updateItem(item: InventoryItem) = inventoryDao.updateItem(item)

    suspend fun deleteItem(item: InventoryItem) = inventoryDao.deleteItem(item)

    fun getAllCategories(): Flow<List<String>> = inventoryDao.getAllCategories()
}