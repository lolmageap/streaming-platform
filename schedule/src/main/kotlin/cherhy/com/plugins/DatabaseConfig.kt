package cherhy.com.plugins

import cherhy.com.util.ApplicationConfigUtils.getDataSource
import cherhy.com.util.DataSourceProperty.URL
import cherhy.com.util.DataSourceProperty.USERNAME
import cherhy.com.util.DataSourceProperty.PASSWORD
import cherhy.com.util.DataSourceProperty.DRIVER_CLASS_NAME
import cherhy.com.util.DataSourceProperty.MAX_POOL_SIZE
import cherhy.com.util.DataSourceProperty.ISOLATION_LEVEL
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