package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.data.services.UsersApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserRepositoryImplTest {
    @MockK
    lateinit var usersApiService: UsersApiService

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun testGetUsersEmptyList() =
        runTest {
            coEvery { usersApiService.getUsers() } returns Response.success(UsersListDto(
                emptyList()
            ))
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
}
