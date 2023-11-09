package za.co.nemesisnet.budgettracker.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Expense")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val amount: Double,
    val category: String,
    val date: String,
    val description: String
)