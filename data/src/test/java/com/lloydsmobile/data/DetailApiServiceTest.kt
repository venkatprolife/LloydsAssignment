package com.lloydsmobile.data

import com.lloydsmobile.data.services.DetailApiService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailApiServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var detailApiService: DetailApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        detailApiService =
            Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(DetailApiService::class.java)
    }

    @Test
    fun testGetUsersNull() =
        runTest {
            val mockResponse = MockResponse()
            mockResponse.setBody("null")
            mockWebServer.enqueue(mockResponse)

            val response = detailApiService.getUserById("1")
            mockWebServer.takeRequest()

            Assert.assertEquals(null, response.body())
        }

    @Test
    fun testGetUsersSuccess() =
        runTest {
            val mockResponse = MockResponse()
            val content = Helper.readFileResource("/details_response.json")
            mockResponse.setResponseCode(200)
            mockResponse.setBody(content)
            mockWebServer.enqueue(mockResponse)

            val response = detailApiService.getUserById("1")
            mockWebServer.takeRequest()

            Assert.assertNotNull(response.body())
            Assert.assertEquals("Bluth", response.body()!!.data.lastName)
        }

    @Test
    fun testGetUsersError() =
        runTest {
            val mockResponse = MockResponse()
            mockResponse.setResponseCode(404)
            mockResponse.setBody("Error")
            mockWebServer.enqueue(mockResponse)

            val response = detailApiService.getUserById("2")
            mockWebServer.takeRequest()

            Assert.assertEquals(false, response.isSuccessful)
            Assert.assertEquals(404, response.code())
        }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
