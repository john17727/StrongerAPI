package dev.juanrincon.plugins

import io.ktor.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureExposed() {
    Database.connect("jdbc:mysql://localhost:3306/stronger", driver = "com.mysql.cj.jdbc.Driver",
        user = "root", password = "Mustang1772714")
}