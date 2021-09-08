package com.vehicles.ktor

import io.ktor.server.netty.*
import com.vehicles.ktor.routes.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*


fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }

    registerMainRoutes()
    registerVehiclesRouting()

}