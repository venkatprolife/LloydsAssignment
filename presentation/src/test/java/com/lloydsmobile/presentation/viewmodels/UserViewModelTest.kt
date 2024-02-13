package com.lloydsmobile.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.usecases.GetUserUseCase
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
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getUserUseCase: GetUserUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testGetUserModel_empty() =
        runTest {
            Mockito.`when`(getUserUseCase())
                .thenReturn(Resource.Success(UserListModel(emptyList())))

            val viewModel = UsersViewModel(getUserUseCase)
            viewModel.getUserList()
            testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals(0, viewModel.userListState.value.data.userList.size)
        }

    @Test
    fun testGetUserModel_success() =
        runTest {
            val userListModel =
                UserListModel(
                    listOf(
                        UserModel("mobile1", "", "", "", 1),
                        UserModel("mobile2", "", "", "", 2),
                        UserModel("mobile3", "", "", "", 3),
                    ),
                )

            Mockito.`when`(getUserUseCase())
                .thenReturn(Resource.Success(userListModel))

            val viewModel = UsersViewModel(getUserUseCase)
            viewModel.getUserList()
            testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals("mobile2", viewModel.userListState.value.data.userList[1].firstName)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
