package com.lloydsmobile.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.presentation.viewmodels.DetailViewModel

@Composable
fun UserDetails() {
    val detailViewModel: DetailViewModel = hiltViewModel()
    detailViewModel.getUser()
    val result = detailViewModel.userDetailsState.collectAsState().value
    if (result.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = result.error)
        }
    }

    result.data?.let {
        DetailsView(it)
    }
}

@Composable
fun DetailsView(userModel: UserModel?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "ID : ${userModel?.id}")
        Text(text = "First Name : ${userModel?.firstName}")
        Text(text = "Last Name : ${userModel?.lastName}")
        Text(text = "Avatar : ${userModel?.avatar}")
        Text(text = "Email : ${userModel?.email}")
    }
}
