package com.vehicles.ktor.routes

import com.vehicles.ktor.model.CarVehicle
import com.vehicles.ktor.model.vehicleStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.vehiclesRouting() {
    route("/vehicles"){
        get {
           if(vehicleStorage.isEmpty()){
               call.respondText (
                   "get vehicles",
                   status = HttpStatusCode.OK
               )
           } else {
               call.respond(vehicleStorage);
           }
        }
    }
}

fun Route.vehicleRouting() {
    route("/vehicle") {
        get{
            call.respondText("get vehicles", status = HttpStatusCode.BadRequest)
        }
        get("{id}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            val vehicle = vehicleStorage.find {it.id == id.toInt()} ?: return@get call.respondText(
                "No vehicle available",
                status = HttpStatusCode.NotFound
            )
            call.respond(vehicle);
        }
        post{
            val vehicle = call.receive<CarVehicle>()
            vehicleStorage.add(vehicle)
            call.respondText("Vehicle stored, '$vehicle'", status = HttpStatusCode.Accepted)
        }
        delete("{id}") {
            val id  = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if(vehicleStorage.removeIf {it.id == id.toInt()}) {
                call.respondText("Vehicle has been removed, id=$id", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not found, id=$id", status = HttpStatusCode.NotFound)
            }
        }
    }
}

fun Application.registerVehiclesRouting(){
    routing {
        vehicleRouting()
        vehiclesRouting()
    }
}