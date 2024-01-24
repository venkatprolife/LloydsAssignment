package com.lloydsmobile.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.usecases.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
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
    fun getUserModel_emptyTest() = runTest {
        Mockito.`when`(getUserUseCase.getUsers())
            .thenReturn(MutableStateFlow(UserListModel()))

        val viewModel = UsersViewModel(getUserUseCase)
        viewModel.getUserList()
        Assert.assertEquals(0, viewModel.userList.value.userList.size)
    }

    @Test
    fun getUserModel_successTest() = runTest {
        val userListModel = UserListModel(
            listOf<UserModel>(
                UserModel("lloyds1", "", "", "", 1),
                UserModel("lloyds2", "", "", "", 2),
                UserModel("lloyds3", "", "", "", 3)
            )
        )

        Mockito.`when`(getUserUseCase.getUsers())
            .thenReturn(MutableStateFlow(userListModel))

        val viewModel = UsersViewModel(getUserUseCase)
        viewModel.getUserList()
        testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals("lloyds2", viewModel.userList.value.userList[1].firstName)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}