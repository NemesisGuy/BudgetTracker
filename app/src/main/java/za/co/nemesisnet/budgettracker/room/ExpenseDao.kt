package za.co.nemesisnet.budgettracker.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import za.co.nemesisnet.budgettracker.dataModels.Expense


@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    @Query("SELECT * FROM Expense")
    suspend fun getAllExpenses(): List<Expense>

    @Query("SELECT * FROM Expense WHERE id = :expenseId")
    fun getExpenseById(expenseId: Long): Expense?

    @Query("SELECT SUM(amount) FROM Expense")
    suspend fun getTotalExpenses(): Double
    @Delete
    suspend fun deleteExpense(expense: Expense)

}