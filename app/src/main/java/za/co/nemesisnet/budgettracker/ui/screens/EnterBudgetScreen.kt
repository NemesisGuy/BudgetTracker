package za.co.nemesisnet.budgettracker.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import za.co.nemesisnet.budgettracker.R
import za.co.nemesisnet.budgettracker.dataModels.Budget
import za.co.nemesisnet.budgettracker.viewModels.EnterBudgetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterBudgetScreen(
    navController: NavController,
    onBudgetAdded: (Double) -> Unit
) {
    var budget by remember { mutableStateOf("") }
    val context = LocalContext.current
    val currentContext = LocalContext.current.applicationContext as Application

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopAppBar(
            title = { Text(text = stringResource (id = R.string.enter_budget)) },
            modifier = Modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = budget,
            onValueChange = { budget = it },
            label = { Text("Enter Budget") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val budgetValue = budget.toDoubleOrNull()
                if (budgetValue != null) {
                    val viewModel = EnterBudgetViewModel(currentContext)
                    viewModel.insertBudget(Budget(amount = budgetValue))
                    onBudgetAdded(budgetValue)
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Text("Save Budget")
        }
    }
}
