package za.co.nemesisnet.budgettracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import za.co.nemesisnet.budgettracker.R
import za.co.nemesisnet.budgettracker.ui.nav.DisplayExpensesDestination
import za.co.nemesisnet.budgettracker.ui.nav.DisplayTotalDestination
import za.co.nemesisnet.budgettracker.ui.nav.EnterBudgetDestination
import za.co.nemesisnet.budgettracker.ui.nav.EnterExpenseDestination
import za.co.nemesisnet.budgettracker.ui.nav.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
    val welcomeMessage = "Welcome!"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
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
                    Text(
                        text = HomeDestination.welcomeMessage,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Button(
                        onClick = {
                            navController.navigate(EnterExpenseDestination.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),

                    ) {
                        Text("Enter Expense")
                    }


                    Button(
                        onClick = {
                            navController.navigate(DisplayExpensesDestination.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                    ) {
                        Text("Display Expenses")
                    }

                    Button(
                        onClick = {
                            navController.navigate(EnterBudgetDestination.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                    ) {
                        Text("Enter Budget")
                    }
                    Button(

                        onClick = {
                            navController.navigate(DisplayTotalDestination.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                    ) {
                        Text("Display Total")
                    }

                }
            }
        )
    }
}



@Composable
@Preview
fun HomeScreenPreview() {

    HomeScreen(navController = NavController(LocalContext.current))
}
