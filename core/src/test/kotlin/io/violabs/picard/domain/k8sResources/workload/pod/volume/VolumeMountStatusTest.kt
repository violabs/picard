package io.violabs.picard.domain.k8sResources.workload.pod.volume


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeMountStatusTest : FullBuildSim<VolumeMountStatus, VolumeMountStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeMountStatusTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private const val MOUNT_PATH = "test/path"

        private val SUCCESS_POSSIBILITIES = possibilities<VolumeMountStatus, VolumeMountStatusDslBuilder> {
            scenario {
                idForFalseBooleanValues()
                given(VolumeMountStatusDslBuilder()) {
                    mountPath = MOUNT_PATH
                    name = "test_name"
                    readOnly(false)
                }
                expected = VolumeMountStatus(
                    mountPath = MOUNT_PATH,
                    name = "test_name",
                    readOnly = false
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<VolumeMountStatus, VolumeMountStatusDslBuilder> {
            requireScenario("mountPath") {
                given(VolumeMountStatusDslBuilder())
            }

            requireScenario("name") {
                given(VolumeMountStatusDslBuilder()) {
                    mountPath = MOUNT_PATH
                }
            }
        }
    }
}