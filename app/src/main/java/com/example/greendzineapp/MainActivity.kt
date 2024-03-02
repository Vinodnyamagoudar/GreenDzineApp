package com.example.greendzineapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.greendzineapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()
            if(authenticateUser(email,password)){
                CoroutineScope(Dispatchers.Main).launch {
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                }
            }else{
                Toast.makeText(this@MainActivity, "User doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun authenticateUser(email: String, password: String): Boolean {
        val json: String? = loadJsonFromAsset(this, "users.json")
        val gson = Gson()
        val type = object : TypeToken<List<Users>>() {}.type
        val users: List<Users> = gson.fromJson(json, type)

        for (user in users) {
            if (user.email == email && user.password == password) {
                return true
            }
        }
        return false
    }

    private fun loadJsonFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}