package cherhy.example.plugins.util

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

object ApplicationConfigUtils {
    private fun getConfigProperty(
        path: String,
    ) =
        HoconApplicationConfig(ConfigFactory.load()).property(path).getString()

    fun getDataSource(
        key: String,
    ) = getConfigProperty("database.datasource.$key")

    fun getJwt(
        key: String,
    ) = getConfigProperty("jwt.$key")
}