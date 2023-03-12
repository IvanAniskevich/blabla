package com.example.testapp.data

import android.content.ClipData
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.testapp.data.user.UserDao
import com.example.testapp.data.user.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ UserModel::class], version = 2, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao


    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null
        fun getDatabase(context: Context): ItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}