package io.violabs.picard.generateTest

import io.violabs.picard.dsl.annotation.GeneratedDSL
import java.time.LocalDateTime

@GeneratedDSL
data class SpaceTime(
    val space: Space? = null,
    val time: LocalDateTime? = null
)