package br.com.jonathanarodr.playmovie.feature.repository

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.repository.local.MovieLocalDataSource
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import br.com.jonathanarodr.playmovie.feature.repository.local.db.toMovie
import br.com.jonathanarodr.playmovie.feature.repository.remote.MovieRemoteDataSource
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieResponse
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.toMovie
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
        MovieResponse(
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
            type = MovieType.MOVIES,
        )
    )

    private val remoteResult = Result.success(remoteMovies)
    private val localResult = Result.success(localMovies)
    private val remoteDetailResult = Result.success(remoteMovies.first())
    private val localDetailResult = Result.success(localMovies.first())

    @Test
    fun `given repository when search movies then return remote list of movies`() {
        runTest {
            coEvery { remoteDataSource.searchMovies() } returns remoteResult

            val expeted = remoteResult.map { result -> result.map { it.toMovie(type = MovieType.MOVIES) } }
            val result = repository.searchMovies()

            assertEquals(expeted, result)
        }
    }

    @Test
    fun `given repository when search tv series then return remote list of movies`() {
        runTest {
            coEvery { remoteDataSource.searchTvSeries() } returns remoteResult

            val expeted = remoteResult.map { result -> result.map { it.toMovie(type = MovieType.SERIES) } }
            val result = repository.searchTvSeries()

            assertEquals(expeted, result)
        }
    }

    @Test
    fun `given repository when search movie detail then return remote detail of movie`() {
        runTest {
            coEvery { remoteDataSource.getMovieDetail(any()) } returns remoteDetailResult

            val expeted = remoteDetailResult.map { it.toMovie(type = MovieType.MOVIES) }
            val result = repository.getMovieDetail(id = 0L)

            assertEquals(expeted, result)
        }
    }

    @Test
    fun `given repository when search serie detail then return remote detail of serie`() {
        runTest {
            coEvery { remoteDataSource.getTvSerieDetail(any()) } returns remoteDetailResult

            val expeted = remoteDetailResult.map { it.toMovie(type = MovieType.SERIES) }
            val result = repository.getTvSerieDetail(id = 0L)

            assertEquals(expeted, result)
        }
    }

    @Test
    fun `given repository when search favorite movies then return local list of movies`() {
        runTest {
            coEvery { localDataSource.searchFavoriteMovies() } returns localResult

            val expected = localResult.map { result -> result.map { it.toMovie() } }
            val result = repository.searchFavoriteMovies()

            assertEquals(expected, result)
        }
    }

    @Test
    fun `given repository when search favorite movie detail then return local detail of movie`() {
        runTest {
            coEvery { localDataSource.getFavoriteMovie(any()) } returns localDetailResult

            val expeted = localDetailResult.map { it.toMovie() }
            val result = repository.getFavoriteMovie(id = 0L)

            assertEquals(expeted, result)
        }
    }

    @Test
    fun `given repository when insert favorite movie then call local datasource`() {
        runTest {
            repository.insertFavoriteMovie(remoteMovies.first().toMovie(type = MovieType.MOVIES))

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
            repository.removeFavoriteMovie(remoteMovies.first().toMovie(type = MovieType.MOVIES))

            coVerify {
                localDataSource.removeFavoriteMovie(
                    eq(localMovies.first())
                )
            }
        }
    }
}
