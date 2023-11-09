package za.co.nemesisnet.budgettracker.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import za.co.nemesisnet.budgettracker.dataModels.Total
import za.co.nemesisnet.budgettracker.room.AppDatabase

class TotalViewModel(application: Application) : AndroidViewModel(application) {

    private val expenseDao = AppDatabase.getInstance(application).expenseDao()
    private val budgetDao = AppDatabase.getInstance(application).budgetDao()

    suspend fun getTotal(): Total {
        val totalExpenses = expenseDao.getTotalExpenses()
        val budget = budgetDao.getBudget(1)?.amount ?: 0.0
        return Total(totalExpenses, budget)
    }

}
