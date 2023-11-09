package za.co.nemesisnet.budgettracker.viewModels

import androidx.lifecycle.ViewModel
import za.co.nemesisnet.budgettracker.dataModels.Expense
import za.co.nemesisnet.budgettracker.room.ExpenseDao



class EditExpenseViewModel(private val expenseDao: ExpenseDao) : ViewModel() {

    suspend fun getExpenseById(expenseId: Long): Expense? {
        return expenseDao.getExpenseById(expenseId)
    }

    suspend fun updateExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }
}
