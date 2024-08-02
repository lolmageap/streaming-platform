package cherhy.example.plugins

import cherhy.example.util.ApplicationConfigUtils.getDataSource
import cherhy.example.util.property.DataSourceProperty.DRIVER_CLASS_NAME
import cherhy.example.util.property.DataSourceProperty.ISOLATION_LEVEL
import cherhy.example.util.property.DataSourceProperty.MAX_POOL_SIZE
import cherhy.example.util.property.DataSourceProperty.PASSWORD
import cherhy.example.util.property.DataSourceProperty.URL
import cherhy.example.util.property.DataSourceProperty.USERNAME
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers.IO
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

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

suspend fun <T> reactiveTransaction(
    block: suspend () -> T,
) = newSuspendedTransaction(IO) { block() }