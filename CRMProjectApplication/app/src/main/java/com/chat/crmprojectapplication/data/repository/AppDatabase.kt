package com.chat.crmprojectapplication.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chat.crmprojectapplication.data.local.LeadEntity
import com.chat.crmprojectapplication.data.remote.LeadDao

@Database(entities = [LeadEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun leadDao(): LeadDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "crm_db"
                ).allowMainThreadQueries() // since no MVVM
                    .build()
            }
            return INSTANCE!!
        }
    }
}
