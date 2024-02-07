package com.lloydsmobile.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {
    var isStackAvailable by remember { mutableStateOf(false) }

    derivedStateOf {
        navController.previousBackStackEntry != null
    }
    DisposableEffect(navController) {
        val listener =
            NavController.OnDestinationChangedListener { controller, _, _ ->
                isStackAvailable = controller.previousBackStackEntry != null
            }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    val navigationIcon: (@Composable () -> Unit)? =
        if (isStackAvailable) {
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        tint = Color.White,
                    )
                }
            }
        } else {
            null
        }

    val route = navController.currentBackStackEntry?.destination?.route
    if (isStackAvailable && navigationIcon != null) {
        TopAppBar(
            title = { TextWhite(getTitleByRoute(route = route)) },
            colors =
                TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            navigationIcon = navigationIcon,
        )
    } else {
        TopAppBar(
            title = { TextWhite(getTitleByRoute(route = route)) },
            colors =
                TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
        )
    }
}

@Composable
fun getTitleByRoute(route: String?): String {
    return when (route) {
        USER_LIST -> stringResource(R.string.lloyds_app)
        USER_DETAILS_ROUTE -> stringResource(id = R.string.user_details_title)
        else -> stringResource(R.string.lloyds_app)
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(1f),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = R.string.loading),
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
fun ErrorMsg(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun TextWhite(text: String) =
    Text(
        text = text,
        color = Color.White,
    )
