package com.segunfamisa

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    install(CallLogging)

    routing{
        get("/") {
            val jsonResponse =
                """
                    {
                        "message": "Hello world. Welcome to Segun Famisa's server!"
                    }
                """.trimIndent()
            call.respondText(jsonResponse, ContentType.Application.Json)
        }
    }
}
