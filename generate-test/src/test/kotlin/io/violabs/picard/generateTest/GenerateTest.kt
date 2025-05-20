package io.violabs.picard.generateTest

import io.violabs.geordi.UnitSim
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class GenerateTest : UnitSim() {

    @Test
    fun `generates a test class`() = test {
        given {
            val now = LocalDateTime.now()
            expect {
                StarShip(
                    name = "USS Enterprise NCC-1701-D",
                    activated = true,
                    docked = false,
                    capacity = 200,
                    coordinates = SpaceTime(
                        space = Space(x = 100f, y = 100f, z = 100f),
                        time = now
                    ),
                    stardate = Stardate("10000.1"),
                    commanderNames = listOf(
                        "Captain Jean-Luc Picard",
                        "Commander William T. Riker"
                    ),
                    // map based primitives (vararg)
                    // map based primitives (add)
                    // map based complex (add w/ builder)
                    passengers = listOf(
                        Passenger(name = "Beverly Crusher", rank = Passenger.Rank.COMMANDER),
                        Passenger(name = "Worf", rank = Passenger.Rank.LIEUTENANT_COMMANDER)
                    ),
                    areaCodes = mapOf(
                        "A1" to "Artax 1",
                        "VLS" to "Violet Lunar Station"
                    ),
                    roomMap = mapOf(
                        "1" to Passenger(name = "Jean-Luc Picard", rank = Passenger.Rank.CAPTAIN),
                        "2" to Passenger(
                            name = "William T. Riker",
                            rank = Passenger.Rank.COMMANDER
                        )
                    )
                )
            }

            whenever {
                makeCall {
                    name = "USS Enterprise NCC-1701-D"
                    activated()
                    docked(false)
                    capacity = 200
                    coordinates {
                        space { x = 100f; y = 100f; z = 100f }
                        time = now
                    }
                    stardate("10000.1")
                    commanderNames(
                        "Captain Jean-Luc Picard",
                        "Commander William T. Riker"
                    )
                    passengers {
                        passenger {
                            name = "Beverly Crusher"
                            rank = Passenger.Rank.COMMANDER
                        }

                        passenger {
                            name = "Worf"
                            rank = Passenger.Rank.LIEUTENANT_COMMANDER
                        }
                    }
                    areaCodes(
                        "A1" to "Artax 1",
                        "VLS" to "Violet Lunar Station"
                    )
                    roomMap {
                        passenger("1") {
                            name = "Jean-Luc Picard"
                            rank = Passenger.Rank.CAPTAIN
                        }

                        passenger("2") {
                            name = "William T. Riker"
                            rank = Passenger.Rank.COMMANDER
                        }
                    }
                }
            }
        }
    }

    fun makeCall(block: StarShipBuilder.() -> Unit): StarShip {
        return StarShipBuilder().apply(block).build()
    }
}