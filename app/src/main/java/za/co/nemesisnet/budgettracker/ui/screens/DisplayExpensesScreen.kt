import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import za.co.nemesisnet.budgettracker.dataModels.Expense
import za.co.nemesisnet.budgettracker.room.AppDatabase



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayExpensesScreen(expenses: List<Expense>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(text = "Expenses") },
            modifier = Modifier.fillMaxWidth(),
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(expenses) { expense ->
                ExpenseCard(expense = expense)
            }
        }
    }
}

@Composable
fun ExpenseCard(expense: Expense) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        ExpenseListItem(expense = expense)
    }
}

@Composable
@Preview
fun DisplayExpensesScreenPreview() {
    val sampleExpenses = listOf(
        Expense(1, 100.0, "Food", "2023-09-18", "Lunch"),
        Expense(2, 50.0, "Transportation", "2023-09-18", "Bus fare"),
        Expense(3, 200.0, "Shopping", "2023-09-18", "Clothes")
    )

    DisplayExpensesScreen(expenses = sampleExpenses)
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DisplayExpensesScreen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var expenses by remember { mutableStateOf<List<Expense>>(emptyList()) }

    LaunchedEffect(Unit) {
        val database = AppDatabase.getInstance(context)
        val expenseDao = database.expenseDao()
        expenses = expenseDao.getAllExpenses()
    }

    DisplayExpensesScreen(expenses = expenses)
}
