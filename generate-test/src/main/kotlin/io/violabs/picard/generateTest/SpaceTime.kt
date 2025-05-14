package io.violabs.picard.generateTest

import io.violabs.picard.dsl.annotation.GenerateDSL
import java.time.LocalDateTime

@GenerateDSL
data class SpaceTime(
    val space: Space? = null,
    val time: LocalDateTime? = null
)