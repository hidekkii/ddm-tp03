package com.example.cadastroaluno

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cadastroaluno.databinding.ActivitySegundaBinding
import kotlinx.coroutines.launch
import kotlin.random.Random

class SegundaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySegundaBinding
    private lateinit var nomeAluno: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nomeAluno = intent.getStringExtra(MainActivity.EXTRA_STUDENT_NAME).orEmpty()
        binding.textStudentName.text = getString(R.string.student_name_label, nomeAluno)

        binding.buttonGenerateMatricula.setOnClickListener {
            // Gera um número de matrícula aleatório de 6 dígitos
            val matricula = Random.nextInt(100000, 999999)

            binding.textMatricula.text = getString(R.string.matricula_label, matricula)
            binding.textMatricula.visibility = android.view.View.VISIBLE

            // Salva o aluno no banco de dados local através do DAO
            val dao = AppDatabase.getDatabase(applicationContext).alunoDao()
            lifecycleScope.launch {
                dao.inserir(Aluno(nome = nomeAluno, matricula = matricula))
                Toast.makeText(
                    this@SegundaActivity,
                    R.string.student_saved,
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.buttonGenerateMatricula.isEnabled = false
        }
    }
}
