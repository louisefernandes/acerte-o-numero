package com.example.acerte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class GanhouActivity : AppCompatActivity() {

    private lateinit var btnGanhou: Button
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganhou)

        this.btnGanhou = findViewById(R.id.btnGanhouVolta)
        this.editText = findViewById(R.id.editText)
        this.btnGanhou.setOnClickListener{ voltar() }
    }

    fun voltar(){
        val nomejogador = this.editText.text.toString()
        val intent = Intent().apply {
            putExtra("NOME_ULTIMOJOGADOR", nomejogador)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}