package com.example.retrofitpostgetdeletepatch

import retrofit2.Call
import retrofit2.http.*

interface RetroService {
    @GET("users")
    @Headers("Accept:application/json\",\"Content-Type:application/json")
    fun getUserList(): Call<UserList>

    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun searchUsers(@Query("name") searchText: String): Call<UserList>

    @GET("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUser(@Path("user_id") user_id: String): Call<UserResponse>

    @POST("users")
    @Headers(
        "Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c"
    )
    fun createUser(@Body params: User): Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers(
        "Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c"
    )
    fun updateUser(@Path("user_id") user_id: String, @Body params: User): Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun deleteUser(@Path("user_id") user_id: String): Call<UserResponse>
}
