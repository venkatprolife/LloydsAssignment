package com.lloydsmobile.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.usecases.GetUserUseCase
import com.lloydsmobile.domain.util.Resource
import com.lloydsmobile.presentation.util.UserListState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception

/**
 * View model for the user list screen
 */
class UserViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getUserUseCase: GetUserUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testGetUserModelEmpty() =
        runTest {
            coEvery { getUserUseCase() } returns Resource.Success(emptyList())
            val viewModel = UsersViewModel(getUserUseCase)
            testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals(0, viewModel.userListState.value.data.size)
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

            coEvery { getUserUseCase() } returns Resource.Success(userListModels)

            val viewModel = UsersViewModel(getUserUseCase)
            testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals("mobile2", viewModel.userListState.value.data[1].firstName)
        }

    @Test
    fun testGetUserModelFailureError() {
        runTest {
            val message = "Something Went Wrong"
            coEvery { getUserUseCase() } returns Resource.Error(message)
            val viewModel = UsersViewModel(getUserUseCase)
            testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals(message, viewModel.userListState.value.error)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
