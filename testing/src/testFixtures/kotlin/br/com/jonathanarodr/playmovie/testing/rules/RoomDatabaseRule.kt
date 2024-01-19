package br.com.jonathanarodr.playmovie.testing.rules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import org.junit.rules.ExternalResource
import java.io.IOException

class RoomDatabaseRule<T : RoomDatabase>(
    private val clazz: Class<T>,
) : ExternalResource() {

    lateinit var database: T

    override fun before() {
        super.before()

        ApplicationProvider.getApplicationContext<Context>().run {
            database = Room.inMemoryDatabaseBuilder(this, clazz).build()
        }
    }

    @Throws(IOException::class)
    override fun after() {
        database.close()
        super.after()
    }
}
