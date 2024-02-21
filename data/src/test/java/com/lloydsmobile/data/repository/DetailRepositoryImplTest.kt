package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.SingleUserDto
import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.services.DetailApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DetailRepositoryImplTest {
    @MockK
    lateinit var detailApiService: DetailApiService

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun testGetUserByIdEmpty() =
        runBlocking {
            coEvery { detailApiService.getUserById(any()) } returns Response.success(SingleUserDto(UserDto("", "", "", 0, "")))

            val detailRepositoryImpl = DetailRepositoryImpl(detailApiService)
            assertEquals("", detailRepositoryImpl.getUserById("1").data!!.url)
        }

    @Test
    fun testGetUserByIdSuccess() =
        runTest {
            val singleUserDto = SingleUserDto(UserDto("", "", "mobile", 1, ""))
            coEvery { detailApiService.getUserById(any()) } returns Response.success(singleUserDto)
            val detailRepositoryImpl = DetailRepositoryImpl(detailApiService)
            assertEquals("mobile", detailRepositoryImpl.getUserById("1").data!!.firstName)
        }
}
