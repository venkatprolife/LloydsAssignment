package com.lloydsmobile.domain.usecases

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.DetailRepository
import com.lloydsmobile.domain.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetDetailsUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()
    @MockK
    lateinit var detailRepository: DetailRepository
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testGetUserModelEmpty() =
        runBlocking {
            coEvery { detailRepository.getUserById(any()) } returns Resource.Success(UserModel("", "", "", "", 0))

            val getDetailsUseCase = GetDetailsUseCase(detailRepository)
            val result = getDetailsUseCase("1")
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals("", result.data!!.firstName)
        }

    @Test
    fun testGetUserModelSuccess() =
        runBlocking {
            coEvery { detailRepository.getUserById(any()) } returns Resource.Success(UserModel("mobile", "", "", "", 1))

            val getDetailsUseCase = GetDetailsUseCase(detailRepository)
            val result = getDetailsUseCase("2")
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals("mobile", result.data!!.firstName)
        }

    @Test
    fun testGetUserModelFailureError() =
        runTest {
            val message = "Something Went Wrong"
            coEvery { detailRepository.getUserById(any()) } returns Resource.Error(message)
            val getDetailsUseCase = GetDetailsUseCase(detailRepository)
            val result = getDetailsUseCase("2")
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(message, result.message)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
