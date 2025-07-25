package com.example.moneycounterapp.ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneycounterapp.ui.theme.Pink41
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MoneyCounterPage(modifier: Modifier = Modifier) {
    var totalCount by remember { mutableIntStateOf(0) }
    var increaseCount by remember { mutableFloatStateOf(0F) }

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = Pink41
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MoneyText(totalMoney = totalCount)
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "${increaseCount.toInt()} TL",
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            MoneyButton(totalMoney = totalCount) {
                totalCount += increaseCount.toInt()
            }
            Slider(
                modifier = Modifier.padding(20.dp),
                value = increaseCount,
                onValueChange = {
                    increaseCount = it.roundToInt().toFloat()
                },
                valueRange = 0F..200F,
                steps = 7,
                thumb = {
                    Icon(imageVector = Icons.Filled.MonetizationOn,
                        contentDescription = "Money",
                        modifier = Modifier.size(50.dp)
                            .clip(CircleShape)
                            .background(color = Color.White)
                    )
                }
            )
        }
    }
}

@Composable
fun MoneyText(modifier: Modifier = Modifier, totalMoney: Int ) {
    Text(
        text = "$totalMoney TL",
        modifier = modifier,
        style = TextStyle(
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold
        )
    )
}

@Composable
fun MoneyButton(modifier: Modifier = Modifier,
                totalMoney: Int,
                updateTotalMoney: (Int) -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .clip(CircleShape)
            .clickable{
                updateTotalMoney(totalMoney)
            },
        elevation = CardDefaults.elevatedCardElevation(50.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(color = Color.White)
                .border(
                    width = 1.dp,
                    color = Color.Green,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center){
            Text(text = "Tab!", fontWeight = FontWeight.ExtraBold)
        }
    }

}