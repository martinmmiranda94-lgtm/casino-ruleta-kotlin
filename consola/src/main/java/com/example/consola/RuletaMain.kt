package com.example.consola

import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`)
    var plata = 10000
    val rojos = listOf(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36)
    val historial = mutableListOf<String>()

    while (plata > 0) {
        println("")
        println("Plata actual: " + plata)
        println("1. Apostar")
        println("2. Salir")
        println("3. Ver ultimas 10 apuestas")
        print("Opcion: ")

        val op = sc.nextLine().toIntOrNull()

        if (op == 2) {
            println("Chau!")
            break
        }

        if (op == 3) {
            println("HISTORIAL DE APUESTAS")
            if (historial.isEmpty()) {
                println("Todavia no hiciste ninguna apuesta.")
            } else {
                for (jugada in historial) {
                    println(jugada)
                }
            }
            continue
        }

        if (op == 1) {
            var apu = 0
            while (true) {
                print("Cuanto pones por cada jugada?: ")
                val n = sc.nextLine().toIntOrNull()
                if (n != null && n > 0) {
                    apu = n
                    break
                }
                println("Opcion invalida")
            }

            if (apu * 3 > plata) {
                println("No te alcanza la plata")
                continue
            }

            var num = -1
            while (true) {
                print("Elegi numero (0 a 36): ")
                val n = sc.nextLine().toIntOrNull()
                if (n != null && n >= 0 && n <= 36) {
                    num = n
                    break
                }
                println("Opcion invalida")
            }

            var col = 0
            while (true) {
                print("Elegi color (1=rojo, 2=negro): ")
                val n = sc.nextLine().toIntOrNull()
                if (n == 1 || n == 2) {
                    col = n
                    break
                }
                println("Opcion invalida")
            }

            var pi = 0
            while (true) {
                print("Elegi par/impar (1=par, 2=impar): ")
                val n = sc.nextLine().toIntOrNull()
                if (n == 1 || n == 2) {
                    pi = n
                    break
                }
                println("Opcion invalida")
            }

            val tirada = (Math.random() * 37).toInt()

            val esRojo = rojos.contains(tirada)
            var colorTirada = "Negro"
            if (tirada == 0) {
                colorTirada = "Cero"
            } else if (esRojo) {
                colorTirada = "Rojo"
            }

            var paridadTirada = "Impar"
            if (tirada == 0) {
                paridadTirada = "Cero"
            } else if (tirada % 2 == 0) {
                paridadTirada = "Par"
            }

            println("")
            println("--- RESULTADO DE LA RULETA ---")
            println("Salio el numero: " + tirada + " (" + colorTirada + ", " + paridadTirada + ")")

            var balanceJugada = 0

            if (num == tirada) {
                val g = apu * 35
                balanceJugada = balanceJugada + g
                println("Acierto Numero (" + num + "): +" + g)
            } else {
                balanceJugada = balanceJugada - apu
                println("Fallo Numero (" + num + "): -" + apu)
            }

            if (tirada != 0) {
                if ((col == 1 && esRojo) || (col == 2 && !esRojo)) {
                    balanceJugada = balanceJugada + apu
                    println("Acierto Color: +" + apu)
                } else {
                    balanceJugada = balanceJugada - apu
                    println("Fallo Color: -" + apu)
                }
            } else {
                balanceJugada = balanceJugada - apu
                println("Fallo Color: -" + apu)
            }

            val esPar = (tirada != 0 && tirada % 2 == 0)
            val esImpar = (tirada != 0 && tirada % 2 != 0)
            if ((pi == 1 && esPar) || (pi == 2 && esImpar)) {
                balanceJugada = balanceJugada + apu
                println("Acierto Par/Impar: +" + apu)
            } else {
                balanceJugada = balanceJugada - apu
                println("Fallo Par/Impar: -" + apu)
            }

            plata = plata + balanceJugada
            val registro = "Salio: " + tirada + " (" + colorTirada + ") | Balance: " + balanceJugada + " | Plata final: " + plata
            historial.add(registro)

            println("Balance de esta jugada: " + balanceJugada)
        }
    }

    if (plata <= 0) {
        println("Te quedaste sin plata. Juego terminado.")
    }
}