package io.violabs.picard.domain

import io.violabs.geordi.UnitSim
import org.junit.jupiter.api.Test

class TextDataTest : UnitSim() {

    @Test
    fun `constructor will throw exception if not correct characters`() = test<Unit> {
        given {
            wheneverThrows<IllegalArgumentException>(
                "Invalid key. Can only contain alphanumeric, '.', '-', '_' - key: &"
            ) {
                TextData(mutableMapOf("&" to "value"))
            }
        }
    }

    @Test
    fun `add will throw an exception if not correct characters`() = test<Unit> {
        given {
            wheneverThrows<IllegalArgumentException>(
                "Invalid key. Can only contain alphanumeric, '.', '-', '_' - key: &"
            ) {
                TextData().apply {
                    add("&", "value")
                }
            }
        }
    }

    @Test
    fun `add happy path`() = test {
        given {
            expect {
                TextData(mutableMapOf("key" to "value"))
            }

            whenever {
                TextData().apply {
                    add("key", "value")
                }
            }
        }
    }
}