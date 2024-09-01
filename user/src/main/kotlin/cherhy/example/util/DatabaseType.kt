package cherhy.example.util

import org.jetbrains.exposed.sql.Database

object DatabaseType {
    lateinit var masterDatabase: Database
    lateinit var slaveDatabase: Database
}