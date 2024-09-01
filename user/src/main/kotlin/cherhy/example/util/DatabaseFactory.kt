package cherhy.example.util

import org.jetbrains.exposed.sql.Database

object DatabaseFactory {
    lateinit var masterDatabase: Database
    lateinit var slaveDatabase: Database
}