package io.violabs.picard.domain.k8sResources.workload.pod


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.workload.NodeSelectorTerm
import io.violabs.picard.domain.k8sResources.workload.pod.affinity.*
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

private val NODE_AFFINITY = NodeAffinity(
    preferredDuringSchedulingIgnoredDuringExecution = listOf(
        NodeAffinityPreferredSchedulingTerm(
            weight = 1,
            preference = NodeSelectorTerm(
                matchExpression = listOf(
                    NodeSelectorRequirement(
                        key = "first",
                        operator = "In",
                        values = listOf("value1")
                    )
                ),
                matchFields = listOf(
                    NodeSelectorRequirement(
                        key = "second",
                        operator = "NotIn",
                        values = listOf("value2")
                    )
                )
            )
        )
    )
)

private val LABEL_SELECTOR = LabelSelector(
    matchExpressions = listOf(
        LabelSelectorRequirement(
            key = "label",
            operator = "In",
            values = listOf("value4")
        )
    ),
    matchLabels = listOf(
        Label("test", "label")
    )
)

private val POD_AFFINITY_TERM = PodAffinityTerm(
    topologyKey = "top",
    labelSelector = LABEL_SELECTOR,
    matchLabelKeys = listOf("key1", "key2"),
    mismatchLabelKeys = listOf("key3", "key4"),
    namespaceSelector = LABEL_SELECTOR,
    namespaces = listOf("namespace1")
)

private val WEIGHTED_POD_AFFINITY_TERM = WeightedPodAffinityTerm(
    weight = 1,
    podAffinityTerm = POD_AFFINITY_TERM
)

private val POD_AFFINITY = PodAffinity(
    preferredDuringSchedulingIgnoredDuringExecution = listOf(WEIGHTED_POD_AFFINITY_TERM),
    requiredDuringSchedulingIgnoredDuringExecution = listOf(POD_AFFINITY_TERM)
)

private val POD_ANTI_AFFINITY = PodAntiAffinity(
    preferredDuringSchedulingIgnoredDuringExecution = listOf(WEIGHTED_POD_AFFINITY_TERM)
)

private val AFFINITY = Affinity(
    nodeAffinity = NODE_AFFINITY,
    podAffinity = POD_AFFINITY,
    podAntiAffinity = POD_ANTI_AFFINITY
)

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
    nodeName = "test_node_name",
    affinity = AFFINITY,

    //
    //
    schedulerName = "test_scheduler_name",
    runtimeClassName = "test_runtime_class_name",
    priorityClassName = "test_priority_class_name",
    priority = 1,
    preemptionPolicy = "test_preemption_policy",
    //
    //
    restartPolicy = RestartPolicy.Always,
    terminationGracePeriodSeconds = 1,
    activeDeadlineSeconds = 1,
    //
    hostname = "test_hostname",
    //
    subdomain = "test_subdomain",
    //
    //
    //
    //
    //
    //
    serviceAccountName = "test_service_account_name",
    //
    //
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

            affinity {
                nodeAffinity {
                    preferredDuringSchedulingIgnoredDuringExecution {
                        term {
                            weight = 1
                            preference {
                                matchExpressions {
                                    requirement {
                                        key = "first"
                                        operator = "In"
                                        values("value1")
                                    }
                                }
                                matchFields {
                                    requirement {
                                        key = "second"
                                        operator = "NotIn"
                                        values("value2")
                                    }
                                }
                            }
                        }
                    }
                }

                podAffinity {
                    preferredDuringSchedulingIgnoredDuringExecution {
                        term {
                            weight = 1
                            podAffinityTerm { testShared() }
                        }
                    }

                    requiredDuringSchedulingIgnoredDuringExecution {
                        term { testShared() }
                    }
                }

                podAntiAffinity {
                    preferredDuringSchedulingIgnoredDuringExecution {
                        term {
                            weight = 1
                            podAffinityTerm { testShared() }
                        }
                    }
                }
            }
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

fun PodAffinityTerm.Builder.testShared() {
    topologyKey = "top"
    labelSelector {
        matchExpressions {
            requirement {
                key = "label"
                operator = "In"
                values("value4")
            }
        }

        matchLabels {
            label("test", "label")
        }
    }
    matchLabelKeys("key1", "key2")
    mismatchLabelKeys("key3", "key4")
    namespaceSelector {
        matchExpressions {
            requirement {
                key = "label"
                operator = "In"
                values("value4")
            }
        }

        matchLabels {
            label("test", "label")
        }
    }
    namespaces("namespace1")
}

private val FAILURE_POSSIBILITIES = possibilities<Pod.Spec, Pod.Spec.Builder> {
    requireNotEmptyScenario("containers") {
        given(Pod.Spec.Builder())
    }
}