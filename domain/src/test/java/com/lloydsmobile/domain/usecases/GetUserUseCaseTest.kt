package com.lloydsmobile.domain.usecases

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.UserRepository
import com.lloydsmobile.domain.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
class GetUserUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()

    @MockK
    lateinit var userRepository: UserRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testGetUserModelEmpty() =
        runTest {
            coEvery { userRepository.getUsers() } returns Resource.Success(emptyList())
            val getUserUseCase = GetUserUseCase(userRepository)
            val result = getUserUseCase()
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(0, result.data!!.size)
        }

    @Test
    fun testGetUserModelSuccess() =
        runTest {
            val userListModels =
                    listOf(
                        UserModel("mobile1", "", "", "", 1),
                        UserModel("mobile2", "", "", "", 2),
                        UserModel("mobile3", "", "", "", 3),
                    )

            coEvery { userRepository.getUsers() } returns Resource.Success(userListModels)

            val getUserUseCase = GetUserUseCase(userRepository)
            val result = getUserUseCase()
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals("mobile2", result.data!![1].firstName)
        }

    @Test
    fun testGetUserModelFailureError() =
        runTest {
            val message = "Something Went Wrong"
            coEvery { userRepository.getUsers() } returns Resource.Error(message)
            val getUserUseCase = GetUserUseCase(userRepository)
            val result = getUserUseCase()
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(message, result.message)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
