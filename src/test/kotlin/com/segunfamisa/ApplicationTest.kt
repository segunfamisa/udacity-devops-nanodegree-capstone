package com.segunfamisa

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `get request to root should return json object with message`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())

                val welcomeMessage = Json.decodeFromString(WelcomeMessage.serializer(), response.content!!)
                assertEquals(
                    "Hello world. Welcome to Segun Famisa's server!",
                    welcomeMessage.message
                )
            }
        }
    }
}

@Serializable
data class WelcomeMessage(val message: String)
