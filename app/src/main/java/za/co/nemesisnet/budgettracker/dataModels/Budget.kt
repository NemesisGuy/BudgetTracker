package za.co.nemesisnet.budgettracker.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "Budget")
data class Budget(
    @PrimaryKey
    val id: Int = 1, // Assuming there's only one budget entry
    val amount: Double
)

