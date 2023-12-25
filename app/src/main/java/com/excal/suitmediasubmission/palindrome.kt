package com.excal.suitmediasubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.excal.suitmediasubmission.databinding.ActivityPalindromeBinding

class palindrome : AppCompatActivity() {
    private lateinit var binding: ActivityPalindromeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPalindromeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name=binding.et1.text.toString()
        var btnCheck=binding.btn1
        var btnNext=binding.btn3
        btnCheck.setOnClickListener(){
            var sentence = binding.et2.text.toString()
            checkPalindrome(sentence)
        }
        btnNext.setOnClickListener(){
            var intent: Intent =Intent(this@palindrome,MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun checkPalindrome(text:String){
        val textRaw=text.replace("\\s".toRegex(), "").toLowerCase()
        val textBackward=textRaw.reversed()
        if(textBackward==textRaw){
            Toast.makeText(this@palindrome, "Is Palindrome ! $textBackward", Toast.LENGTH_SHORT ).show()
        }else{
            Toast.makeText(this@palindrome, "Is Not a Palindrome ! $textBackward", Toast.LENGTH_SHORT ).show()
        }
    }
}