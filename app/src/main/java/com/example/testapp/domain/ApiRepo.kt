package com.example.testapp.domain

import android.util.Log
import com.example.testapp.data.ApiServices
import com.example.testapp.data.api.FlashSaleModel
import com.example.testapp.data.api.LatestModel
import com.example.testapp.data.api.DetailItemModel

class ApiRepo(private val api : ApiServices) {
    suspend fun getLatestList(): List<LatestModel>{
        val r = api.getLatest().latest
        Log.v("wtf", "api latest  = $r")
        return r
    }

    suspend fun getListFlashSale(): List<FlashSaleModel>{
        val r = api.getFlashSale().flash_sale
        Log.v("wtf", "api sale  = $r")
        return r
    }

    suspend fun getDetailItem(): DetailItemModel{
        return api.getReebok()
    }
}