package com.lloydsmobile.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.usecases.GetUserUseCase
import com.lloydsmobile.domain.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
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
    fun testGetUserModel_empty() =
        runTest {
            coEvery { getUserUseCase() } returns Resource.Success(emptyList())

            val viewModel = UsersViewModel(getUserUseCase)
            viewModel.getUserList()
            testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals(0, viewModel.userListState.value.data.size)
        }

    @Test
    fun testGetUserModel_success() =
        runTest {
            val userListModels =
                    listOf(
                        UserModel("mobile1", "", "", "", 1),
                        UserModel("mobile2", "", "", "", 2),
                        UserModel("mobile3", "", "", "", 3),
                    )

            coEvery { getUserUseCase() } returns Resource.Success(userListModels)

            val viewModel = UsersViewModel(getUserUseCase)
            viewModel.getUserList()
            testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals("mobile2", viewModel.userListState.value.data[1].firstName)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
