package com.example.vlad777.screens.ruletka

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vlad777.Player
import com.example.vlad777.R
import com.example.vlad777.screens.BetField
import com.example.vlad777.screens.MenuItem
import kotlin.math.roundToInt

@SuppressLint("MutableCollectionMutableState")
@Preview(showBackground = true)
@Composable
fun Ruletka() {
    val context = LocalContext.current
    var rotationValue by remember {
        mutableFloatStateOf(720f)
    }
    var color by remember {
        mutableStateOf("")
    }
    var animatedColor by remember {
        mutableStateOf("")
    }
    var ruletkaBet by remember {
        mutableStateOf("")
    }
    var redBet by remember {
        mutableStateOf("")
    }
    var blackBet by remember {
        mutableStateOf("")
    }
    var greenBet by remember {
        mutableStateOf("")
    }
    var list by remember {
        mutableStateOf(mutableListOf<String>())
    }
    list = Utils.gameHistory

    val animatedRotation: Float by animateFloatAsState(
        targetValue = rotationValue,
        label = "",
        animationSpec = tween(
            durationMillis = 4000
        ),
        finishedListener = {
            Player.isSpinning = false
            val index = (360f - (it % 360)) / (360f / 37)
            color = Utils.list[index.roundToInt()]
            list.add(element = color)
            Utils.gameHistory = list
            println(Utils.gameHistory)
            if (redBet.isNotEmpty()) {
                if (color == "Red") {
                    Player.money += (redBet.toInt() * 2)
                    Toast.makeText(context, "+${redBet.toInt() * 2}", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "-${redBet}", Toast.LENGTH_SHORT).show()
            }
            if (blackBet.isNotEmpty()) {
                if (color == "Black") {
                    Player.money += (blackBet.toInt() * 2)
                    Toast.makeText(context, "+${blackBet.toInt() * 2}", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "-${blackBet}", Toast.LENGTH_SHORT).show()
            }
            if (greenBet.isNotEmpty()) {
                if (color == "Green") {
                    Player.money += (greenBet.toInt() * 13)
                    Toast.makeText(context, "+${greenBet.toInt() * 13}", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "-${greenBet}", Toast.LENGTH_SHORT).show()
            }
            redBet = ""
            blackBet = ""
            greenBet = ""
        }
    )
    if (animatedRotation > 720f) {
        val animatedIndex = (360f - (animatedRotation % 360)) / (360f / 37)
        animatedColor = Utils.list[animatedIndex.roundToInt()]
    }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Image(
                painterResource(id = R.drawable.rullete), contentDescription = "",
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .rotate(animatedRotation)
            )
            Image(
                painterResource(
                    id = when (animatedColor) {
                        "Red" -> R.drawable.ic_arrow_red
                        "Black" -> R.drawable.ic_arrow_black
                        else -> R.drawable.ic_arrow_green
                    }
                ), contentDescription = "",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            History(list)
        }
        Divider(
            color = Color.Black,
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text("Balance: ${Player.money}")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BetField(bet = ruletkaBet, onBetChanged = {
                        ruletkaBet = it
                    })
                    SpinButton {
                        if (redBet.isNotEmpty() || blackBet.isNotEmpty() || greenBet.isNotEmpty()) {
                            Player.isSpinning = true
                            rotationValue += (720..1080).random().toFloat()
                        } else Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MenuItem(text = "+10") {
                        if (ruletkaBet.isEmpty()) ruletkaBet += 10
                        else ruletkaBet = (ruletkaBet.toInt() + 10).toString()
                    }
                    MenuItem(text = "+100") {
                        if (ruletkaBet.isEmpty()) ruletkaBet += 100
                        else ruletkaBet = (ruletkaBet.toInt() + 100).toString()
                    }
                    MenuItem(text = "+1000") {
                        if (ruletkaBet.isEmpty()) ruletkaBet += 1000
                        else ruletkaBet = (ruletkaBet.toInt() + 1000).toString()
                    }
                    MenuItem(text = "All") {
                        ruletkaBet = Player.money.toString()
                    }
                    MenuItem(text = "Clear") {
                        ruletkaBet = ""
                    }
                }
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BetButton(
                        bet = redBet,
                        text = "Red (2x)",
                        color = Color.Red,
                        colorText = Color.Black,
                        onClick = {
                            if (!Player.isSpinning) {
                                if (ruletkaBet == "")
                                    Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                                else if (ruletkaBet == "0")
                                    Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                                else if (ruletkaBet.toInt() <= Player.money) {
                                    Player.money -= ruletkaBet.toInt()
                                    if (redBet.isNotEmpty()) redBet =
                                        (redBet.toInt() + ruletkaBet.toInt()).toString()
                                    else redBet += ruletkaBet
                                    ruletkaBet = ""
                                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT)
                                    .show()
                            } else Toast.makeText(context, "wheel is spinning", Toast.LENGTH_SHORT)
                                .show()
                        },
                        onTextClick = {
                            Player.money += redBet.toInt()
                            redBet = ""
                            Toast.makeText(context, "bet is cancelled", Toast.LENGTH_SHORT).show()
                        }
                    )
                    BetButton(
                        bet = blackBet,
                        text = "Black (2x)",
                        color = Color.Black,
                        colorText = Color.White,
                        onClick = {
                            if (!Player.isSpinning) {
                                if (ruletkaBet == "")
                                    Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                                else if (ruletkaBet == "0")
                                    Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                                else if (ruletkaBet.toInt() <= Player.money) {
                                    Player.money -= ruletkaBet.toInt()
                                    if (blackBet.isNotEmpty()) blackBet =
                                        (blackBet.toInt() + ruletkaBet.toInt()).toString()
                                    else blackBet += ruletkaBet
                                    ruletkaBet = ""
                                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT)
                                    .show()
                            } else Toast.makeText(context, "wheel is spinning", Toast.LENGTH_SHORT)
                                .show()
                        },
                        onTextClick = {
                            Player.money += blackBet.toInt()
                            blackBet = ""
                            Toast.makeText(context, "bet is cancelled", Toast.LENGTH_SHORT).show()
                        }
                    )
                    BetButton(
                        bet = greenBet,
                        text = "Green (13x)",
                        color = Color.Green,
                        colorText = Color.Black,
                        onClick = {
                            if (!Player.isSpinning) {
                                if (ruletkaBet == "")
                                    Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                                else if (ruletkaBet == "0")
                                    Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                                else if (ruletkaBet.toInt() <= Player.money) {
                                    Player.money -= ruletkaBet.toInt()
                                    if (greenBet.isNotEmpty()) greenBet =
                                        (greenBet.toInt() + ruletkaBet.toInt()).toString()
                                    else greenBet += ruletkaBet
                                    ruletkaBet = ""
                                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT)
                                    .show()
                            } else Toast.makeText(context, "wheel is spinning", Toast.LENGTH_SHORT)
                                .show()
                        },
                        onTextClick = {
                            Player.money += greenBet.toInt()
                            greenBet = ""
                            Toast.makeText(context, "bet is cancelled", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun SpinButton(onClick: () -> Unit) {
    Button(onClick = {
        if (!Player.isSpinning) onClick.invoke()
    }) {
        Text("Spin")
    }
}

@Composable
fun BetButton(bet: String, text: String, color: Color, colorText: Color, onClick: () -> Unit, onTextClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { onClick.invoke() }, shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color
            )
        ) {
            Text(text, color = colorText,
                modifier = Modifier.clickable {
                    onTextClick.invoke()
                })
        }
        Text(text = if (bet == "") "" else bet)
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun History(list: MutableList<String>) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .border(0.5.dp, Color.Black)
    ) {
        LazyRow() {
            itemsIndexed(list) { index, item ->
                println("item  = item")
                Text(
                    if (item == "Green") "G " else if (item == "Black") "B " else if (item == "Red") "R " else " ",
                    color = when (item) {
                        "Green" -> Color.Green
                        "Black" -> Color.Black
                        "Red" -> Color.Red
                        else -> Color.Yellow
                    },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }

}
