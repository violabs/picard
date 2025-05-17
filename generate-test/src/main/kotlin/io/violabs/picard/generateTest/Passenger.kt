package io.violabs.picard.generateTest

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.dsl.annotation.GeneratedGroupDSL

/**
 * todo: make it flexible enough to only need the [GeneratedGroupDSL]
 * or add a property to generate DSL.
 */
@GeneratedDSL
@GeneratedGroupDSL
data class Passenger(val name: String, val rank: Rank) {

    enum class Rank {
        CAPTAIN,
        CREWMEMBER,
        COMMANDER,
        LIEUTENANT_COMMANDER,
        FIRST_OFFICER,
        SECOND_OFFICER,
        CIVILIAN
    }
}