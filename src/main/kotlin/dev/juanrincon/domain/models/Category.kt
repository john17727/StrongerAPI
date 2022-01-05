package dev.juanrincon.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class Category(
    val id: Int = 0,
    val name: String,
    val imageUrl: String?
)
