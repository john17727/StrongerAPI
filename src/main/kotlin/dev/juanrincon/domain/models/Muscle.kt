package dev.juanrincon.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Muscle(
    val name: String,
    val imageUrl: String
)
