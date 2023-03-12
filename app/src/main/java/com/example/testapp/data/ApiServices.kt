package com.example.testapp.data

import com.example.testapp.data.api.*
import retrofit2.http.GET

interface ApiServices {
//    /cc0071a1-f06e-48fa-9e90-b1c2a61eaca7   latest
//    https://run.mocky.io/v3/a9ceeb6e-416d-4352-bde6-2203416576ac   flash_sale
//    https://run.mocky.io/v3/f7f99d04-4971-45d5-92e0-70333383c239   for page 2


    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(): LatestList

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSale(): FlashSaleList

    @GET("f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getReebok(): DetailItemModel

//    @GET("/api/exrates/rates")
//    suspend fun getItems(
//        @Query("ondate") data: String,
//        @Query("periodicity") periodicity: String
//    ): ArrayList<ItemJson>

}