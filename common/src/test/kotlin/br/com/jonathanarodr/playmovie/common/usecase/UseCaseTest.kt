package br.com.jonathanarodr.playmovie.common.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class UseCaseTest {

    private val task = mockk<(suspend () -> Unit)>()
    private val useCase = UseCase()

    @Test
    fun `given execute task when invoke is called with success then return valid result`() {
        runTest {
            coEvery { task.invoke() } returns Unit

            val result = useCase.execute {
                task()
            }

            assertTrue(result.isSuccess)
        }
    }

    @Test
    fun `given execute task when invoke is called with failure then return valid result`() {
        runTest {
            coEvery { task.invoke() } throws Exception("Task failure")

            val result = useCase.execute {
                task()
            }

            assertTrue(result.isFailure)
        }
    }
}
