package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.SingleUserDto
import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.services.DetailApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class DetailRepositoryImplTest {
    @Mock
    lateinit var detailApiService: DetailApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetUserByIdEmpty() =
        runTest {
            Mockito.`when`(detailApiService.getUserById("1")).thenReturn(Response.success(SingleUserDto(UserDto())))

            val detailRepositoryImpl = DetailRepositoryImpl(detailApiService)
            assertEquals("", detailRepositoryImpl.getUserById("1").data!!.avatar)
        }

    @Test
    fun testGetUserByIdSuccess() =
        runTest {
            val singleUserDto = SingleUserDto(UserDto("", "", "lloyds", 1, ""))
            Mockito.`when`(detailApiService.getUserById("1")).thenReturn(Response.success(singleUserDto))

            val detailRepositoryImpl = DetailRepositoryImpl(detailApiService)
            assertEquals("lloyds", detailRepositoryImpl.getUserById("1").data!!.firstName)
        }
}
