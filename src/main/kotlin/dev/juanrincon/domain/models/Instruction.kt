package dev.juanrincon.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Instruction(
    val step: Int,
    val text: String
)
