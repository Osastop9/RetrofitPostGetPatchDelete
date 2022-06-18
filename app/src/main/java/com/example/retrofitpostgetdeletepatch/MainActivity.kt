package com.example.retrofitpostgetdeletepatch

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_create_new_user.*
import kotlinx.android.synthetic.main.activity_main.*

lateinit var recyclerViewAdapter: RecyclerViewAdapter
lateinit var viewModel: MainActivityViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
        searchUsers()

        createUserButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateNewUserActivity::class.java))
        }
    }

    private fun searchUsers() {
        search_button.setOnClickListener {
            if (!TextUtils.isEmpty(searchEditText.text.toString())) {
                viewModel.searchUsersList(searchEditText.text.toString())
            } else {
                viewModel.getUsersList()
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.HORIZONTAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserListObservable().observe(
            this,
            Observer<UserList> {
                if (it == null) {
                    Toast.makeText(this, "Check network connection ", Toast.LENGTH_SHORT).show()
                } else {
                    recyclerViewAdapter.userList = it.data.toMutableList()
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }
        )
    }
}
