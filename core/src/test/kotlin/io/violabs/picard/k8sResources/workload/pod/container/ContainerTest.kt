package io.violabs.picard.k8sResources.workload.pod.container

import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.ImagePullPolicy
import io.violabs.picard.domain.ObjectFieldSelector
import io.violabs.picard.domain.ResourceFieldSelector
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapEnvSource
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapKeySelector
import io.violabs.picard.domain.k8sResources.config.secret.SecretEnvSource
import io.violabs.picard.domain.k8sResources.config.secret.SecretKeySelector
import io.violabs.picard.domain.k8sResources.workload.pod.container.*
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeDevice
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class ContainerTest : UnitSim() {

    @Test
    fun `build minimum exception`() = test<Unit> {
        given {
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
    }

    @Test
    fun `build minimum happy path`() = test {
        given {
            val builder = Container.Builder().apply {
                name = "test"
            }

            expect { Container(name = "test") }

            whenever { builder.build() }
        }
    }

    @TestTemplate
    fun `build varied happy path - #scenario`(builder: Container.Builder, container: Container) = test {
        given {
            expect { container }

            whenever { builder.build() }
        }
    }

    companion object {
        private val SIMULATION_GROUP = SimulationGroup.vars("scenario", "builder", "container")

        @JvmStatic
        @BeforeAll
        fun setup() {
            SUCCESS_POSSIBILITIES.scenarios.forEach {
                SIMULATION_GROUP.with(it.id, it.given, it.expected)
            }

            setup<ContainerTest>(
                SIMULATION_GROUP to { this::`build varied happy path - #scenario` }
            )
        }
    }
}

private val SUCCESS_POSSIBILITIES = possibilities<Container, Container.Builder> {
    val containerName = "test"

    scenario {
        id = "full"
        description = "single level of optionals. if a child optional has required, it is skipped."
        given(Container.Builder()) {
            name = containerName
            image = "test/image:latest"
            imagePullPolicy = ImagePullPolicy.Always
            command("echo", "hello")
            args("hello", "universe")
            workingDir = "/home/test"
            ports {
                port {
                    name = "http"
                    protocol = Protocol.TCP
                    containerPort = 8080
                }
            }
            env {
                add {
                    name = "ENV_VAR_1"
                    value = "value1"
                    valueFrom {
                        configMapKeyRef {
                            name = "config-map-key"
                            key = "key"
                            optional()
                        }

                        fieldRef {
                            fieldPath = "spec.nodeName"
                            apiVersion = KAPIVersion.V1
                        }

                        secretKeyRef {
                            name = "secret-name"
                            key = "key"
                            optional()
                        }

                        resourceFieldRef {
                            resource = "limits.cpu"
                            this.containerName = "container-name"
                            divisor("10")
                        }
                    }
                }
            }
            envFrom {
                add {
                    configMapRef {
                        name = "config-map-name"
                        optional()
                    }

                    prefix = "prefix"

                    secretRef {
                        name = "secret-name"
                        optional()
                    }
                }
            }
            volumeMounts {
                volumeMount {
                    name = "test-volume"
                    mountPath = "/test-volume"
                    mountPropagation = "None"
                    readOnly()
                    recursiveReadOnly = "Disabled"
                    subPath = "sub-path"
                    subPathExpr = "sub-path-expr"
                }
            }
            volumeDevices {
                volumeDevice {
                    name = "test-volume"
                    devicePath = "/dev/xvda"
                }
            }
            resources {
                claims {
                    claim {
                        name = "claim-name"
                        request = "100m"
                    }
                }

                limits {
                    put("cpu", Quantity("100m"))
                    put("memory", Quantity("100Mi"))
                }

                requests {
                    put("cpu", Quantity("100m"))
                    put("memory", Quantity("100Mi"))
                }

                resizePolicy {
                    add {
                        resourceName = "cpu"
                        restartPolicy = "Always"
                    }
                }
            }
        }
        expected = Container(
            name = containerName,
            image = "test/image:latest",
            imagePullPolicy = ImagePullPolicy.Always,
            command = listOf("echo", "hello"),
            args = listOf("hello", "universe"),
            workingDir = "/home/test",
            ports = listOf(
                ContainerPort(
                    name = "http",
                    protocol = Protocol.TCP,
                    containerPort = 8080
                )
            ),
            env = listOf(
                EnvVar(
                    name = "ENV_VAR_1",
                    value = "value1",
                    valueFrom = EnvVarSource(
                        configMapKeyRef = ConfigMapKeySelector(
                            key = "key",
                            name = "config-map-key",
                            optional = true
                        ),
                        fieldRef = ObjectFieldSelector(
                            fieldPath = "spec.nodeName",
                            apiVersion = KAPIVersion.V1
                        ),
                        secretKeyRef = SecretKeySelector(
                            name = "secret-name",
                            key = "key",
                            optional = true
                        ),
                        resourceFieldRef = ResourceFieldSelector(
                            resource = "limits.cpu",
                            containerName = "container-name",
                            divisor = Quantity("10")
                        )
                    )
                )
            ),
            envFrom = listOf(
                EnvFromSource(
                    configMapRef = ConfigMapEnvSource(
                        name = "config-map-name",
                        optional = true
                    ),
                    prefix = "prefix",
                    secretRef = SecretEnvSource(
                        name = "secret-name",
                        optional = true
                    )
                )
            ),
            volumeMounts = listOf(
                VolumeMount(
                    name = "test-volume",
                    mountPath = "/test-volume",
                    mountPropagation = "None",
                    readOnly = true,
                    recursiveReadOnly = "Disabled",
                    subPath = "sub-path",
                    subPathExpr = "sub-path-expr"
                )
            ),
            volumeDevices = listOf(
                VolumeDevice(
                    name = "test-volume",
                    devicePath = "/dev/xvda"
                )
            ),
            resources = ContainerResourceRequirements(
                claims = listOf(
                    ContainerResourceClaim(
                        name = "claim-name",
                        request = "100m"
                    )
                ),
                limits = mapOf(
                    "cpu" to Quantity("100m"),
                    "memory" to Quantity("100Mi")
                ),
                requests = mapOf(
                    "cpu" to Quantity("100m"),
                    "memory" to Quantity("100Mi")
                ),
                resizePolicy = listOf(
                    ResizePolicy(
                        resourceName = "cpu",
                        restartPolicy = "Always"
                    )
                )
            )
        )
    }

    scenario {
        id = "ports minimum happy path"
        given(Container.Builder()) {
            name = containerName
        }
        expected = Container(
            name = containerName
        )
    }

    scenario {
        id = "env var minimum happy path"
        given(Container.Builder()) {
            name = containerName
        }
        expected = Container(
            name = containerName
        )
    }

//    scenario {
//        id = "env var full happy path"
//        given(Container.Builder()) {
//            name = containerName
//        }
//        expected = Container(
//            name = containerName
//        )
//    }
}

private val EXCEPTION_POSSIBILITIES = possibilities<Container, Container.Builder> {
    scenario {
        id = "ports exception"
    }
}