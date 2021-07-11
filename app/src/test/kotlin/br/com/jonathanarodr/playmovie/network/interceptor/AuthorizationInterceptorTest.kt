package br.com.jonathanarodr.playmovie.network.interceptor

import br.com.jonathanarodr.playmovie.BuildConfig
import io.mockk.every
import io.mockk.mockk
import okhttp3.Interceptor
import okhttp3.Request
import org.junit.Assert.assertEquals
import org.junit.Test

class AuthorizationInterceptorTest {

    private val authorization = "Bearer ${BuildConfig.AUTHORIZATION_KEY}"
    private val chain = mockk<Interceptor.Chain> {
        every { request() } returns Request.Builder()
            .url("https://google.com")
            .build()
    }

    private val interceptor = AuthorizationInterceptor()

    @Test
    fun `given interceptor when intercept request then add authorization token`() {
        val result = interceptor.intercept(chain)

        assertEquals(authorization, result.headers("Authorization"))
    }
}