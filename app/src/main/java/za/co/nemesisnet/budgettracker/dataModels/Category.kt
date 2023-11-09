package za.co.nemesisnet.budgettracker.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)