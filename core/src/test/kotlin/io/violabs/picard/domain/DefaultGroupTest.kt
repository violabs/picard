package io.violabs.picard.domain

import io.violabs.geordi.UnitSim
import org.junit.jupiter.api.Test

class DefaultGroupTest : UnitSim() {

    @Test
    fun `items will return null if empty`() = test {
        given {
            expectNull()

            whenever {
                val group = object : DefaultGroup<String>() {
                    fun getItems(): List<String>? = items()
                }
                group.getItems()
            }
        }
    }
}