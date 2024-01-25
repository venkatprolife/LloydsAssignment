package com.lloydsmobile.domain.repository

import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserRepositoryDomainImplTest {
    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getUsers_emptyTest() =
        runTest {
            Mockito.`when`(userRepository.userListDto).thenReturn(MutableStateFlow(UsersListDto()))

            val userRepositoryDomainImpl = UserRepositoryDomainImpl(userRepository)
            userRepositoryDomainImpl.getUsers()
            assertEquals(0, userRepositoryDomainImpl.userListModel.value.userList.size)
        }

    @Test
    fun getUsers_successTest() =
        runTest {
            val userListDto =
                UsersListDto(
                    listOf<UserDto>(
                        UserDto("", "", "", 1, ""),
                        UserDto("", "", "", 2, ""),
                        UserDto("", "", "", 3, ""),
                    ),
                )

            Mockito.`when`(userRepository.userListDto).thenReturn(MutableStateFlow(userListDto))

            val userRepositoryDomainImpl = UserRepositoryDomainImpl(userRepository)
            userRepositoryDomainImpl.getUsers()
            assertEquals(2, userRepositoryDomainImpl.userListModel.value.userList[1].id)
        }
}
