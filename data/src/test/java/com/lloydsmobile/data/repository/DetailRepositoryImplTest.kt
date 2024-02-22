package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.SingleUserDto
import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.data.services.DetailApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.lang.Exception

class DetailRepositoryImplTest {
    @MockK
    lateinit var detailApiService: DetailApiService

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun testGetUserByIdEmpty() =
        runBlocking {
            val singleUserDto = mockk<SingleUserDto>(relaxed = true)
            coEvery { detailApiService.getUserById(any()) } returns Response.success(singleUserDto)
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
    @Test
    fun testGetUsersException() {
        runTest {
            val message = "Something Went Wrong"
            coEvery {
                detailApiService.getUserById(any())
            } throws Exception(message)
            val details = DetailRepositoryImpl(detailApiService).getUserById("1")
            assertEquals(message, details.message)
        }
    }
}
