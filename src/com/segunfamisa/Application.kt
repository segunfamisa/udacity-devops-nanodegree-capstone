package com.segunfamisa

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.netty.*

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
