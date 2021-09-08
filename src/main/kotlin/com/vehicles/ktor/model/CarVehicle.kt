package com.vehicles.ktor.model

import kotlinx.serialization.Serializable


@Serializable
data class CarVehicle(val id: Int, val desc: String, val maxSpeed: Int): Vehicle {
    override fun description(): String {
        print("CarVehicle, desc:'$desc'")
        return desc
    }

    override fun maxSpeed(): Int {
        print("CarVehicle, maxSpeed:'$maxSpeed'")
        return maxSpeed
    }
}

val vehicleStorage = mutableListOf(
    CarVehicle(1, "car-1", 210),
    CarVehicle(2, "car-2", 220),
    CarVehicle(3, "car-3", 230)
)