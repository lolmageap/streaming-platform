package cherhy.com.util

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.reflect.*

val httpClient
    get() = HttpClient(CIO)

@OptIn(InternalAPI::class)
suspend inline fun <reified RESPONSE_TYPE> HttpClient.call(
    url: String,
    httpMethod: HttpMethod,
    requestHeader: Map<String, String>? = null,
    requestBody: Any? = null,
) =
    this.use {
        this.request(url) {
            method = httpMethod
            requestHeader?.forEach { (key, value) ->
                this.headers {
                    append(key, value)
                }
            }
            if (httpMethod == HttpMethod.Post || httpMethod == HttpMethod.Put) {
                if (requestBody != null) {
                    this.body = requestBody
                    this.bodyType = typeInfo<LinkedHashMap<String, Any>>()
                    this.headers {
                        append(HttpHeaders.ContentType, ContentType.Application.Json)
                    }
                }
            }
        }
    }.body<RESPONSE_TYPE>()