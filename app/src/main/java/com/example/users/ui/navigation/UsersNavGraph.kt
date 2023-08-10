package com.example.users.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.users.ui.theme.screens.userdetails.UserDetailsDestination
import com.example.users.ui.theme.screens.userdetails.UserDetailsScreen
import com.example.users.ui.theme.screens.users.UsersDestination
import com.example.users.ui.theme.screens.users.UsersScreen
import com.example.users.ui.theme.screens.users.UsersViewModel

@Composable
fun UsersNavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navHostController, startDestination = UsersDestination.route) {
        composable(route = UsersDestination.route) {
            val usersViewModel: UsersViewModel = viewModel()
            UsersScreen(
                usersViewModel.userUiState,
                modifier = Modifier.fillMaxSize()
            ) { name, email, address, phoneNumber, webSite ->
                navHostController.navigate(
                    "${UserDetailsDestination.route}" +
                            "/$name/$email/$address/$phoneNumber/$webSite"
                )
            }
        }

        composable(
            route = UserDetailsDestination.routeWithArgs,
            arguments = listOf(
                navArgument(UserDetailsDestination.name) {
                    type = NavType.StringType
                },
                navArgument(UserDetailsDestination.email) {
                    type = NavType.StringType
                },
                navArgument(UserDetailsDestination.address) {
                    type = NavType.StringType
                },
                navArgument(UserDetailsDestination.phoneNumber) {
                    type = NavType.StringType
                },
                navArgument(UserDetailsDestination.webSite) {
                    type = NavType.StringType
                },

                )
        ) {
            val args = it.arguments

            UserDetailsScreen(
                name = args?.getString(UserDetailsDestination.name) ?: "",
                email = args?.getString(UserDetailsDestination.email) ?: "",
                address = args?.getString(UserDetailsDestination.address) ?: "",
                phoneNumber = args?.getString(UserDetailsDestination.phoneNumber) ?: "",
                website = args?.getString(UserDetailsDestination.webSite) ?: "",
                modifier = Modifier.fillMaxSize()
            )

        }

    }
}