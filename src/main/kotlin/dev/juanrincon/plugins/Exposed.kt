package dev.juanrincon.plugins

import io.ktor.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureExposed() {
    val config = environment.config
    val databasePath = "ktor.database."
    val type = config.property(databasePath + "type").getString()
    val host = config.property(databasePath + "host").getString()
    val port = config.property(databasePath + "port").getString()
    val db = config.property(databasePath + "db").getString()
    val driver = config.property(databasePath + "driver").getString()
    val user = config.property(databasePath + "user").getString()
    val password = config.property(databasePath + "password").getString()

    val url = "jdbc:$type://$host:$port/$db"

    Database.connect(url, driver = driver, user = user, password = password)
}