package com.excal.suitmediasubmission

import UserAdapter
import UserViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.excal.suitmediasubmission.data.ApiService
import com.excal.suitmediasubmission.data.Data
import com.excal.suitmediasubmission.data.UserData
import com.excal.suitmediasubmission.databinding.ActivityUserListBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserListActivity : AppCompatActivity(), UserAdapter.onItemClick {
    private lateinit var rvUsers: RecyclerView
    private lateinit var binding: ActivityUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        rvUsers = binding.rvUsers
        setContentView(binding.root)
        binding.btnBack.setOnClickListener(){
            val intent=Intent(this@UserListActivity,MainActivity::class.java)
            startActivity(intent)
        }

        // Initialize the adapter here



        getUserData()
    }

    private fun getUserData() {
        var retrofit= Retrofit.Builder()
            .baseUrl("https:/reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        retrofit.getUsers().enqueue(object :retrofit2.Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if(response.isSuccessful){
                    Log.i("UserListActivity", "${response.body()}")
                    val data=response.body()!!
                    val userAdapter=UserAdapter(data)
                    rvUsers.apply {
                        layoutManager=LinearLayoutManager(this@UserListActivity)
                        adapter=userAdapter
                        adapter?.let {
                            adapter->
                            if(adapter is UserAdapter){
                                adapter.listener=this@UserListActivity
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                t.printStackTrace()
            }

        })


    }

    override fun setOnItemClick(view: View, user: Data) {
        var intent: Intent = Intent(this@UserListActivity, MainActivity::class.java)
        intent.putExtra("fName", user.first_name.toString())
        intent.putExtra("lName", user.last_name.toString())
        startActivity(intent)
    }
}
