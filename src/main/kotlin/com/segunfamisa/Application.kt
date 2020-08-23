package com.segunfamisa

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    install(CallLogging)

    routing {
        get("/") {
            val jsonResponse =
                """
                    {
                        "message": "Hello world! Welcome to Segun Famisa's ktor server :)"
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
                        "error": "Um you have to put your name as a get request param with key name!"
                    }
                    """.trimIndent(),
                    ContentType.Application.Json,
                    HttpStatusCode.BadRequest
                )
            } else {
                call.respondText(
                    """
                    {
                        "message": "Hey you $name, welcome to Segun Famisa's ktor server!"
                    }
                    """.trimIndent(),
                    ContentType.Application.Json
                )
            }
        }
    }
}
