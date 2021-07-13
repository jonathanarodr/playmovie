package br.com.jonathanarodr.playmovie.core.commom

import br.com.jonathanarodr.playmovie.core.common.UseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test

@Ignore("Ignore tests because Result class return ClassCastException in kotlin v1.5.20 (issue KT-46477)")
class UseCaseTest {

    private val task = mockk<(suspend () -> Unit)>()
    private val useCase = UseCase()

    @Test
    fun `given execute task when invoke is called with success then return valid result`() {
        runBlocking {
            coEvery { task.invoke() } returns Unit

            val result = useCase.execute {
                task()
            }

            assertTrue(result.isSuccess)
        }
    }

    @Test
    fun `given execute task when invoke is called with failure then return valid result`() {
        runBlocking {
            coEvery { task.invoke() } throws Exception("Task failure")

            val result = useCase.execute {
                task()
            }

            assertTrue(result.isFailure)
        }
    }
}