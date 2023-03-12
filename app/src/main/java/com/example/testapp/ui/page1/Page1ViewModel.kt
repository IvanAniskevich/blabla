package com.example.testapp.ui.page1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.R
import com.example.testapp.data.RetrofitInstance
import com.example.testapp.data.api.CategoriesModel
import com.example.testapp.data.api.FlashSaleModel
import com.example.testapp.data.api.LatestModel
import com.example.testapp.domain.ApiRepo
import kotlinx.coroutines.launch

class Page1ViewModel( private val repo : ApiRepo): ViewModel() {

    private var _listOfLatestModel = MutableLiveData<List<LatestModel>>()
    val listOfLatestModel: LiveData<List<LatestModel>> get() = _listOfLatestModel

    private var _listOfFlashSaleModel = MutableLiveData<List<FlashSaleModel>>()
    val listOfFlashSaleModel : LiveData<List<FlashSaleModel>> get() = _listOfFlashSaleModel

    private var _listOfCategories = MutableLiveData<List<CategoriesModel>>()
    val listOfCategories: LiveData<List<CategoriesModel>> get() = _listOfCategories

    init {
        getData()
        Log.v("wtf", "init")
    }

    private fun getData(){
        viewModelScope.launch {
            try {
                _listOfLatestModel.value = repo.getLatestList()
                _listOfFlashSaleModel.value = repo.getListFlashSale()
                _listOfCategories.value = setCategories()
            }catch (e: Exception){
                Log.v("wtf", "init get data exception = $e")

            }
        }
    }


    private fun setCategories() :List<CategoriesModel> =
        listOf(
            CategoriesModel(R.drawable.ic_launcher_foreground, "text"),
            CategoriesModel(R.drawable.ic_launcher_foreground, "text"),
            CategoriesModel(R.drawable.ic_launcher_foreground, "text"),
            CategoriesModel(R.drawable.ic_launcher_foreground, "text"),
            CategoriesModel(R.drawable.ic_launcher_foreground, "text"),
            CategoriesModel(R.drawable.ic_launcher_foreground, "text"),
        )



}