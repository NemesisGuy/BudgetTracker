package za.co.nemesisnet.budgettracker



import DisplayExpensesScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import za.co.nemesisnet.budgettracker.ui.nav.DeleteExpenseDestination
import za.co.nemesisnet.budgettracker.ui.nav.DisplayExpensesDestination
import za.co.nemesisnet.budgettracker.ui.nav.DisplayTotalDestination
import za.co.nemesisnet.budgettracker.ui.nav.EnterBudgetDestination
import za.co.nemesisnet.budgettracker.ui.nav.EnterExpenseDestination
import za.co.nemesisnet.budgettracker.ui.screens.DeleteExpenseScreen
import za.co.nemesisnet.budgettracker.ui.screens.DisplayTotalScreen
import za.co.nemesisnet.budgettracker.ui.screens.EnterBudgetScreen
import za.co.nemesisnet.budgettracker.ui.screens.EnterExpenseScreen
import za.co.nemesisnet.budgettracker.ui.screens.HomeDestination
import za.co.nemesisnet.budgettracker.ui.screens.HomeScreen
import za.co.nemesisnet.budgettracker.ui.theme.BudgetTrackerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BudgetTrackerApp()
                }
            }
        }
    }
}

@Composable
fun BudgetTrackerApp() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = HomeDestination.route) {
        composable(HomeDestination.route) {
            HomeScreen(navController)
        }
        composable(EnterExpenseDestination.route) {
            EnterExpenseScreen(

                navController = navController,
                onExpenseAdded = { /* Handle expense added */

                }
            )
        }



        composable(DisplayExpensesDestination.route) {
            DisplayExpensesScreen()
        }
        composable(EnterBudgetDestination.route) {
            EnterBudgetScreen(
                navController = navController,
                onBudgetAdded = { /* Handle budget added */ }
            )
        }
        composable(DisplayTotalDestination.route) {
            DisplayTotalScreen()
        }
        composable(DeleteExpenseDestination.route) {
            DeleteExpenseScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetTrackerAppPreview() {
    BudgetTrackerApp()
}
