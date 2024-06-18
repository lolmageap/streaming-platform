package cherhy.example.plugins.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import cherhy.example.plugins.util.ApplicationConfigUtils.getDataSource
import cherhy.example.plugins.util.property.DataSourceProperty.DRIVER_CLASS_NAME
import cherhy.example.plugins.util.property.DataSourceProperty.ISOLATION_LEVEL
import cherhy.example.plugins.util.property.DataSourceProperty.MAX_POOL_SIZE
import cherhy.example.plugins.util.property.DataSourceProperty.PASSWORD
import cherhy.example.plugins.util.property.DataSourceProperty.USERNAME
import cherhy.example.plugins.util.property.DataSourceProperty.URL

fun Application.configureDatabase() {
    val hikari = HikariDataSource(
        HikariConfig().apply {
            jdbcUrl = getDataSource(URL)
            username = getDataSource(USERNAME)
            password = getDataSource(PASSWORD)
            driverClassName = getDataSource(DRIVER_CLASS_NAME)
            maximumPoolSize = getDataSource(MAX_POOL_SIZE).toInt()
            isAutoCommit = false
            transactionIsolation = getDataSource(ISOLATION_LEVEL)
            validate()
        }
    )
    Database.connect(hikari)
}