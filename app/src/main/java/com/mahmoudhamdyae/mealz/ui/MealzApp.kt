package com.mahmoudhamdyae.mealz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mahmoudhamdyae.domain.models.Category
import com.mahmoudhamdyae.mealz.R

@Composable
fun MealzApp(
    onSelectedItem: (Category) -> Unit,
    viewModel: MealzViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar() }
    ) { contentPadding ->
        val state = viewModel.state
        MainScreen(
            state = state,
            onSelectedItem = onSelectedItem,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp)
            .background(MaterialTheme.colorScheme.primary),
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}