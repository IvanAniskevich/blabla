package com.example.testapp

import android.app.Application
import android.content.Context
import com.example.testapp.data.ApiServices
import com.example.testapp.data.ItemDatabase
import com.example.testapp.data.RetrofitInstance
import com.example.testapp.domain.ApiRepo
import com.example.testapp.domain.UserRepo
import com.example.testapp.ui.detail.DetailViewModel
import com.example.testapp.ui.logIn.LogInViewModel
import com.example.testapp.ui.page1.Page1ViewModel
import com.example.testapp.ui.profile.ProfileViewModel
import com.example.testapp.ui.signIn.SigtInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(modul)
        }
    }


    val modul = module {
        single<ItemDatabase> { ItemDatabase.getDatabase(this@BaseApplication )}
        single<UserRepo> {  UserRepo(get<ItemDatabase>().getUserDao())}
        single<ApiServices> { RetrofitInstance.API_SERVICES }
        single<ApiRepo> { ApiRepo(get()) }

        viewModel<SigtInViewModel>{
            SigtInViewModel(get())
        }

        viewModel<LogInViewModel>{
            LogInViewModel(get())
        }
        viewModel<Page1ViewModel>{
            Page1ViewModel(get())
        }
        viewModel<DetailViewModel>{
            DetailViewModel(get())
        }
        viewModel<ProfileViewModel>{
            ProfileViewModel(get())
        }
    }


}