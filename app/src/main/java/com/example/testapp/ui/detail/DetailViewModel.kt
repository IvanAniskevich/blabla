package com.example.testapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.api.DetailItemModel
import com.example.testapp.domain.ApiRepo
import kotlinx.coroutines.launch

class DetailViewModel(private val repo: ApiRepo  ): ViewModel() {

    private var _detailItem = MutableLiveData<DetailItemModel>()
    val detailItem: LiveData<DetailItemModel> get() = _detailItem

    private var _selectedImage = MutableLiveData<String>()
    val selectedImage: LiveData<String> get() = _selectedImage

    private var _selectedColor = MutableLiveData<String>()
    val selectedColor: LiveData<String> get() = _selectedColor

    private var _quantityCounter = MutableLiveData<Int>()
    val quantityCounter: LiveData<Int> get() = _quantityCounter

    private var _finalCost = MutableLiveData<Int>()
    val finalCost: LiveData<Int> get() = _finalCost

    init {
        getData()
        _quantityCounter.value = 1
    }

    private fun getData(){
        viewModelScope.launch {
            try {
                _detailItem.value = repo.getDetailItem()
            }catch (e: Exception){
                Log.v("wtf", "init get detail item exception = $e")
            }
        }
    }

    fun selectImage(img : String){
        _selectedImage.value =img
    }
    fun minus(){
        val value = quantityCounter.value
        if (value != null) {
            if (value > 1){
                _quantityCounter.value = value!! - 1
                calculateTheCost()
            }
        }
    }
    fun plus(){
        val value = quantityCounter.value
        _quantityCounter.value = value!! + 1
        calculateTheCost()
    }
    private fun calculateTheCost(){
        val price = detailItem.value?.price
        val count = quantityCounter.value
        if (price != null && count != null) {
            _finalCost.value = price * count
        }
    }
    fun setColor(color: String){
        _selectedColor.value = color
    }
}