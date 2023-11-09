package za.co.nemesisnet.budgettracker.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import za.co.nemesisnet.budgettracker.viewModels.DeleteExpenseViewModel

@Composable
fun DeleteExpenseScreen(
    navController: NavController
) {
    val deleteExpenseViewModel = DeleteExpenseViewModel(LocalContext.current.applicationContext as Application)
    val expenseId = navController.currentBackStackEntry?.arguments?.getLong("expense_id")
        ?: throw IllegalStateException("Expense ID not found in arguments")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Are you sure you want to delete this expense?")

        Button(
            onClick = {
                deleteExpenseViewModel.deleteExpense(expenseId)
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Text("Delete Expense")
        }
    }
}
