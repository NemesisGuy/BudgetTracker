import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import za.co.nemesisnet.budgettracker.dataModels.Expense


@Composable
fun ExpenseListItem(expense: Expense) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Amount: $${String.format("%.2f", expense.amount)}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(bottom = 4.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Category: ${expense.category}",
                style = TextStyle(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Date: ${expense.date}",
                style = TextStyle(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Description: ${expense.description}",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Composable
@Preview
fun ExpenseListItemPreview() {
    val sampleExpense = Expense(
        id = 1,
        amount = 100.0,
        category = "Food",
        date = "2023-09-18",
        description = "Lunch"
    )

    ExpenseListItem(expense = sampleExpense)
}
