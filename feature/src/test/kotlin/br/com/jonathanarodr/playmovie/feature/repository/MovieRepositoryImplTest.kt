package br.com.jonathanarodr.playmovie.feature.repository

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.local.MovieLocalDataSource
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import br.com.jonathanarodr.playmovie.feature.repository.remote.MovieRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.util.Date

@SmallTest
@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    private val remoteDataSource = mockk<MovieRemoteDataSource>()
    private val localDataSource = mockk<MovieLocalDataSource>(relaxed = true)
    private val repository = MovieRepositoryImpl(remoteDataSource, localDataSource)

    private val date = Date.from(Instant.now())

    private val remoteMovies = listOf(
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

    private val remoteResult = Result.success(remoteMovies)
    private val localResult = Result.success(localMovies)

    @Test
    fun `given repository when search movies then return remote list of movies`() {
        runTest {
            coEvery { remoteDataSource.searchMovies() } returns remoteResult

            val result = repository.searchMovies()

            assertEquals(remoteResult, result)
        }
    }

    @Test
    fun `given repository when search tv series then return remote list of movies`() {
        runTest {
            coEvery { remoteDataSource.searchTvSeries() } returns remoteResult

            val result = repository.searchTvSeries()

            assertEquals(remoteResult, result)
        }
    }

    @Test
    fun `given repository when search favorite movies then return local list of movies`() {
        runTest {
            coEvery { localDataSource.searchFavoriteMovies() } returns localResult

            val result = repository.searchFavoriteMovies()

            assertEquals(remoteResult, result)
        }
    }

    @Test
    fun `given repository when insert favorite movie then call local datasource`() {
        runTest {
            repository.insertFavoriteMovie(remoteMovies.first())

            coVerify {
                localDataSource.insertFavoriteMovie(
                    eq(localMovies.first())
                )
            }
        }
    }

    @Test
    fun `given repository when remove favorite movie then call local datasource`() {
        runTest {
            repository.removeFavoriteMovie(remoteMovies.first())

            coVerify {
                localDataSource.removeFavoriteMovie(
                    eq(localMovies.first())
                )
            }
        }
    }
}
