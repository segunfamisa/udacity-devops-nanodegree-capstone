package com.segunfamisa

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    install(CallLogging)

    val env = if (System.getenv("BUILD_NUMBER").isNullOrEmpty()) {
        "local"
    } else {
        System.getenv("BUILD_NUMBER")
    }
    routing {
        get("/") {
            val jsonResponse =
                """
                    {
                        "message": "Hello world! Welcome to Segun Famisa's ktor server :)",
                        "version": $env
                    }
                """.trimIndent()
            call.respondText(jsonResponse, ContentType.Application.Json)
        }

        get("/greet") {
            val name = call.request.queryParameters["name"]
            if (name.isNullOrEmpty()) {
                call.respondText(
                    """
                    {
                        "error": "Umm you have to put your name as a GET request param with key name :D",
                        "version": $env
                    }
                    """.trimIndent(),
                    ContentType.Application.Json,
                    HttpStatusCode.BadRequest
                )
            } else {
                call.respondText(
                    """
                    {
                        "message": "Hey you $name, welcome to segunfamisa's ktor server!",
                        "version": $env
                    }
                    """.trimIndent(),
                    ContentType.Application.Json
                )
            }
        }
    }
}
