package io.violabs.picard.generateTest

import io.violabs.picard.dsl.annotation.GeneratedDsl

@GeneratedDsl(
    withGroup = true,
    withMapGroup = "SINGLE"
)
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