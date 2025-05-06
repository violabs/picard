package io.violabs.picard.serialization

import com.fasterxml.jackson.databind.ObjectMapper
import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.k8sResources.workload.pod.Pod
import org.junit.jupiter.api.Test

class KAPIVersionJsonSerializerTest : UnitSim() {
    private val om = ObjectMapper()

    @Test
    fun `version serializes correctly`() = test {
        given {
            expect { """
                {"apiVersion":"v1","kind":"Pod","metadata":null,"spec":null,"status":null}
            """.trimIndent() }

            whenever {
                om.writeValueAsString(Pod())
            }
        }
    }
}