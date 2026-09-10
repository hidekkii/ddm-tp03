package com.example.cadastroaluno

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cadastroaluno.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNext.setOnClickListener {
            val nome = binding.editStudentName.text?.toString()?.trim().orEmpty()

            if (nome.isEmpty()) {
                Toast.makeText(this, R.string.empty_name_error, Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SegundaActivity::class.java)
                intent.putExtra(EXTRA_STUDENT_NAME, nome)
                startActivity(intent)
            }
        }
    }

    companion object {
        const val EXTRA_STUDENT_NAME = "extra_student_name"
    }
}
