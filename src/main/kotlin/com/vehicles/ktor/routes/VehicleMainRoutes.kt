package com.vehicles.ktor.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getMainRoutes() {
    get("/") {
        call.respondText("Welcome to the Vehicle app", status = HttpStatusCode.OK)
    }
}

fun Application.registerMainRoutes() {
    routing {
        getMainRoutes()
    }
}