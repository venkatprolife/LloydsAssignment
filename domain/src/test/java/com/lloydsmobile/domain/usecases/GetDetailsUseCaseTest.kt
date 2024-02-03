package com.lloydsmobile.domain.usecases

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.DetailRepository
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
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetDetailsUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var detailRepository: DetailRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testGetUserModelEmpty() =
        runTest {
            Mockito.`when`(detailRepository.getUserById(ArgumentMatchers.anyString()))
                .thenReturn(Resource.Success(UserModel()))

            val getDetailsUseCase = GetDetailsUseCase(detailRepository)
            val result = getDetailsUseCase("1")
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals("", result.data!!.firstName)
        }

    @Test
    fun testGetUserModelSuccess() =
        runTest {
            Mockito.`when`(detailRepository.getUserById(ArgumentMatchers.anyString()))
                .thenReturn(Resource.Success(UserModel("lloyds", "", "", "", 1)))

            val getDetailsUseCase = GetDetailsUseCase(detailRepository)
            val result = getDetailsUseCase("2")
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals("lloyds", result.data!!.firstName)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
