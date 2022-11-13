package com.example.weatherappavito.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherappavito.data.db.model.WeatherHourDb
import com.example.weatherappavito.data.db.model.WeatherNowDb
import com.example.weatherappavito.data.db.model.WeatherSevenDb

@Database(
    entities = [WeatherHourDb::class, WeatherNowDb::class, WeatherSevenDb::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    companion object {
        private var db: DataBase? = null
        private const val DB_NAME = "mail.db"
        private val LOCK = Any()

        fun getInstance(context: Context): DataBase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, DataBase::class.java, DB_NAME)
                    .build()
                db = instance
                return instance
            }

        }
    }


    abstract fun weatherDao(): WeatherDao
}