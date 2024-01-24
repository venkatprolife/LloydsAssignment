package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.data.services.UsersApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class UserRepositoryImplTest {

    @Mock
    lateinit var usersApiService: UsersApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getUsers_emptyList() = runTest{
        Mockito.`when`(usersApiService.getUsers()).thenReturn(Response.success(UsersListDto()))

        val userRepositoryImpl = UserRepositoryImpl(usersApiService)
        userRepositoryImpl.getUsers()
        assertEquals(0, userRepositoryImpl.userListDto.value.data.size)
    }

    @Test
    fun getUsers_successList() = runTest{
        val userListDto = UsersListDto(listOf<UserDto>(
            UserDto("", "", "", 1, ""),
            UserDto("", "", "", 2, ""),
            UserDto("", "", "", 3, "")
        ))

        Mockito.`when`(usersApiService.getUsers()).thenReturn(Response.success(userListDto))

        val userRepositoryImpl = UserRepositoryImpl(usersApiService)
        userRepositoryImpl.getUsers()
        assertEquals(3, userRepositoryImpl.userListDto.value.data.size)
        assertEquals(2, userRepositoryImpl.userListDto.value.data[1].id)
    }
}