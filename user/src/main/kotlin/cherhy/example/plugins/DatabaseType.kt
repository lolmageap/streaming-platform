package cherhy.example.plugins

import cherhy.example.util.ApplicationConfigUtils.getDataSource
import cherhy.example.util.DataSourceType.MASTER
import cherhy.example.util.DataSourceType.SLAVE
import cherhy.example.util.DatabaseFactory
import cherhy.example.util.property.DataSourceProperty.DRIVER_CLASS_NAME
import cherhy.example.util.property.DataSourceProperty.ISOLATION_LEVEL
import cherhy.example.util.property.DataSourceProperty.MAX_POOL_SIZE
import cherhy.example.util.property.DataSourceProperty.PASSWORD
import cherhy.example.util.property.DataSourceProperty.URL
import cherhy.example.util.property.DataSourceProperty.USERNAME
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase() {
    val masterDatabase = HikariDataSource(
        HikariConfig().apply {
            jdbcUrl = getDataSource(MASTER, URL)
            username = getDataSource(MASTER, USERNAME)
            password = getDataSource(MASTER, PASSWORD)
            driverClassName = getDataSource(MASTER, DRIVER_CLASS_NAME)
            maximumPoolSize = getDataSource(MASTER, MAX_POOL_SIZE).toInt()
            isAutoCommit = false
            transactionIsolation = getDataSource(MASTER, ISOLATION_LEVEL)
            isReadOnly = false
            validate()
        }
    )

    val slaveDatabase = HikariDataSource(
        HikariConfig().apply {
            jdbcUrl = getDataSource(SLAVE, URL)
            username = getDataSource(SLAVE, USERNAME)
            password = getDataSource(SLAVE, PASSWORD)
            driverClassName = getDataSource(SLAVE, DRIVER_CLASS_NAME)
            maximumPoolSize = getDataSource(SLAVE, MAX_POOL_SIZE).toInt()
            isAutoCommit = false
            transactionIsolation = getDataSource(SLAVE, ISOLATION_LEVEL)
            isReadOnly = true
            validate()
        }
    )

    DatabaseFactory.masterDatabase = Database.connect(masterDatabase)
    DatabaseFactory.slaveDatabase = Database.connect(slaveDatabase)

    environment.monitor.subscribe(ApplicationStopped) {
        masterDatabase.close()
        slaveDatabase.close()
    }
}