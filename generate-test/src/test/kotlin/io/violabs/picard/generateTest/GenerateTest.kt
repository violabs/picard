package io.violabs.picard.generateTest

import io.violabs.geordi.UnitSim
import org.junit.jupiter.api.Test

class GenerateTest : UnitSim() {

    @Test
    fun `generates a test class`() = test {
        given {
            expect {
                StarShip(
                    name = "Enterprise",
                    activated = true
                )
            }

            whenever {
                makeCall {
                    name = "Enterprise"
                    activated()
                }
            }
        }
    }

    fun makeCall(block: StarShipBuilder.() -> Unit): StarShip {
        return StarShipBuilder().apply(block).build()
    }
}