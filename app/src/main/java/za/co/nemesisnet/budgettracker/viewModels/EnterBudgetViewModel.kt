package za.co.nemesisnet.budgettracker.viewModels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.nemesisnet.budgettracker.dataModels.Budget
import za.co.nemesisnet.budgettracker.room.AppDatabase

class EnterBudgetViewModel(application: Application) : AndroidViewModel(application) {

    private val budgetDao = AppDatabase.getInstance(application).budgetDao()

    fun insertBudget(budget: Budget) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetDao.insertBudget(budget)
        }
    }

    suspend fun getBudget(): Budget? {
        return budgetDao.getBudget(1)
    }
}

