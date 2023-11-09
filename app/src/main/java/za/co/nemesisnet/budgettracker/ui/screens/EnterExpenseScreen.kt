package za.co.nemesisnet.budgettracker.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.nemesisnet.budgettracker.R
import za.co.nemesisnet.budgettracker.dataModels.Expense
import za.co.nemesisnet.budgettracker.room.AppDatabase
import za.co.nemesisnet.budgettracker.ui.nav.NavigationDestination

object EnterExpenseDestination : NavigationDestination {
    override val route = "enter_expense"
    override val titleRes = R.string.enter_expense
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterExpenseScreen(
    navController: NavController,

    onExpenseAdded: (Expense) -> Unit
) {
    // State variables for expense details
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current
    val database = AppDatabase.getInstance(context)

    suspend fun addExpenseToDatabase(expense: Expense) {
        val database = AppDatabase.getInstance(context)

        database.expenseDao().insertExpense(expense)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = EnterExpenseDestination.titleRes)) },
            modifier = Modifier
                .fillMaxWidth()
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp),
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        label = { Text("Expense Amount") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = category,
                        onValueChange = { category = it },
                        label = { Text("Expense Category") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = date,
                        onValueChange = { date = it },
                        label = { Text("Expense Date") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Expense Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    Button(
                        onClick = {
                            // Add expense to the database
                            CoroutineScope(Dispatchers.IO).launch {
                                addExpenseToDatabase(expense = Expense(
                                    id = 0, // Auto-generated ID
                                    amount = amount.toDoubleOrNull() ?: 0.0,
                                    category = category,
                                    date = date,
                                    description = description
                                ))
                            }

                            navController.navigate(HomeDestination.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                    ) {
                        Text("Add Expense")
                    }
                }
            }
        )
    }
}



@Composable
@Preview
fun EnterExpenseScreenPreview() {

    val navController = rememberNavController()

    EnterExpenseScreen(navController = navController) {}
}
