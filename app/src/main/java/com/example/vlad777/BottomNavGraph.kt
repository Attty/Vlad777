package com.example.vlad777

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vlad777.screens.Dice
import com.example.vlad777.screens.ruletka.Ruletka
import com.example.vlad777.screens.Slot

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Dice.route,
        enterTransition = { fadeIn(animationSpec = tween(100))
         },
        exitTransition = { fadeOut(animationSpec = tween(100)) },
    ) {
        composable(route = BottomBarScreen.Dice.route) {
            Dice()
        }
        composable(route = BottomBarScreen.Ruletka.route) {
            Ruletka()
        }
        composable(route = BottomBarScreen.Slot.route) {
            Slot()
        }
    }
}