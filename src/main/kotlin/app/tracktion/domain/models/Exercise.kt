package app.tracktion.domain.models

import java.io.Serializable

data class Exercise(
    var id: Int = 0,
    val name: String,
    val imageUrl: String?,
    val videoUrl: String?,
    val instructions: List<Instruction>?,
    val muscle: Muscle,
    val category: Category
) : Serializable
