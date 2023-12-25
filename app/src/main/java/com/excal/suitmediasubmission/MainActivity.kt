package com.excal.suitmediasubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.excal.suitmediasubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        var firstName=intent.getStringExtra("fName")?:""
        var lastName=intent.getStringExtra("lName")?:""
        var name:String
        name=firstName+" "+lastName

        setContentView(binding.root)
        binding.tv1.text=name
        binding.btnBack.setOnClickListener(){
            val  intent: Intent =Intent(this@MainActivity,palindrome::class.java)
            startActivity(intent)
        }
        binding.btn1.setOnClickListener(){
            val intent:Intent=Intent(this@MainActivity,UserListActivity::class.java)
            startActivity(intent)
        }

    }
}