package com.vehicles.ktor.model

/**
 * Interfaces:
 * https://kotlinlang.org/docs/interfaces.html
 */
interface Vehicle {
    fun description(): String
    fun maxSpeed(): Int
    fun runDiagnostics() {
        println("Vehicle, Ready to Use!")
    }
}