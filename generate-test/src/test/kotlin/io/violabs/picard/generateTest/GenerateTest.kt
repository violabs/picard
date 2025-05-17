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
                    // group (list bldr)
                    passengers = listOf(
                        Passenger(name = "Beverly Crusher", rank = Passenger.Rank.COMMANDER),
                        Passenger(name = "Worf", rank = Passenger.Rank.LIEUTENANT_COMMANDER)
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
                }
            }
        }
    }

    fun makeCall(block: StarShipBuilder.() -> Unit): StarShip {
        return StarShipBuilder().apply(block).build()
    }
}