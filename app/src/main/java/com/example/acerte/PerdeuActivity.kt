package com.example.acerte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class PerdeuActivity : AppCompatActivity() {

    private lateinit var btnPerdeu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perdeu)

        this.btnPerdeu = findViewById(R.id.btnPerdeuVolta)
        this.btnPerdeu.setOnClickListener{ voltar() }
    }

    fun voltar(){
        finish()
    }
}