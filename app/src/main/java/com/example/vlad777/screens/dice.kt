package com.example.vlad777.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vlad777.Player
import com.example.vlad777.R
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun Dice() {
    val context = LocalContext.current
    var firstDice by remember {
        mutableIntStateOf(0)
    }
    var secondDice by remember {
        mutableIntStateOf(0)
    }
    var sumDices by remember {
        mutableIntStateOf(0)
    }
    var diceBet by remember { mutableStateOf("") }

    var oddBet by remember {
        mutableStateOf("")
    }
    var evenBet by remember {
        mutableStateOf("")
    }
    var doublesBet by remember {
        mutableStateOf("")
    }
    var twoBet by remember {
        mutableStateOf("")
    }
    var threeBet by remember {
        mutableStateOf("")
    }
    var fourBet by remember {
        mutableStateOf("")
    }
    var fiveBet by remember {
        mutableStateOf("")
    }
    var sixBet by remember {
        mutableStateOf("")
    }
    var sevenBet by remember {
        mutableStateOf("")
    }
    var eightBet by remember {
        mutableStateOf("")
    }
    var nineBet by remember {
        mutableStateOf("")
    }
    var tenBet by remember {
        mutableStateOf("")
    }
    var elevenBet by remember {
        mutableStateOf("")
    }
    var twelveBet by remember {
        mutableStateOf("")
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Dices(firstDice, secondDice)
        Divider(
            color = Color.Black,
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text("Balance: ${Player.money}")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BetField(diceBet, onBetChanged = { updatedBet ->
                        diceBet = updatedBet
                    })
                    RollButton(onClick = {
                        if (evenBet.isEmpty() && oddBet.isEmpty() && doublesBet.isEmpty() &&
                            twoBet.isEmpty() && threeBet.isEmpty() && fourBet.isEmpty() &&
                            fiveBet.isEmpty() && sixBet.isEmpty() && sevenBet.isEmpty() &&
                            eightBet.isEmpty() && nineBet.isEmpty() && tenBet.isEmpty() &&
                            elevenBet.isEmpty() && twelveBet.isEmpty()
                        ) {
                            Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                        } else {
                            firstDice = Random.nextInt(1, 7)
                            secondDice = Random.nextInt(1, 7)
                            sumDices = firstDice + secondDice
                            if (evenBet.isNotEmpty()) {
                                if (sumDices % 2 == 0) {
                                    Player.money += evenBet.toInt() * 2
                                    Toast.makeText(
                                        context,
                                        "+${evenBet.toInt() * 2}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    println("evenBet: +${evenBet.toInt() * 2}, $sumDices")
                                } else {
                                    Toast.makeText(context, "-$evenBet", Toast.LENGTH_SHORT).show()
                                    println("evenBet: -$evenBet")
                                }
                            }
                            if (oddBet.isNotEmpty()) {
                                if (sumDices % 2 != 0) {
                                    Player.money += oddBet.toInt() * 2
                                    Toast.makeText(
                                        context,
                                        "+${oddBet.toInt() * 2}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    println("oddBet: +${oddBet.toInt() * 2}, $sumDices")
                                } else {
                                    Toast.makeText(context, "-$oddBet", Toast.LENGTH_SHORT).show()
                                    println("oddBet: -${oddBet}")
                                }
                            }
                            if (doublesBet.isNotEmpty()) {
                                if (firstDice == secondDice) {
                                    Player.money += doublesBet.toInt() * 6
                                    Toast.makeText(
                                        context,
                                        "+${doublesBet.toInt() * 6}   ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    println("doublesBet: +${doublesBet.toInt() * 6}, $firstDice, $secondDice")
                                } else {
                                    Toast.makeText(context, "-$doublesBet ", Toast.LENGTH_SHORT)
                                        .show()
                                    println("doublesBet: -$doublesBet")
                                }
                            }
                            if (twoBet.isNotEmpty()) {
                                if (sumDices == 2) {
                                    Player.money += twoBet.toInt() * 36
                                    Toast.makeText(
                                        context,
                                        "+${twoBet.toInt() * 36}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$twoBet", Toast.LENGTH_SHORT).show()
                                }
                            }
                            if (threeBet.isNotEmpty()) {
                                if (sumDices == 3) {
                                    Player.money += threeBet.toInt() * 18
                                    Toast.makeText(
                                        context,
                                        "+${threeBet.toInt() * 18}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$threeBet", Toast.LENGTH_SHORT).show()
                                }
                            }

                            if (fourBet.isNotEmpty()) {
                                if (sumDices == 4) {
                                    Player.money += fourBet.toInt() * 12
                                    Toast.makeText(
                                        context,
                                        "+${fourBet.toInt() * 12}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$fourBet", Toast.LENGTH_SHORT).show()
                                }
                            }
                            if (fiveBet.isNotEmpty()) {
                                if (sumDices == 5) {
                                    Player.money += fiveBet.toInt() * 9
                                    Toast.makeText(
                                        context,
                                        "+${fiveBet.toInt() * 9}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$fiveBet", Toast.LENGTH_SHORT).show()
                                }
                            }
                            if (sixBet.isNotEmpty()) {
                                if (sumDices == 6) {
                                    Player.money += sixBet.toInt() * 7
                                    Toast.makeText(
                                        context,
                                        "+${sixBet.toInt() * 7}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$sixBet", Toast.LENGTH_SHORT).show()
                                }
                            }
                            if (sevenBet.isNotEmpty()) {
                                if (sumDices == 7) {
                                    Player.money += sevenBet.toInt() * 6
                                    Toast.makeText(
                                        context,
                                        "+${sevenBet.toInt() * 6}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$sevenBet", Toast.LENGTH_SHORT).show()
                                }
                            }
                            if (eightBet.isNotEmpty()) {
                                if (sumDices == 8) {
                                    Player.money += eightBet.toInt() * 7
                                    Toast.makeText(
                                        context,
                                        "+${eightBet.toInt() * 7}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$eightBet", Toast.LENGTH_SHORT).show()
                                }
                            }
                            if (nineBet.isNotEmpty()) {
                                if (sumDices == 9) {
                                    Player.money += nineBet.toInt() * 9
                                    Toast.makeText(
                                        context,
                                        "+${nineBet.toInt() * 9}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$nineBet", Toast.LENGTH_SHORT).show()
                                }
                            }
                            if (tenBet.isNotEmpty()) {
                                if (sumDices == 10) {
                                    Player.money += tenBet.toInt() * 12
                                    Toast.makeText(
                                        context,
                                        "+${tenBet.toInt() * 12}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$tenBet", Toast.LENGTH_SHORT).show()
                                }
                            }
                            if (elevenBet.isNotEmpty()) {
                                if (sumDices == 11) {
                                    Player.money += elevenBet.toInt() * 18
                                    Toast.makeText(
                                        context,
                                        "+${elevenBet.toInt() * 18}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$elevenBet", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                            if (twelveBet.isNotEmpty()) {
                                if (sumDices == 12) {
                                    Player.money += twelveBet.toInt() * 36
                                    Toast.makeText(
                                        context,
                                        "+${twelveBet.toInt() * 36}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "-$twelveBet", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                            evenBet = ""
                            oddBet = ""
                            doublesBet = ""
                            twoBet = ""
                            threeBet = ""
                            fourBet = ""
                            fiveBet = ""
                            sixBet = ""
                            sevenBet = ""
                            eightBet = ""
                            nineBet = ""
                            tenBet = ""
                            elevenBet = ""
                            twelveBet = ""
                        }
                    })
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MenuItem(text = "+10") {
                        if (diceBet.isEmpty()) diceBet += 10
                        else diceBet = (diceBet.toInt() + 10).toString()
                    }
                    MenuItem(text = "+100") {
                        if (diceBet.isEmpty()) diceBet += 100
                        else diceBet = (diceBet.toInt() + 100).toString()
                    }
                    MenuItem(text = "+1000") {
                        if (diceBet.isEmpty()) diceBet += 1000
                        else diceBet = (diceBet.toInt() + 1000).toString()
                    }
                    MenuItem(text = "All") {
                        diceBet = Player.money.toString()
                    }
                    MenuItem(text = "Clear") {
                        diceBet = ""
                    }
                }
            }

        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BetButton(text = "Even (2x)",bet = evenBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (evenBet.isNotEmpty()) evenBet = (evenBet.toInt() + diceBet.toInt()).toString()
                    else evenBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }
            BetButton(text = "Odd (2x)",bet = oddBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (oddBet.isNotEmpty()) oddBet = (oddBet.toInt() + diceBet.toInt()).toString()
                    else oddBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }
            BetButton(text = "Doubles (6x)",bet = doublesBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (doublesBet.isNotEmpty()) doublesBet =
                        (doublesBet.toInt() + diceBet.toInt()).toString()
                    else doublesBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberBetButton(text = "2 (36x)",bet = twoBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    threeBet.isNotEmpty() || fourBet.isNotEmpty()
                    || fiveBet.isNotEmpty() || sixBet.isNotEmpty()
                    || sevenBet.isNotEmpty() || eightBet.isNotEmpty()
                    || nineBet.isNotEmpty() || tenBet.isNotEmpty()
                    || elevenBet.isNotEmpty() || twelveBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (twoBet.isNotEmpty()) twoBet = (twoBet.toInt() + diceBet.toInt()).toString()
                    else twoBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }
            NumberBetButton(text = "3 (18x)",bet = threeBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    twoBet.isNotEmpty() || fourBet.isNotEmpty()
                    || fiveBet.isNotEmpty() || sixBet.isNotEmpty()
                    || sevenBet.isNotEmpty() || eightBet.isNotEmpty()
                    || nineBet.isNotEmpty() || tenBet.isNotEmpty()
                    || elevenBet.isNotEmpty() || twelveBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (threeBet.isNotEmpty()) threeBet =
                        (threeBet.toInt() + diceBet.toInt()).toString()
                    else threeBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }
            NumberBetButton(text = "4 (12x)",bet = fourBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    twoBet.isNotEmpty() || threeBet.isNotEmpty()
                    || fiveBet.isNotEmpty() || sixBet.isNotEmpty()
                    || sevenBet.isNotEmpty() || eightBet.isNotEmpty()
                    || nineBet.isNotEmpty() || tenBet.isNotEmpty()
                    || elevenBet.isNotEmpty() || twelveBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (fourBet.isNotEmpty()) fourBet = (fourBet.toInt() + diceBet.toInt()).toString()
                    else fourBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberBetButton(text = "5 (9x)",bet = fiveBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    twoBet.isNotEmpty() || threeBet.isNotEmpty()
                    || fourBet.isNotEmpty() || sixBet.isNotEmpty()
                    || sevenBet.isNotEmpty() || eightBet.isNotEmpty()
                    || nineBet.isNotEmpty() || tenBet.isNotEmpty()
                    || elevenBet.isNotEmpty() || twelveBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (fiveBet.isNotEmpty()) fiveBet = (fiveBet.toInt() + diceBet.toInt()).toString()
                    else fiveBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }

            NumberBetButton(text = "6 (7x)",bet = sixBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    twoBet.isNotEmpty() || threeBet.isNotEmpty()
                    || fourBet.isNotEmpty() || fiveBet.isNotEmpty()
                    || sevenBet.isNotEmpty() || eightBet.isNotEmpty()
                    || nineBet.isNotEmpty() || tenBet.isNotEmpty()
                    || elevenBet.isNotEmpty() || twelveBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (sixBet.isNotEmpty()) sixBet = (sixBet.toInt() + diceBet.toInt()).toString()
                    else sixBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }

            NumberBetButton(text = "7 (6x)",bet = sevenBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    twoBet.isNotEmpty() || threeBet.isNotEmpty()
                    || fourBet.isNotEmpty() || fiveBet.isNotEmpty()
                    || sixBet.isNotEmpty() || eightBet.isNotEmpty()
                    || nineBet.isNotEmpty() || tenBet.isNotEmpty()
                    || elevenBet.isNotEmpty() || twelveBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (sevenBet.isNotEmpty()) sevenBet =
                        (sevenBet.toInt() + diceBet.toInt()).toString()
                    else sevenBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberBetButton(text = "8 (7x)",bet = eightBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    twoBet.isNotEmpty() || threeBet.isNotEmpty()
                    || fourBet.isNotEmpty() || fiveBet.isNotEmpty()
                    || sixBet.isNotEmpty() || sevenBet.isNotEmpty()
                    || nineBet.isNotEmpty() || tenBet.isNotEmpty()
                    || elevenBet.isNotEmpty() || twelveBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (eightBet.isNotEmpty()) eightBet =
                        (eightBet.toInt() + diceBet.toInt()).toString()
                    else eightBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }

            NumberBetButton(text = "9 (9x)",bet = nineBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    twoBet.isNotEmpty() || threeBet.isNotEmpty()
                    || fourBet.isNotEmpty() || fiveBet.isNotEmpty()
                    || sixBet.isNotEmpty() || sevenBet.isNotEmpty()
                    || eightBet.isNotEmpty() || tenBet.isNotEmpty()
                    || elevenBet.isNotEmpty() || twelveBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (nineBet.isNotEmpty()) nineBet = (nineBet.toInt() + diceBet.toInt()).toString()
                    else nineBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }

            NumberBetButton(text = "10 (12x)",bet = tenBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    twoBet.isNotEmpty() || threeBet.isNotEmpty()
                    || fourBet.isNotEmpty() || fiveBet.isNotEmpty()
                    || sixBet.isNotEmpty() || sevenBet.isNotEmpty()
                    || eightBet.isNotEmpty() || nineBet.isNotEmpty()
                    || elevenBet.isNotEmpty() || twelveBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (tenBet.isNotEmpty()) tenBet = (tenBet.toInt() + diceBet.toInt()).toString()
                    else tenBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberBetButton(text = "11 (18x)",bet = elevenBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    twoBet.isNotEmpty() || threeBet.isNotEmpty()
                    || fourBet.isNotEmpty() || fiveBet.isNotEmpty()
                    || sixBet.isNotEmpty() || sevenBet.isNotEmpty()
                    || eightBet.isNotEmpty() || nineBet.isNotEmpty()
                    || tenBet.isNotEmpty() || twelveBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (elevenBet.isNotEmpty()) elevenBet =
                        (elevenBet.toInt() + diceBet.toInt()).toString()
                    else elevenBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }
            NumberBetButton(text = "12 (36x)",bet = twelveBet) {
                if (diceBet == "") Toast.makeText(context, "make a bet", Toast.LENGTH_SHORT).show()
                else if (diceBet == "0") Toast.makeText(context, "not zero", Toast.LENGTH_SHORT).show()
                else if (
                    twoBet.isNotEmpty() || threeBet.isNotEmpty()
                    || fourBet.isNotEmpty() || fiveBet.isNotEmpty()
                    || sixBet.isNotEmpty() || sevenBet.isNotEmpty()
                    || eightBet.isNotEmpty() || nineBet.isNotEmpty()
                    || tenBet.isNotEmpty() || elevenBet.isNotEmpty()
                ) Toast.makeText(
                    context,
                    "you already have a bet on another number",
                    Toast.LENGTH_SHORT
                ).show()
                else if (diceBet.toInt() <= Player.money) {
                    Player.money -= diceBet.toInt()
                    if (twelveBet.isNotEmpty()) twelveBet =
                        (twelveBet.toInt() + diceBet.toInt()).toString()
                    else twelveBet += diceBet
                    diceBet = ""
                } else Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun MenuItem(text: String, onClick: () -> Unit) {
    OutlinedCard(
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text,
            modifier = Modifier
                .clickable { onClick.invoke() }
                .padding(6.dp))
    }
}


@Composable
fun Dices(firstDice: Int, secondDice: Int) {
    Row(
        modifier = Modifier
            .fillMaxHeight(0.25f)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(
                id = when (firstDice) {
                    1 -> R.drawable.ic_1
                    2 -> R.drawable.ic_2
                    3 -> R.drawable.ic_3
                    4 -> R.drawable.ic_4
                    5 -> R.drawable.ic_5
                    6 -> R.drawable.ic_6
                    else -> {
                        R.drawable.ic_1
                    }
                }
            ), contentDescription = "1"
        )
        Image(
            painterResource(
                id = when (secondDice) {
                    1 -> R.drawable.ic_1
                    2 -> R.drawable.ic_2
                    3 -> R.drawable.ic_3
                    4 -> R.drawable.ic_4
                    5 -> R.drawable.ic_5
                    6 -> R.drawable.ic_6
                    else -> {
                        R.drawable.ic_1
                    }
                }
            ), contentDescription = "1"
        )
    }
}

@Composable
fun RollButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick.invoke() }) {
        Text(text = "Roll")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetField(bet: String, onBetChanged: (String) -> Unit) {
    OutlinedTextField(
        value = bet,
        onValueChange = {
            val newText = it.filter { char -> char.isDigit() }
            onBetChanged(newText)
        },
        modifier = Modifier
            .fillMaxWidth(0.75f),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        )
    )
}

@Composable
fun BetButton(text: String,bet: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { onClick.invoke() }, shape = RoundedCornerShape(10.dp)) {
            Text(text)
        }
        Text(text = if (bet == "") "" else bet)
    }
}

@Composable
fun NumberBetButton(text: String,bet: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { onClick.invoke() }, shape = RoundedCornerShape(10.dp)) {
            Text(text)
        }
        Text(text = if (bet == "") "" else bet)
    }
}
