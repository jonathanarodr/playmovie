package br.com.jonathanarodr.playmovie.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.jonathanarodr.playmovie.model.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DataConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private val TAG = AppDatabase::class.java.simpleName
        private const val DATABASE_NAME = "playmovie"
        @Volatile private var instance: AppDatabase? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            return instance ?: buildDatabase(context).also {
                Log.d(TAG, "Carregando instância do banco de dados...")
                instance = it
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME)
                .build()
        }
    }
}