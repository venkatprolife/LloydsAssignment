package com.lloydsmobile.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.usecases.GetUserDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getUserDetailUseCase: GetUserDetailUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getUserModel_emptyTest() =
        runTest {
            Mockito.`when`(getUserDetailUseCase.getUserById(ArgumentMatchers.anyString()))
                .thenReturn(MutableStateFlow(UserModel()))

            val viewModel = DetailViewModel(getUserDetailUseCase, savedStateHandle)
            viewModel.getUser()
            assertEquals(0, viewModel.userModel.value?.id)
        }

    @Test
    fun getUserModel_successTest() =
        runTest {
            Mockito.`when`(getUserDetailUseCase.getUserById(ArgumentMatchers.anyString()))
                .thenReturn(MutableStateFlow(UserModel("lloyds", "", "", "", 1)))

            val viewModel = DetailViewModel(getUserDetailUseCase, savedStateHandle)
            viewModel.getUser()
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals("lloyds", viewModel.userModel.value?.firstName)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
