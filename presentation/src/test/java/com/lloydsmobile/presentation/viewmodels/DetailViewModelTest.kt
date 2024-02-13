package com.lloydsmobile.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.usecases.GetDetailsUseCase
import com.lloydsmobile.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    lateinit var getDetailsUseCase: GetDetailsUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testGetUserModelEmpty() =
        runTest {
            Mockito.`when`(savedStateHandle.get<String>(ArgumentMatchers.anyString()))
                .thenReturn("")

            Mockito.`when`(getDetailsUseCase(ArgumentMatchers.anyString()))
                .thenReturn(Resource.Success(UserModel("", "", "", "", 0)))

            val viewModel = DetailViewModel(getDetailsUseCase, savedStateHandle)
            viewModel.getUser()
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(0, viewModel.userDetailsState.value.data!!.id)
        }

    @Test
    fun testGetUserModelSuccess() =
        runTest {
            Mockito.`when`(savedStateHandle.get<String>(ArgumentMatchers.anyString()))
                .thenReturn("")

            Mockito.`when`(getDetailsUseCase(ArgumentMatchers.anyString()))
                .thenReturn(Resource.Success(UserModel("mobile", "", "", "", 1)))

            val viewModel = DetailViewModel(getDetailsUseCase, savedStateHandle)
            viewModel.getUser()
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals("mobile", viewModel.userDetailsState.value.data!!.firstName)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
