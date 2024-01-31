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
    fun testGetUserById_null() =
        runTest {
            Mockito.`when`(detailApiService.getUserById("1")).thenReturn(Response.success(null))

            val detailRepositoryImpl = DetailRepositoryImpl(detailApiService)
            detailRepositoryImpl.getUserById("1")
            assertEquals("", detailRepositoryImpl.userModel.value.avatar)
        }

    @Test
    fun testGetUserById_success() =
        runTest {
            val singleUserDto = SingleUserDto(UserDto("", "", "lloyds", 1, ""))
            Mockito.`when`(detailApiService.getUserById("1")).thenReturn(Response.success(singleUserDto))

            val detailRepositoryImpl = DetailRepositoryImpl(detailApiService)
            detailRepositoryImpl.getUserById("1")
            assertEquals("lloyds", detailRepositoryImpl.userModel.value.firstName)
        }
}
