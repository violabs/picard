package io.violabs.picard.generateTest

import io.violabs.picard.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

@GeneratedDsl
data class SpaceTime(
    val space: Space? = null,
    val time: LocalDateTime? = null
)