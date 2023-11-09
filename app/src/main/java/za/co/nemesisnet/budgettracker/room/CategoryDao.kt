package za.co.nemesisnet.budgettracker.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import za.co.nemesisnet.budgettracker.dataModels.Category


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Query("SELECT * FROM Category")
    suspend fun getAllCategories(): List<Category>
}
