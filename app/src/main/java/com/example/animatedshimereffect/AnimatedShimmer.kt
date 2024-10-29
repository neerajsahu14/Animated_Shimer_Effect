package com.example.animatedshimereffect

import android.R.attr.repeatMode
import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedShimmer( modifier: Modifier = Modifier){
    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,

                easing = FastOutSlowInEasing
            ),
                    repeatMode = RepeatMode.Reverse,
    ), label = ""
    )
    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)

    )
    Spacer(Modifier.height(16.dp))

        ShimmerGridItem(brush)
}

@Composable
fun ShimmerGridItem(brush: Brush){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer( modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(brush))
        Spacer(Modifier.width(10.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush)
                .fillMaxWidth(fraction = 0.7f)
            )
            Spacer(Modifier.height(5.dp))
            Spacer(modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush)
                .fillMaxWidth(fraction = 0.9f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShimmerGridItemPreview(){
    ShimmerGridItem(brush = Brush.linearGradient(listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )))
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ShimmerGridItemNightPreview(){
    ShimmerGridItem(brush = Brush.linearGradient(listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(onNavigationIconClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text("My App") },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { /* Handle search action */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue
        )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar() {
    CenterAlignedTopAppBar(
        title = { Text("My App") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue // Customize the background color if needed
        )
    )
}