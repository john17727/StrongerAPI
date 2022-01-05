package dev.juanrincon.plugins

import dev.juanrincon.domain.daos.*
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

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

    transaction {
        SchemaUtils.create(Categories, Muscles, Instructions, Exercises)
    }
}