package com.mahmoudhamdyae.mealz.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mahmoudhamdyae.domain.models.Meal
import com.mahmoudhamdyae.mealz.R

@Composable
fun MainScreen(
    uiState: MealzUiState,
    onSelectedItem: (Meal) -> Unit,
    modifier: Modifier = Modifier
) {
    if (uiState.isLoading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else if (uiState.error != null) {
        ErrorScreen(errorText = uiState.error, modifier = modifier)
    } else {
        if (uiState.mealz.isEmpty()) {
            ErrorScreen(errorText = "There is no internet connection", modifier = modifier)
        } else {
            MainList(mealz = uiState.mealz, onSelectedItem = onSelectedItem, modifier =  modifier)
        }
    }
}

@Composable
fun ErrorScreen(
    errorText: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = errorText,
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainList(
    mealz: List<Meal>,
    onSelectedItem: (Meal) -> Unit,
    modifier: Modifier = Modifier
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    // Fade in entry animation for the entire list
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn {
            itemsIndexed(mealz) { index, meal ->
                MealListItem(
                    meal = meal,
                    onSelectedItem = onSelectedItem,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        // Animate each list item to slide in vertically
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun MealListItem(
    meal: Meal,
    onSelectedItem: (Meal) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable {
            onSelectedItem(meal)
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 82.dp, maxHeight = 82.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(meal.strCategoryThumb)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = meal.strCategory,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(8.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = meal.strCategory,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1,
                )
                Text(
                    text = meal.strCategoryDescription,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.width(16.dp))
        }
    }
}