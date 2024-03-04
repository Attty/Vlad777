package com.example.vlad777

import android.icu.text.StringPrepParseException
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
){
    object Dice: BottomBarScreen(
        route = "Dice",
        title = "Dice",
        icon = R.drawable.ic_dice
    )
    object Ruletka: BottomBarScreen(
        route = "Ruletka",
        title = "Rulete",
        icon = R.drawable.ic_ruletka
    )
    object Slot: BottomBarScreen(
        route = "Slot",
        title = "Slot",
        icon = R.drawable.ic_slot
    )
}
