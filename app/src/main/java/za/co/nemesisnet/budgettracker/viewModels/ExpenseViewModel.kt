package za.co.nemesisnet.budgettracker.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import za.co.nemesisnet.budgettracker.dataModels.Budget
import za.co.nemesisnet.budgettracker.dataModels.Category
import za.co.nemesisnet.budgettracker.dataModels.Expense
import za.co.nemesisnet.budgettracker.room.AppDatabase


class ExpenseViewModel(application: Context) : AndroidViewModel(application as Application) {
    private val expenseDao = AppDatabase.getInstance(application).expenseDao()
    private val categoryDao = AppDatabase.getInstance(application).categoryDao()
    private val budgetDao = AppDatabase.getInstance(application).budgetDao()

    suspend fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    suspend fun getAllExpenses(): List<Expense> {
        return expenseDao.getAllExpenses()
    }

    suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(category)
    }

    suspend fun getAllCategories(): List<Category> {
        return categoryDao.getAllCategories()
    }

    fun getExpenseById(expenseId: Long): Expense? {

        return expenseDao.getExpenseById(expenseId)
    }

}
