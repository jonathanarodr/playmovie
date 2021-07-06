package br.com.jonathanarodr.playmovie.feature.repository.local.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    entities = [MovieEntity::class],
    exportSchema = false,
)
@TypeConverters(
    DateConverter::class,
)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "playmovie"

        operator fun invoke(context: Application): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                DATABASE_NAME
            ).build()
        }
    }

    abstract fun movieDao(): MovieDao
}