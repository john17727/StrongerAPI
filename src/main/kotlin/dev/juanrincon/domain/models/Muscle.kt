package dev.juanrincon.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Muscle(
    val id: Int,
    val name: String,
    val imageUrl: String?
)
