package com.vehicles.ktor.routes

import com.vehicles.ktor.module
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class VehicleRoutesKtTest {

    @JvmField
    val CONTENT_TYPE: String = "application/json"

    @Test
    fun vehiclesRoutingGet() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/vehicles").apply {
                assertEquals(
                    """[{"id":1,"desc":"car-1","maxSpeed":210},{"id":2,"desc":"car-2","maxSpeed":220},{"id":3,"desc":"car-3","maxSpeed":230}]""",
                    response.content
                )
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun vehicleRoutingPost() {
        val testId = 4
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/vehicle"){
                addHeader("Content-Type", CONTENT_TYPE)
                setBody("""{"id":$testId, "desc": "car-$testId", "maxSpeed": 220}""")
            }.apply {
                assertEquals("""Vehicle stored, 'CarVehicle(id=$testId, desc=car-$testId, maxSpeed=220)'""", response.content)
                assertEquals(HttpStatusCode.Accepted, response.status())
            }
        }
    }

    @Test
    fun vehicleRoutingGet(){
        val testId = 1
        withTestApplication({module(testing = true)}){
            handleRequest(HttpMethod.Get, "/vehicle/$testId") {
                addHeader("Content-Type", CONTENT_TYPE)
            }.apply {
                assertEquals("""{"id":$testId,"desc":"car-$testId","maxSpeed":210}""", response.content)
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun vehicleRoutingDelete(){
        val testId = 1
        withTestApplication({module(testing = true)}){
            handleRequest(HttpMethod.Delete, "/vehicle/$testId") {
                addHeader("Content-Type", CONTENT_TYPE)
            }.apply {
                assertEquals("""Vehicle has been removed, id=$testId""", response.content)
                assertEquals(HttpStatusCode.Accepted, response.status())
            }
        }
    }

    @Test
    fun registerVehiclesRouting() {
    }
}