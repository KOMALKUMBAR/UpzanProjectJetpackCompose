package com.chat.crmprojectapplication.data.remote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.chat.crmprojectapplication.data.local.LeadEntity

@Dao
interface LeadDao {
    @Query("SELECT * FROM leads ORDER BY id DESC")
    fun getAllLeads(): List<LeadEntity>

    @Insert
    fun insertLead(lead: LeadEntity)

    @Update
    fun updateLead(lead: LeadEntity)

    @Delete
    fun deleteLead(lead: LeadEntity)
}
