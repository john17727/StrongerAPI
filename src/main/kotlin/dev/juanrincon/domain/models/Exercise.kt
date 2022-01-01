package dev.juanrincon.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    var id: Int = -1,
    val title: String,
    val imageUrl: String,
    val videoUrl: String,
    val instructions: List<Instruction>,
    val Muscle: Muscle,
    val category: Category
)
