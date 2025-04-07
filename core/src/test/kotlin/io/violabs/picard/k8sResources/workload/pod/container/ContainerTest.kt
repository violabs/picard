package io.violabs.picard.k8sResources.workload.pod.container

import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import org.junit.jupiter.api.Test

class ContainerTest : UnitSim() {

    @Test
    fun `build minimum exception`() = test<Unit> {
        val builder = Container.Builder()

        wheneverThrows<IllegalArgumentException> {
            whenFn = { builder.build() }
            result = {
                assertOrLog(
                    it.message,
                    "Container name must not be null"
                )
            }
        }
    }

    @Test
    fun `build minimum happy path`() = test {
        expect {
            Container(name = "test")
        }

        whenever {

        }
    }
}