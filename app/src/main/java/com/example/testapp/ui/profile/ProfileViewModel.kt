package com.example.testapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.R
import com.example.testapp.data.user.UserModel
import com.example.testapp.domain.UserRepo
import kotlinx.coroutines.launch

class ProfileViewModel(private val repo: UserRepo) : ViewModel() {

    private var _listOfData = MutableLiveData<List<ProfileDataModel>>()
    val listOfData: LiveData<List<ProfileDataModel>> get() = _listOfData

    private var _currentUser = MutableLiveData<UserModel>()
    val currentUser: LiveData<UserModel> get() = _currentUser

    init {
        _listOfData.value = setList()
    }

    fun getUser(email: String) {
        viewModelScope.launch {
            _currentUser.value = repo.getUserByEmail(email)
        }
    }

    private fun setList(): List<ProfileDataModel> =
        listOf(
            ProfileDataModel(
                icon = R.drawable.ic_credit_card_24,
                text = "Trade store",
                text2 = null,
                ic2 = R.drawable.ic_arrow_right_24
            ),
            ProfileDataModel(
                icon = R.drawable.ic_credit_card_24,
                text = "Payment method",
                text2 = null,
                ic2 = R.drawable.ic_arrow_right_24
            ),
            ProfileDataModel(
                icon = R.drawable.ic_credit_card_24,
                text = "Balance",
                text2 = " $ 1593",
                ic2 = null
            ),
            ProfileDataModel(
                icon = R.drawable.ic_credit_card_24,
                text = "Trade history",
                text2 = null,
                ic2 = R.drawable.ic_arrow_right_24
            ),
            ProfileDataModel(
                icon = R.drawable.ic_cached_24,
                text = "Restore Purchase",
                text2 = null,
                ic2 = R.drawable.ic_arrow_right_24
            ),
            ProfileDataModel(
                icon = R.drawable.ic_help_outline_24,
                text = "RHelp",
                text2 = null,
                ic2 = null
            ),
            ProfileDataModel(
                icon = R.drawable.ic_login_24,
                text = "Lod out",
                text2 = null,
                ic2 = null
            ),
        )
}