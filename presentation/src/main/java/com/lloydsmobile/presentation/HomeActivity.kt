package com.lloydsmobile.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.theme.LloydsAssignmentTheme
import com.lloydsmobile.presentation.screens.UserDetails
import com.lloydsmobile.presentation.screens.UserListView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val navUserList = "userlist"
    private val navUserDetails = "userdetails"
    private val userId = "userid"

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            setContent {
                LloydsAssignmentTheme {
                    App()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun App() {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                TopBar(navController)
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                NavHost(navController = navController, startDestination = navUserList) {
                    composable(route = navUserList) {
                        UserListView {
                            navController.navigate("$navUserDetails/$it")
                        }
                    }
                    composable(
                        route = "$navUserDetails/{$userId}",
                        arguments =
                        listOf(
                            navArgument(userId) {
                                type = NavType.StringType
                            },
                        ),
                    ) {
                        UserDetails()
                    }
                }
            }
        }
    }
}
