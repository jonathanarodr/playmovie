package br.com.jonathanarodr.playmovie.feature.repository

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.local.MovieLocalDataSource
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import br.com.jonathanarodr.playmovie.feature.repository.remote.MovieRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.util.Date

@SmallTest
class MovieRepositoryImplTest {

    private val remoteDataSource = mockk<MovieRemoteDataSource>()
    private val localDataSource = mockk<MovieLocalDataSource>(relaxed = true)
    private val repository = MovieRepositoryImpl(remoteDataSource, localDataSource)

    private val date = Date.from(Instant.now())

    private val movies = listOf(
        Movie(
            id = 0L,
            title = "title",
            overview = "overview",
            poster = "poster",
            backdrop = "backdrop",
            average = 0.0,
            releaseDate = date,
        )
    )

    private val localMovies = listOf(
        MovieEntity(
            id = 0L,
            title = "title",
            overview = "overview",
            poster = "poster",
            backdrop = "backdrop",
            average = 0.0,
            releaseDate = date,
        )
    )

    @Test
    fun `given repository when search movies then return remote list of movies`() {
        runBlocking {
            coEvery { remoteDataSource.searchMovies() } returns movies

            val result = repository.searchMovies()

            assertEquals(movies, result)
        }
    }

    @Test
    fun `given repository when search tv series then return remote list of movies`() {
        runBlocking {
            coEvery { remoteDataSource.searchTvSeries() } returns movies

            val result = repository.searchTvSeries()

            assertEquals(movies, result)
        }
    }

    @Test
    fun `given repository when search favorite movies then return local list of movies`() {
        runBlocking {
            coEvery { localDataSource.searchFavoriteMovies() } returns localMovies

            val result = repository.searchFavoriteMovies()

            assertEquals(movies, result)
        }
    }

    @Test
    fun `given repository when insert favorite movie then call local datasource`() {
        runBlocking {
            repository.insertFavoriteMovie(movies.first())

            coVerify {
                localDataSource.insertFavoriteMovie(
                    eq(localMovies.first())
                )
            }
        }
    }

    @Test
    fun `given repository when remove favorite movie then call local datasource`() {
        runBlocking {
            repository.removeFavoriteMovie(movies.first())

            coVerify {
                localDataSource.removeFavoriteMovie(
                    eq(localMovies.first())
                )
            }
        }
    }
}
