package com.lloydsmobile.presentation.screens

import androidx.compose.foundation.layout.Arrangement
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
import com.lloydsmobile.presentation.ErrorMsg
import com.lloydsmobile.presentation.Loading
import com.lloydsmobile.presentation.R
import com.lloydsmobile.presentation.viewmodels.DetailViewModel

/**
 * Details of user screen
 */
@Composable
fun UserDetails() {
    val detailViewModel: DetailViewModel = hiltViewModel()
    val state = detailViewModel.userDetailsState.collectAsState().value

    if (state.isLoading) Loading()

    if (state.error.isNotBlank()) ErrorMsg(state.error)

    state.data?.let {
        DetailsView(it)
    }
}

@Composable
fun DetailsView(userModel: UserModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val listTitle =
            listOf(
                R.string.id,
                R.string.first_name,
                R.string.last_name,
                R.string.url,
                R.string.email,
            )
        userModel.run {
            val listValue = listOf(id, firstName, lastName, url, email)
            for (i in listTitle.indices) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = listTitle[i]),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = " : ${listValue[i]}",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }
        }
    }
}
