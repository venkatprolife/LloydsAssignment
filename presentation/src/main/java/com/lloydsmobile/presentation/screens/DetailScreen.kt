package com.lloydsmobile.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.presentation.R
import com.lloydsmobile.presentation.viewmodels.DetailViewModel

@Composable
fun UserDetails() {
    val detailViewModel: DetailViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        detailViewModel.getUser()
    }
    val result = detailViewModel.userDetailsState.collectAsState().value

    if (result.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.headlineSmall)
        }
    }

    if (result.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = result.error, style = MaterialTheme.typography.titleLarge)
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
        val listTitle = listOf(R.string.id, R.string.first_name, R.string.last_name, R.string.url, R.string.email)
        val listValue = listOf(userModel?.id, userModel?.firstName, userModel?.lastName, userModel?.url, userModel?.email)
        for (i in listTitle.indices) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = listTitle[i]), style = MaterialTheme.typography.titleMedium)
                    Text(text = " : ${listValue[i]}", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}
