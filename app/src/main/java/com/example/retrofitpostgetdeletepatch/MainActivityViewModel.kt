package com.example.retrofitpostgetdeletepatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    var recyclerListData: MutableLiveData<UserList> = MutableLiveData()

    fun getUserListObservable(): MutableLiveData<UserList> {
        return recyclerListData
    }
    fun getUsersList() {
        val retroInstance = RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        val call = retroInstance.getUserList()
        call.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }

    fun searchUsersList(searchText: String) {
        val retroInstance = RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        val call = retroInstance.searchUsers(searchText)
        call.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }
}
