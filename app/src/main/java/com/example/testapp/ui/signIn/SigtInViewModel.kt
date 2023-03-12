package com.example.testapp.ui.signIn

import android.util.Log
import androidx.lifecycle.*
import com.example.testapp.data.user.UserModel
import com.example.testapp.domain.UserRepo
import kotlinx.coroutines.launch

class SigtInViewModel(private val repo: UserRepo) : ViewModel() {


    val emailPattern =
        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"

    private var _isAdded = MutableLiveData<Boolean>()
    val isAdded: LiveData<Boolean> get() = _isAdded

    fun addUser(
        firstName: String,
        lastName: String,
        email: String
    ) {
        val user = UserModel(
            firstName = firstName,
            lastName = lastName,
            email = email
        )
        viewModelScope.launch {
            val isExist = isUserExist(user.email)
            Log.v("wtf", "add user isHav = $isExist")
            if (isExist == false) {
                createUser(user)
               _isAdded.value = true
                Log.v("wtf", "true isAdded value = ${_isAdded.value}")
            } else {
                _isAdded.value = false
                Log.v("wtf", "false isAdded value = ${_isAdded.value}")
            }
        }
    }

    private suspend fun createUser(user: UserModel) {
            repo.insertUser(user)
    }

    private suspend fun isUserExist(email: String): Boolean {
        val user = repo.getUserByEmail(email)
        Log.v("wtf", "isHavUser = $user")
        return user != null
    }
}