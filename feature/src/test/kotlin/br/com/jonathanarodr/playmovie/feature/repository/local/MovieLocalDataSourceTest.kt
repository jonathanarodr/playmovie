package br.com.jonathanarodr.playmovie.feature.repository.local

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieDao
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class MovieLocalDataSourceTest {

    private val localMovies = mockk<List<MovieEntity>>()
    private val dao = mockk<MovieDao>(relaxed = true)
    private val dataSource = MovieLocalDataSource(dao)
    private val expected = Result.success(localMovies)

    @Test
    fun `given datasource when search favorite movies then return list of movies from local storage`() {
        runTest {
            coEvery { dao.listMovies() } returns localMovies

            val result = dataSource.searchFavoriteMovies()

            assertEquals(expected, result)
        }
    }

    @Test
    fun `given datasource when insert favorite movie then insert movie from local storage`() {
        runTest {
            val movie = mockk<MovieEntity>()

            dataSource.insertFavoriteMovie(movie)

            coVerify {
                dao.insertMovie(
                    eq(movie)
                )
            }
        }
    }

    @Test
    fun `given datasource when remove favorite movie then delete movie from local storage`() {
        runTest {
            val movie = mockk<MovieEntity>()

            dataSource.removeFavoriteMovie(movie)

            coVerify {
                dao.deleteMovie(
                    eq(movie)
                )
            }
        }
    }
}
