package com.example.testapp.ui.logIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.UserRepo
import kotlinx.coroutines.launch

class LogInViewModel(private val repo: UserRepo) : ViewModel() {

    private var _isLogged = MutableLiveData<Boolean>()
    val isLogged: LiveData<Boolean> get() = _isLogged

    fun logIn(email: String) {
        viewModelScope.launch {
            val isExist = isUserExist(email)
            _isLogged.value = isExist == true
        }
    }

    private suspend fun isUserExist(email: String): Boolean {
        val user = repo.getUserByEmail(email)
        return user != null
    }

}