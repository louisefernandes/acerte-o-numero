package com.example.acerte

import android.util.Log
import kotlin.random.Random

class Jogo {
    private var numeroAleatorio = 0
    private var status = "Executando..."
    private var minimo = 1
    private var maximo = 100

    constructor() {
        this.numeroAleatorio = Random.nextInt(1, 100)
        Log.d("STATUS",this.numeroAleatorio.toString() )
    }

    fun fazerTentativa(palpite: Int) {
        if (palpite == numeroAleatorio) {
            status = "Ganhou!"
        }
        else {
            if (palpite < minimo || palpite > maximo) {
                status = "Ops... Perdeu!"

            } else if (palpite < numeroAleatorio) {
                minimo = palpite + 1

            } else if (palpite > numeroAleatorio) {
                maximo = palpite - 1
            }

            if (minimo == maximo) {
                status = "Ops... Perdeu!"
            }
        }
    }

    fun getStatus(): String {
        return status
    }

    fun getNumeroAleatorio(): Int {
        return numeroAleatorio
    }

    fun getIntervalo(): Pair<Int, Int> {
        return Pair(minimo, maximo)
    }

    fun getMinimo(): Int {
        return minimo
    }

    fun getMaximo(): Int {
        return maximo
    }

    fun novoJogo() {
        this.numeroAleatorio = Random.nextInt(1, 100)
        this.status = "Executando..."
        this.minimo = 1
        this.maximo = 100
        Log.d("STATUS",this.numeroAleatorio.toString() )
    }
}