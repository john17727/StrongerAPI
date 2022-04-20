package dev.juanrincon.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.juanrincon.domain.daos.*
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureExposed() {
//    val config = environment.config
//    val databasePath = "ktor.database."
//    val type = config.property(databasePath + "type").getString()
//    val host = config.property(databasePath + "host").getString()
//    val port = config.property(databasePath + "port").getString()
//    val db = config.property(databasePath + "db").getString()
//    val driver = config.property(databasePath + "driver").getString()
//    val user = config.property(databasePath + "user").getString()
//    val password = config.property(databasePath + "password").getString()
//
//    val url = "jdbc:$type://$host:$port/$db"
//
//    Database.connect(url, driver = driver, user = user, password = password)
//

    Database.connect(hikari())
    transaction {
        SchemaUtils.create(Categories, Muscles, Instructions, Exercises)
    }
}

private fun hikari(): HikariDataSource {
    val config = HikariConfig()
    config.driverClassName = System.getenv("JDBC_DRIVER")
    config.jdbcUrl = System.getenv("JDBC_DATABASE_URL")
    config.maximumPoolSize = 3
    config.isAutoCommit = false
    config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    val user = System.getenv("DB_USER")
    if (user != null) {
        config.username = user
    }
    val password = System.getenv("DB_PASSWORD")
    if (password != null) {
        config.password = password
    }
    config.validate()
    return HikariDataSource(config)
}

suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }