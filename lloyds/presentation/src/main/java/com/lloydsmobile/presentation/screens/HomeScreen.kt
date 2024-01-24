package com.lloydsmobile.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.presentation.viewmodels.UsersViewModel


@Composable
fun UserListView(onClick: (userId: Int) -> Unit) {
    val usersViewModel: UsersViewModel = hiltViewModel()
    usersViewModel.getUserList()
    val usersList = usersViewModel.userList.collectAsState()

    if (usersList.value.userList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...")
        }
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(
            space = 36.dp), content = {
            items(usersList.value.userList) { item ->
                ListViewItem(
                    item,
                    onClick = onClick
                )
            }
        })
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListViewItem(userModel: UserModel, onClick: (userId: Int) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick(userModel.id)
        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            GlideImage(
                model = userModel.avatar,
                contentDescription = "",
                modifier = Modifier.weight(
                    .2f
                ),
            )
            ListViewItemColumn(userModel.firstName, userModel.lastName, userModel.email, Modifier.weight(.8f))
        }
    }
}

@Composable
private fun ListViewItemColumn(
    firstName: String,
    lastName: String,
    email: String,
    modifier: Modifier
) {
    Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = modifier) {
        Text(text = firstName, fontWeight = FontWeight.Bold)
        Text(text = lastName, fontWeight = FontWeight.Thin)
        Text(text = email)
    }
}
