package za.co.nemesisnet.budgettracker.viewModels

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.nemesisnet.budgettracker.dataModels.Expense
import za.co.nemesisnet.budgettracker.room.AppDatabase

class EnterExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val expenseDao = AppDatabase.getInstance(application).expenseDao()

    fun insertExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseDao.insertExpense(expense)
        }
    }
}
