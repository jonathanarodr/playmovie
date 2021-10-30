package br.com.jonathanarodr.playmovie.feature.repository.local

import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieDao
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieLocalDataSourceTest {

    private val localMovies = mockk<List<MovieEntity>>()
    private val dao = mockk<MovieDao>(relaxed = true)
    private val dataSource = MovieLocalDataSource(dao)

    @Test
    fun `given datasource when search favorite movies then return list of movies from local storage`() {
        runBlocking {
            coEvery { dao.listMovies() } returns localMovies

            val result = dataSource.searchFavoriteMovies()

            assertEquals(localMovies, result)
        }
    }

    @Test
    fun `given datasource when insert favorite movie then insert movie from local storage`() {
        runBlocking {
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
        runBlocking {
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
