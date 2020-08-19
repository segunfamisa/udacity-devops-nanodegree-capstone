package com.segunfamisa

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    routing {
        get("/") {
            val jsonResponse = """
                    {
                        "message": "Hello world. Welcome to Segun Famisa's server!"
                    }
                """.trimIndent()
            call.respondText(jsonResponse, ContentType.Application.Json)
        }
    }
}
