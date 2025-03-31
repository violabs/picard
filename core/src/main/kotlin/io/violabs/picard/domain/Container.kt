package io.violabs.picard.domain

data class Container(
    val name: String,
    val image: String,
    val command: List<String>? = null,
    val ports: List<Port>? = null
) {

    data class Port(
        val containerPort: Int
    )
}