package app.tracktion.domain.models

import java.io.Serializable

data class Split(
    val id: Int = 0,
    val name: String,
    val imageUrl: String?
) : Serializable
