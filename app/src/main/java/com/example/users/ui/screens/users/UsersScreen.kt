package com.example.users.ui.theme.screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.users.R
import com.example.users.data.User
import com.example.users.ui.navigation.NavigationDestination


object UsersDestination : NavigationDestination {
    override val route = "users_screen"
}

@Composable
fun UsersScreen(
    usersUiState: UsersUiState, modifier: Modifier = Modifier,
    onItemClick: (String, String, String, String, String) -> Unit
) {
    when (usersUiState) {
        is UsersUiState.Loading -> LoadingScreen(modifier = Modifier.size(200.dp))

        is UsersUiState.Success -> UserList(
            usersUiState.users,
            modifier = modifier,
            onItemClick = onItemClick
        )

        is UsersUiState.Error -> ErrorScreen(modifier = modifier)
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun UserList(
    users: List<User>,
    modifier: Modifier = Modifier,
    onItemClick: (String, String, String, String, String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(users) {
            UserCard(
                it,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(
                            it.name,
                            it.email,
                            "Address: " +
                                    "${it.address.street}, " +
                                    "${it.address.suite}, " +
                                    "${it.address.city}",
                            it.phone,
                            it.website
                        )
                    }
            )
        }
    }
}

@Composable
fun UserCard(user: User, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = user.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = user.email)
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}
