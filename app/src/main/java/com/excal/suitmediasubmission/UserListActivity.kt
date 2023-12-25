package com.excal.suitmediasubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.excal.suitmediasubmission.data.ApiService
import com.excal.suitmediasubmission.data.User
import com.excal.suitmediasubmission.data.UserAdapter
import com.excal.suitmediasubmission.databinding.ActivityUserListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListActivity : AppCompatActivity(),UserAdapter.onItemClick{
    private val apiService = ApiService.create()
    private lateinit var rvUsers: RecyclerView

    private lateinit var binding:ActivityUserListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserListBinding.inflate(layoutInflater)

        rvUsers=binding.rvUsers

        setContentView(binding.root)
        getUserData()


    }
    private fun getUserData(){

        val call = apiService.getUsers()
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    val responseData = response.body()

                    if (responseData is List<*>) {
                        val users = responseData.filterIsInstance<User>()
                        rvUsers.apply {
                            val listUserAdapter = UserAdapter(users)
                            adapter = listUserAdapter
                            layoutManager = LinearLayoutManager(this@UserListActivity)
                            listUserAdapter.listener = this@UserListActivity
                        }
                    } else if (responseData is User) {
                        val user = responseData as User
                        rvUsers.apply {
                            val listUserAdapter = UserAdapter(listOf(user))
                            adapter = listUserAdapter
                            layoutManager = LinearLayoutManager(this@UserListActivity)
                            listUserAdapter.listener = this@UserListActivity
                        }
                    }
                } else {
                    Log.e("UserListActivity", "API call failed with code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("UserListActivity", "${t.message}")
            }
        })

    }
    override fun setOnItemClick(view: View, user: User) {
        var intent: Intent =Intent(this@UserListActivity,MainActivity::class.java)
        intent.putExtra("fName",user.first_name)
        intent.putExtra("lName",user.last_name)
        startActivity(intent)


    }
}