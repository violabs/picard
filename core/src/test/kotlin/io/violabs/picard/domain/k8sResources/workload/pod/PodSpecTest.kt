package io.violabs.picard.domain.k8sResources.workload.pod


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.LocalObjectReference
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.Volume
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import io.violabs.picard.domain.k8sResources.workload.pod.container.EphemeralContainer
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodSpecTest : FullBuildSim<Pod.Spec, Pod.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodSpecTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )
    }
}

private val STANDARD_CONTAINER = Container("standard")

/**
 * Containers are tested separately.
 */
private val SPEC = Pod.Spec(
    containers = listOf(STANDARD_CONTAINER),
    initContainers = listOf(Container("init")),
    ephemeralContainers = listOf(EphemeralContainer("ephemeral")),
    imagePullSecrets = listOf(LocalObjectReference("image-pull-secret")),
    enableServiceLinks = true,
    os = PodOS("linux"),
    volumes = listOf(
        Volume(
            "test_volume",
            "/test_empty_dir",
        )
    ),
    nodeSelector = mapOf("nodeSelectorKey" to "nodeSelectorValue"),
)

private val SUCCESS_POSSIBILITIES = possibilities<Pod.Spec, Pod.Spec.Builder> {
    scenario {
        id = "minimum"
        given(Pod.Spec.Builder()) {
            containers {
                container {
                    name = "standard"
                }
            }
        }
        expected = Pod.Spec(
            containers = listOf(STANDARD_CONTAINER)
        )
    }

    scenario {
        id = "full"
        given(Pod.Spec.Builder()) {
            containers {
                container {
                    name = "standard"
                }
            }

            initContainers {
                container {
                    name = "init"
                }
            }

            ephemeralContainers {
                container {
                    name = "ephemeral"
                }
            }

            imagePullSecrets {
                reference("image-pull-secret")
            }

            enableServiceLinks()

            os("linux")

            volumes {
                volume {
                    name = "test_volume"
                    emptyDir = "/test_empty_dir"
                }
            }

            nodeSelector {
                put("nodeSelectorKey", "nodeSelectorValue")
            }

            nodeName = "test_node_name"
//
//
            schedulerName = "test_scheduler_name"
            runtimeClassName = "test_runtime_class_name"
            priorityClassName = "test_priority_class_name"
            priority = 1
            preemptionPolicy = "test_preemption_policy"
//
//
            restartPolicy = RestartPolicy.Always
            terminationGracePeriodSeconds = 1L
            activeDeadlineSeconds = 1L
//
            hostname = "test_hostname"
//
            subdomain = "test_subdomain"
//
//
//
//
//
//
            serviceAccountName = "test_service_account_name"
//
//

        }
        expected = SPEC
    }

    scenario {
        idForFalseBooleanValues()
        given(Pod.Spec.Builder()) {
            containers {
                container {
                    name = "standard"
                }
            }
            enableServiceLinks = false
        }
        expected = Pod.Spec(
            containers = listOf(STANDARD_CONTAINER),
            enableServiceLinks = false
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<Pod.Spec, Pod.Spec.Builder> {
    requireNotEmptyScenario("containers") {
        given(Pod.Spec.Builder())
    }
}