package dev.juanrincon.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class Category(
    val id: Int?,
    val name: String,
    val imageUrl: String?
)
