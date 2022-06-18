package com.example.retrofitpostgetdeletepatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNewUserViewModel: ViewModel() {
    var createNewUserLiveData: MutableLiveData<UserResponse?> = MutableLiveData()

    fun getcreateNewUserObservable(): MutableLiveData<UserResponse?>{
        return createNewUserLiveData
    }

    fun createUser(user: User){
        val retroInstance = RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        val call = retroInstance.createUser(user)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }
        })
    }
}