package com.example.acerte

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    private lateinit var editTextNumber: TextInputEditText
    private lateinit var buttonChute: Button
    private lateinit var textViewIntervalo: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvUltimoGanhador: TextView
    private lateinit var jogo: Jogo

    private val nomeResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val nomeUltimoJogador = it.data?.getStringExtra("NOME_ULTIMOJOGADOR")
            if (nomeUltimoJogador != null) {
                this.tvUltimoGanhador.text = "Último jogador: $nomeUltimoJogador"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.editTextNumber = findViewById(R.id.editTextNumber)
        this.buttonChute = findViewById(R.id.btnChute)
        this.textViewIntervalo = findViewById(R.id.tvIntervalo)
        this.tvStatus = findViewById(R.id.tvStatus)
        this.tvUltimoGanhador = findViewById(R.id.tvUltimoGanhador)

        this.jogo = Jogo()
        this.tvStatus.setText(jogo.getStatus())
        atualizarInterface()

        buttonChute.setOnClickListener(View.OnClickListener {
            this.fazerPalpite()
        })

        tvStatus.setOnLongClickListener(View.OnLongClickListener {
            this.jogo.novoJogo()
            this.atualizarInterface()
            true
        })
    }

    private fun fazerPalpite() {
        if(jogo.getStatus() == "Executando...") {
            val palpiteText = editTextNumber.text.toString()
            editTextNumber.setText("")
            val palpite = palpiteText.toIntOrNull()

            if (palpite != null) {
                jogo.fazerTentativa(palpite)
                this.atualizarInterface()

                if (jogo.getStatus() != "Ganhou!" && jogo.getStatus() != "Ops... Perdeu!") {
                    Toast.makeText(this, "Continue tentando!", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Digite um número válido", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            this.tvStatus.setText(jogo.getStatus())
            this.atualizarInterface()
        }
    }
    private fun atualizarInterface() {
        this.textViewIntervalo.text = "${jogo.getMinimo()} - ${jogo.getMaximo()}"
        this.tvStatus.setText(jogo.getStatus())

        if (jogo.getStatus() == "Ganhou!") {
            this.indoTelaGanhou()
        }
        else if (jogo.getStatus() == "Ops... Perdeu!") {
            this.indoTelaPerdeu()
        }
    }

    private fun indoTelaPerdeu() {
        val intent = Intent(this, PerdeuActivity::class.java)
        startActivity(intent)
    }

    private fun indoTelaGanhou() {
        val intent = Intent(this, GanhouActivity::class.java)
        nomeResult.launch(intent)
    }

    override fun onRestart() {
        super.onRestart()
        this.jogo.novoJogo()
        this.atualizarInterface()
        Log.i("APP_ACERTE", "OnRestart - Main")
    }
}