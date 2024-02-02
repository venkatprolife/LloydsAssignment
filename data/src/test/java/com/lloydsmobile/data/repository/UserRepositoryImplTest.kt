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
    fun testGetUsersEmptyList() =
        runTest {
            Mockito.`when`(usersApiService.getUsers()).thenReturn(Response.success(UsersListDto()))

            val userRepositoryImpl = UserRepositoryImpl(usersApiService)
            assertEquals(0, userRepositoryImpl.getUsers().data!!.userList.size)
        }

    @Test
    fun testGetUsersSuccessList() =
        runTest {
            val userListDto =
                UsersListDto(
                    listOf<UserDto>(
                        UserDto("", "", "", 1, ""),
                        UserDto("", "", "", 2, ""),
                        UserDto("", "", "", 3, ""),
                    ),
                )

            Mockito.`when`(usersApiService.getUsers()).thenReturn(Response.success(userListDto))

            val users = UserRepositoryImpl(usersApiService).getUsers()
            assertEquals(3, users.data!!.userList.size)
            assertEquals(2, users.data!!.userList[1].id)
        }
}
