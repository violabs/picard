package io.violabs.picard.generateTest

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class StarShip(
    val name: String? = null,
    val activated: Boolean? = null,
    val docked: Boolean? = null,
    val capacity: Int? = null,
    val coordinates: SpaceTime? = null,
    val stardate: Stardate? = null,
    val commanderNames: List<String>? = null,
    val passengers: List<Passenger>? = null,
    val areaCodes: Map<String, String>? = null,
    val roomMap: Map<String, Passenger>? = null
)