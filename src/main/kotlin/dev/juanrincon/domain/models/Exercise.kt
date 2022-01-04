package dev.juanrincon.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    var id: Int?,
    val name: String,
    val imageUrl: String?,
    val videoUrl: String?,
    val instructions: List<Instruction>?,
    val muscle: String,
    val category: String
)
