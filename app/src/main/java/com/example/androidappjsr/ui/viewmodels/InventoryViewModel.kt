// File: ui/viewmodels/InventoryViewModel.kt
package com.example.androidappjsr.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidappjsr.data.InventoryDatabase
import com.example.androidappjsr.data.InventoryItem
import com.example.androidappjsr.data.InventoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InventoryViewModel(private val repository: InventoryRepository) : ViewModel() {

    private val _inventoryItems = MutableStateFlow<List<InventoryItem>>(emptyList())
    val inventoryItems: StateFlow<List<InventoryItem>> = _inventoryItems.asStateFlow()

    val categories: Flow<List<String>> = repository.getAllCategories()

    init {
        loadAllItems()
    }

    fun loadAllItems() {
        viewModelScope.launch {
            repository.getAllItems().collect { items ->
                _inventoryItems.value = items
            }
        }
    }

    fun loadItemsByCategory(category: String) {
        viewModelScope.launch {
            repository.getItemsByCategory(category).collect { items ->
                _inventoryItems.value = items
            }
        }
    }

    fun getItemById(id: String): Flow<InventoryItem?> {
        val result = MutableStateFlow<InventoryItem?>(null)
        viewModelScope.launch {
            result.value = repository.getItemById(id)
        }
        return result
    }

    suspend fun addItem(item: InventoryItem) {
        repository.insertItem(item)
    }

    suspend fun updateItem(item: InventoryItem) {
        repository.updateItem(item)
    }

    suspend fun deleteItem(item: InventoryItem) {
        repository.deleteItem(item)
    }
}

class InventoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            val database = InventoryDatabase.getDatabase(context)
            val repository = InventoryRepository(database.inventoryDao())
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}