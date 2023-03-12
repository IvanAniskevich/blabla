package com.example.testapp.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insert (user: UserModel)

    @Query("SELECT * from users where email = :email ")
   suspend fun getUserByEmail (email: String) : UserModel

}