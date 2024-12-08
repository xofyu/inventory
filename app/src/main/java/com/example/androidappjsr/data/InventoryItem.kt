// File: data/InventoryItem.kt
package com.example.androidappjsr.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "inventory_items")
data class InventoryItem(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val imageUri: String,
    val quantity: Int,
    val price: Double,
    val category: String,
    val dateAdded: Long = System.currentTimeMillis()
)