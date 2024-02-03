package com.lloydsmobile.domain.usecases

import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.UserRepository
import com.lloydsmobile.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetUserUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var userRepository: UserRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testGetUserModelEmpty() =
        runTest {
            Mockito.`when`(userRepository.getUsers())
                .thenReturn(Resource.Success(UserListModel()))

            val getUserUseCase = GetUserUseCase(userRepository)
            val result = getUserUseCase()
            testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals(0, result.data!!.userList.size)
        }

    @Test
    fun testGetUserModelSuccess() =
        runTest {
            val userListModel =
                UserListModel(
                    listOf<UserModel>(
                        UserModel("lloyds1", "", "", "", 1),
                        UserModel("lloyds2", "", "", "", 2),
                        UserModel("lloyds3", "", "", "", 3),
                    ),
                )

            Mockito.`when`(userRepository.getUsers())
                .thenReturn(Resource.Success(userListModel))

            val getUserUseCase = GetUserUseCase(userRepository)
            val result = getUserUseCase()
            testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals("lloyds2", result.data!!.userList[1].firstName)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
