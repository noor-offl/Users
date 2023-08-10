package com.example.users.ui.theme.screens.users

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.users.data.Data
import com.example.users.data.User
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "UsersViewModel"

sealed interface UsersUiState {
    data class Success(val users: List<User>) : UsersUiState
    object Error : UsersUiState
    object Loading : UsersUiState
}

class UsersViewModel: ViewModel() {
    var userUiState: UsersUiState by mutableStateOf(UsersUiState.Loading)
        private set

    init {
        getUsers()
    }
    private fun getUsers() {
        viewModelScope.launch {
            userUiState = try {
                UsersUiState.Success(Data.retrofitService.getUsers())
            } catch (e: IOException) {
                Log.e(TAG, "getUsers: Error: ${e.message}")
                UsersUiState.Error
            } catch (e: HttpException) {
                UsersUiState.Error
            }
        }
    }
}