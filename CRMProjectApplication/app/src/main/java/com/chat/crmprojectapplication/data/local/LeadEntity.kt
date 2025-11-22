package com.chat.crmprojectapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leads")
data class LeadEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val company: String,
    val email: String,
    val phone: String,
    val status: String, // New, Contacted, Converted
    val notes: String?
)
