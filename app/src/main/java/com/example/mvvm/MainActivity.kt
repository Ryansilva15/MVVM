package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private lateinit var viewModel: MainViewModel
private val personRepository = PersonRepository()

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener(this)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setObserver()
    }

    override fun onClick(v: View) {
        if (v.id == binding.buttonLogin.id) {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.doLogin()(email, password)
            }
        }
    }

    private fun setObserver() {
        viewModel.welcome().observe(this, Observer { message ->
            binding.textWelcome.text = message
        })
        viewModel.login().observe(this, Observer{
            if (it) {
                Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, "Falha!", Toast.LENGTH_LONG).show()

            }
        })
    }
}

private operator fun Unit.invoke(email: String, password: String) {
    TODO("Not yet implemented")
}
