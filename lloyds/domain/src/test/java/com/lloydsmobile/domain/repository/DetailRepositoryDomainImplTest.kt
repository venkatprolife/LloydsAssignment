package com.lloydsmobile.domain.repository

import com.lloydsmobile.data.models.SingleUserDto
import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.repository.DetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class DetailRepositoryDomainImplTest {

    @Mock
    lateinit var detailRepository: DetailRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getUserById_emptyTest() = runTest {
        Mockito.`when`(detailRepository.userDto).thenReturn(MutableStateFlow(UserDto()))

        val detailRepositoryDomainImpl = DetailRepositoryDomainImpl(detailRepository)
        detailRepositoryDomainImpl.getUserById("1")
        assertEquals("", detailRepositoryDomainImpl.userModel.value.avatar)
    }

    @Test
    fun getUserById_successTest() = runTest {
        val userDto = UserDto("", "", "lloyds", 1, "")
        Mockito.`when`(detailRepository.userDto).thenReturn(MutableStateFlow(userDto))

        val detailRepositoryDomainImpl = DetailRepositoryDomainImpl(detailRepository)
        detailRepositoryDomainImpl.getUserById("1")
        assertEquals("lloyds", detailRepositoryDomainImpl.userModel.value.firstName)
    }
}