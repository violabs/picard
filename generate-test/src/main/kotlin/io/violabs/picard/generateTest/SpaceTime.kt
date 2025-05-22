package io.violabs.picard.generateTest

import io.violabs.picard.dsl.annotation.GeneratedDsl
import java.time.LocalDateTime

@GeneratedDsl
data class SpaceTime(
    val space: Space? = null,
    val time: LocalDateTime? = null
)