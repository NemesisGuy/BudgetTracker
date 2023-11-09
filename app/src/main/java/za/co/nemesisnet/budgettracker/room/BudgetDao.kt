package za.co.nemesisnet.budgettracker.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import za.co.nemesisnet.budgettracker.dataModels.Budget


@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: Budget)

    @Query("SELECT * FROM Budget WHERE id = :budgetId")
    suspend fun getBudget(budgetId: Int): Budget?
}
