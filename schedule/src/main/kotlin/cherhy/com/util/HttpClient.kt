package cherhy.com.util

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

val httpClient
    get() = HttpClient(CIO)

@OptIn(InternalAPI::class)
suspend inline fun <reified T : Any> HttpClient.call(
    url: String,
    httpMethod: HttpMethod,
    body: Any? = null,
): T =
    this.use {
        this.get(url) {
            method = httpMethod

            if (httpMethod == HttpMethod.Post || httpMethod == HttpMethod.Put) {
                contentType(ContentType.Application.Json)
                if (body != null) this.body = body
            }
        }
    }.body<T>()