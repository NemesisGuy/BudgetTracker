package za.co.nemesisnet.budgettracker.room

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
//import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
//import androidx.room.vo.Database
import za.co.nemesisnet.budgettracker.dataModels.Budget
import za.co.nemesisnet.budgettracker.dataModels.Category
import za.co.nemesisnet.budgettracker.dataModels.Expense

@Database(entities = [Expense::class, Category::class, Budget::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
    abstract fun budgetDao(): BudgetDao

    companion object {
        private const val DATABASE_NAME = "expense_tracker_database"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}




