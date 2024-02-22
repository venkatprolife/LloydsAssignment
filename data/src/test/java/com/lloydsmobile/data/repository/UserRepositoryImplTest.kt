package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.data.services.UsersApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.lang.Exception

class UserRepositoryImplTest {
    @MockK
    lateinit var usersApiService: UsersApiService
    @Before
    fun setUp() = MockKAnnotations.init(this)
    @Test
    fun testGetUsersEmptyList() =
        runTest {
            val usersListDto = mockk<UsersListDto>(relaxed = true)
            coEvery { usersApiService.getUsers() } returns Response.success(usersListDto)
            val userRepositoryImpl = UserRepositoryImpl(usersApiService)
            assertEquals(0, userRepositoryImpl.getUsers().data?.size)
        }

    @Test
    fun testGetUsersSuccessList() =
        runTest {
            val userListDto =
                UsersListDto(
                    listOf(
                        UserDto("", "", "", 1, ""),
                        UserDto("", "", "", 2, ""),
                        UserDto("", "", "", 3, ""),
                    ),
                )
            coEvery { usersApiService.getUsers() } returns Response.success(userListDto)

            val users = UserRepositoryImpl(usersApiService).getUsers()
            assertEquals(3, users.data?.size)
            assertEquals(2, users.data!![1].id)
        }
    @Test
    fun testGetUsersException() {
        runTest {
            val message = "Something Went Wrong"
            coEvery {
                usersApiService.getUsers()
            } throws Exception(message)
            val users = UserRepositoryImpl(usersApiService).getUsers()
            assertEquals(message, users.message)
        }
    }
}
