package com.example.users.ui.theme.screens.userdetails

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.users.ui.navigation.NavigationDestination

object UserDetailsDestination : NavigationDestination {
    override val route = "user_details_screen"
    const val name = "name"
    const val email = "email"
    const val address = "address"
    const val phoneNumber = "phone_number"
    const val webSite = "web_site"
    val routeWithArgs = "$route/{$name}/{$email}/{$address}/{$phoneNumber}/{$webSite}"
}

@Composable
fun UserDetailsScreen(
    name: String,
    email: String,
    address: String,
    phoneNumber: String,
    website: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = name)
        Text(text = email)
        Text(text = address)
        Text(text = phoneNumber)
        Text(text = website)
    }
}