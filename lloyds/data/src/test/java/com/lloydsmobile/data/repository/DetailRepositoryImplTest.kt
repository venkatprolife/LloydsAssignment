package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.SingleUserDto
import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.data.services.DetailApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
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
    fun getUserById_nullTest() = runTest{
        Mockito.`when`(detailApiService.getUserById("1")).thenReturn(Response.success(null))

        val detailRepositoryImpl = DetailRepositoryImpl(detailApiService)
        detailRepositoryImpl.getUserById("1")
        assertEquals("", detailRepositoryImpl.userDto.value?.avatar)
    }


    @Test
    fun getUserById_successTest() = runTest {
        val singleUserDto = SingleUserDto(UserDto("", "", "lloyds", 1, ""))
        Mockito.`when`(detailApiService.getUserById("1")).thenReturn(Response.success(singleUserDto))

        val detailRepositoryImpl = DetailRepositoryImpl(detailApiService)
        detailRepositoryImpl.getUserById("1")
        assertEquals("lloyds", detailRepositoryImpl.userDto.value?.first_name)
    }
}