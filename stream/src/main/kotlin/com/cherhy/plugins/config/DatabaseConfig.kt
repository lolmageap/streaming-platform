package com.cherhy.plugins.config

import com.cherhy.plugins.util.ApplicationConfigUtils.getDataSource
import com.cherhy.plugins.util.property.DataSourceProperty.DRIVER_CLASS_NAME
import com.cherhy.plugins.util.property.DataSourceProperty.MAX_POOL_SIZE
import com.cherhy.plugins.util.property.DataSourceProperty.PASSWORD
import com.cherhy.plugins.util.property.DataSourceProperty.URL
import com.cherhy.plugins.util.property.DataSourceProperty.USERNAME
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.ktorm.database.Database

val database = Database.connect(
    HikariDataSource(
        HikariConfig().apply {
            driverClassName = getDataSource(URL)
            jdbcUrl = getDataSource(USERNAME)
            username = getDataSource(PASSWORD)
            password = getDataSource(DRIVER_CLASS_NAME)
            maximumPoolSize = getDataSource(MAX_POOL_SIZE).toInt()
        }
    )
)