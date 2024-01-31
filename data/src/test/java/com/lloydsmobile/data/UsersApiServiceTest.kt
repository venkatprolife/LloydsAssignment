package com.lloydsmobile.data

import com.lloydsmobile.data.services.UsersApiService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersApiServiceTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var usersApiService: UsersApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        usersApiService =
            Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsersApiService::class.java)
    }

    @Test
    fun testGetUsersNull() =
        runTest {
            val mockResponse = MockResponse()
            mockResponse.setBody("null")
            mockWebServer.enqueue(mockResponse)

            val response = usersApiService.getUsers()
            mockWebServer.takeRequest()

            Assert.assertEquals(null, response.body())
        }

    @Test
    fun testGetUsersSuccess() =
        runTest {
            val mockResponse = MockResponse()
            val content = Helper.readFileResource("/response.json")
            mockResponse.setResponseCode(200)
            mockResponse.setBody(content)
            mockWebServer.enqueue(mockResponse)

            val response = usersApiService.getUsers()
            mockWebServer.takeRequest()

            Assert.assertNotNull(response.body())
            Assert.assertEquals(6, response.body()!!.data.size)
        }

    @Test
    fun testGetUsersError() =
        runTest {
            val mockResponse = MockResponse()
            mockResponse.setResponseCode(404)
            mockResponse.setBody("Error")
            mockWebServer.enqueue(mockResponse)

            val response = usersApiService.getUsers()
            mockWebServer.takeRequest()

            Assert.assertEquals(false, response.isSuccessful)
            Assert.assertEquals(404, response.code())
        }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
