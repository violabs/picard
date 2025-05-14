package io.violabs.picard.generateTest

import io.violabs.picard.dsl.annotation.GenerateDSL

@GenerateDSL
data class StarShip(
    val name: String? = null,
    val activated: Boolean? = null,
    val capacity: Int? = null,
    val coordinates: SpaceTime? = null
)