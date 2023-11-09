import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import za.co.nemesisnet.budgettracker.dataModels.Expense
import za.co.nemesisnet.budgettracker.viewModels.EditExpenseViewModel
import za.co.nemesisnet.budgettracker.room.ExpenseDao
import za.co.nemesisnet.budgettracker.room.AppDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditExpenseScreen(
    viewModel: EditExpenseViewModel,
    expenseId: Long
) {
    val expense by produceState<Expense?>(initialValue = null) {
        val fetchedExpense = viewModel.getExpenseById(expenseId)
        value = fetchedExpense
    }

    var updatedAmount by remember { mutableStateOf(expense?.amount.toString() ?: "") }
    var updatedCategory by remember { mutableStateOf(expense?.category ?: "") }
    var updatedDate by remember { mutableStateOf(expense?.date ?: "") }
    var updatedDescription by remember { mutableStateOf(expense?.description ?: "") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        TopAppBar(
            title = { Text(text = "Edit Expense") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = updatedAmount,
            onValueChange = { updatedAmount = it },
            label = { Text("Amount") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = updatedCategory,
            onValueChange = { updatedCategory = it },
            label = { Text("Category") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = updatedDate,
            onValueChange = { updatedDate = it },
            label = { Text("Date") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = updatedDescription,
            onValueChange = { updatedDescription = it },
            label = { Text("Description") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val updatedExpense = Expense(
                    id = expense?.id ?: 0L,
                    amount = updatedAmount.toDoubleOrNull() ?: 0.0,
                    category = updatedCategory,
                    date = updatedDate,
                    description = updatedDescription
                )

                coroutineScope.launch {
                    viewModel.updateExpense(updatedExpense)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Save Expense")
        }
    }
}

@Composable
@Preview
fun EditExpenseScreenPreview() {
    val context = LocalContext.current // Assuming you have access to the Context
    val appDatabase = AppDatabase.getInstance(context)
    val expenseDao = appDatabase.expenseDao()

    val viewModel = EditExpenseViewModel(expenseDao)
    val expenseId = 1L // Provide an example expenseId

    EditExpenseScreen(viewModel, expenseId)
}
