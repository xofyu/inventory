// File: ui/navigation/InventoryNavHost.kt
package com.example.androidappjsr.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.androidappjsr.ui.screens.AddEditItemScreen
import com.example.androidappjsr.ui.screens.CatalogueScreen
import com.example.androidappjsr.ui.screens.ItemDetailScreen

@Composable
fun InventoryNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "catalogue"
    ) {
        composable("catalogue") {
            CatalogueScreen(
                onAddItem = { navController.navigate("item/add") },
                onItemClick = { itemId -> navController.navigate("item/details/$itemId") }
            )
        }

        composable(
            route = "item/details/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
            ItemDetailScreen(
                itemId = itemId,
                onNavigateUp = { navController.navigateUp() },
                onEditItem = { id -> navController.navigate("item/edit/$id") }
            )
        }

        composable("item/add") {
            AddEditItemScreen(
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = "item/edit/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
            AddEditItemScreen(
                itemId = itemId,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}