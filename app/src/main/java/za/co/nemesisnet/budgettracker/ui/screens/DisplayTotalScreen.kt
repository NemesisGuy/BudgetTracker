package za.co.nemesisnet.budgettracker.ui.screens

import android.app.Application
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import za.co.nemesisnet.budgettracker.dataModels.Total
import za.co.nemesisnet.budgettracker.viewModels.TotalViewModel
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTotalScreen() {
    val totalViewModel = TotalViewModel(LocalContext.current.applicationContext as Application)
    val total = remember { mutableStateOf(Total(0.0, 0.0)) }

    LaunchedEffect(true) {
        total.value = totalViewModel.getTotal()
    }

    val percentageUsed = (total.value.totalExpenses / total.value.budget) * 100

    val borderColor by remember(percentageUsed) {
        mutableStateOf(
            when {
                percentageUsed <= 50 -> Color.Green
                percentageUsed <= 80 -> Color.Yellow
                else -> Color.Red
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopAppBar(
            title = { Text(text = "Total") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            actions = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp) // Adjust padding as needed
                )
            }
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Total Expenses: ${total.value.totalExpenses}")
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Budget: ${total.value.budget}")
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(1.dp, borderColor), // Apply border with calculated color
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Available: ${total.value.budget - total.value.totalExpenses}")
            }
        }
    }
}


@Preview
@Composable
fun DisplayTotalScreenPreview() {
    DisplayTotalScreen()
}
