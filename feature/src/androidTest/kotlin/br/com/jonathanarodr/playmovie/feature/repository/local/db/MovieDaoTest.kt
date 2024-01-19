package br.com.jonathanarodr.playmovie.feature.repository.local.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.FlakyTest
import androidx.test.filters.MediumTest
import br.com.jonathanarodr.playmovie.testing.rules.RoomDatabaseRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant
import java.util.Date

@RunWith(AndroidJUnit4::class)
@MediumTest
@FlakyTest
@ExperimentalCoroutinesApi
class MovieDaoTest {

    @get:Rule
    val rule = RoomDatabaseRule(AppDataBase::class.java)

    private lateinit var dao: MovieDao

    private val entity = MovieEntity(
        id = 0L,
        title = "title",
        overview = "overview",
        poster = "poster",
        backdrop = "backdrop",
        average = 0.0,
        releaseDate = Date.from(Instant.now()),
    )

    @Before
    fun setup() {
        dao = rule.database.movieDao()
    }

    @Test
    @Throws(Exception::class)
    fun givenDao_whenListMovies_thenVerifyStorage() {
        runTest {
            val result = dao.listMovies()

            assertThat(result).isEmpty()
        }
    }

    @Test
    @Throws(Exception::class)
    fun givenDao_whenInsertMovie_thenVerifyStorage() {
        runTest {
            dao.insertMovie(entity)

            val result = dao.listMovies()

            assertThat(result).contains(entity)
        }
    }

    @Test
    @Throws(Exception::class)
    fun givenDao_whenDeleteMovie_thenVerifyStorage() {
        runTest {
            dao.insertMovie(entity)
            dao.deleteMovie(entity)

            val result = dao.listMovies()

            assertThat(result).doesNotContain(entity)
        }
    }
}
