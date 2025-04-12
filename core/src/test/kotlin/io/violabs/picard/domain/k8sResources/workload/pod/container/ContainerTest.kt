package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.ImagePullPolicy
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.pod.container.common.ContainerSim
import io.violabs.picard.domain.k8sResources.workload.pod.security.SecurityProfileType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerTest : ContainerSim<Container, Container.Builder>(Container.Builder()) {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            containerSetup(ContainerTest::class, SUCCESS_POSSIBILITIES)
        }

        private val FULL_CONTAINER = Container(
            name = CONTAINER_NAME,
            image = "test/image:latest",
            imagePullPolicy = ImagePullPolicy.Always,
            command = listOf("echo", "hello"),
            args = listOf("hello", "universe"),
            workingDir = "/home/test",
            ports = listOf(CONTAINER_PORT),
            env = listOf(ENV_VAR),
            envFrom = listOf(ENV_FROM_SOURCE),
            volumeMounts = listOf(VOLUME_MOUNT),
            volumeDevices = listOf(VOLUME_DEVICE),
            resources = VOLUME_RESOURCE_REQUIREMENTS,
            lifecycle = LIFECYCLE,
            terminationMessagePath = "/dev/termination-log",
            terminationMessagePolicy = "FallbackToLogsOnError",
            livenessProbe = PROBE,
            readinessProbe = PROBE,
            startupProbe = PROBE,
            restartPolicy = RestartPolicy.Always,
            securityContext = SECURITY_CONTEXT,
            stdin = true,
            stdinOnce = true,
            tty = true,
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Container, Container.Builder> {

            scenario {
                id = "minimum"
                description = "least required fields"
                given(Container.Builder()) {
                    name = CONTAINER_NAME
                }
                expected = Container(CONTAINER_NAME)
            }

            fullScenario(
                this,
                Container.Builder(),
                FULL_CONTAINER
            ) {
                ports {
                    port {
                        name = "http"
                        protocol = Protocol.TCP
                        containerPort = 8080
                        hostIp = "0.0.0.0"
                        hostPort = 8080
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

                lifecycle {
                    postStart {
                        exec {
                            command("echo", "exec")
                        }

                        httpGet {
                            port(8080)
                            host = "localhost"
                            httpHeaders {
                                header("name", "value")
                            }
                            path = "/path"
                            scheme = "HTTP"
                        }

                        sleep(10)

                        tcpSocket {
                            port(8080)
                            host = "localhost"
                        }
                    }

                    preStop {
                        exec {
                            command("echo", "exec")
                        }

                        httpGet {
                            port(8080)
                            host = "localhost"
                            httpHeaders {
                                header("name", "value")
                            }
                            path = "/path"
                            scheme = "HTTP"
                        }

                        sleep(10)

                        tcpSocket {
                            port(8080)
                            host = "localhost"
                        }
                    }
                }

                livenessProbe {
                    exec {
                        command("echo", "exec")
                    }

                    httpGet {
                        port(8080)
                        host = "localhost"
                        httpHeaders {
                            header("name", "value")
                        }
                        path = "/path"
                        scheme = "HTTP"
                    }

                    tcpSocket {
                        port(8080)
                        host = "localhost"
                    }

                    grpcAction {
                        port = 8080
                        service = "service"
                    }

                    initialDelaySeconds = 10
                    terminationGracePeriodSeconds = 10
                    periodSeconds = 10
                    timeoutSeconds = 1
                    successThreshold = 1
                    failureThreshold = 3
                }

                readinessProbe {
                    exec {
                        command("echo", "exec")
                    }

                    httpGet {
                        port(8080)
                        host = "localhost"
                        httpHeaders {
                            header("name", "value")
                        }
                        path = "/path"
                        scheme = "HTTP"
                    }

                    tcpSocket {
                        port(8080)
                        host = "localhost"
                    }

                    grpcAction {
                        port = 8080
                        service = "service"
                    }

                    initialDelaySeconds = 10
                    terminationGracePeriodSeconds = 10
                    periodSeconds = 10
                    timeoutSeconds = 1
                    successThreshold = 1
                    failureThreshold = 3
                }

                startupProbe {
                    exec {
                        command("echo", "exec")
                    }

                    httpGet {
                        port(8080)
                        host = "localhost"
                        httpHeaders {
                            header("name", "value")
                        }
                        path = "/path"
                        scheme = "HTTP"
                    }

                    tcpSocket {
                        port(8080)
                        host = "localhost"
                    }

                    grpcAction {
                        port = 8080
                        service = "service"
                    }

                    initialDelaySeconds = 10
                    terminationGracePeriodSeconds = 10
                    periodSeconds = 10
                    timeoutSeconds = 1
                    successThreshold = 1
                    failureThreshold = 3
                }

                restartPolicy = RestartPolicy.Always

                securityContext {
                    allowPrivilegeEscalation()
                    appArmorProfile {
                        type = SecurityProfileType.Localhost
                        localHostProfile = "test-profile"
                    }

                    capabilities {
                        add("NET_ADMIN")
                        drop("ALL")
                    }

                    procMount = "procMount"
                    priveleged()
                    readOnlyRootFilesystem()
                    runAsUser = 1000
                    runAsGroup = 1000
                    runAsNonRoot()

                    seLinuxOptions {
                        level = "s0"
                        role = "role"
                        type = "type"
                        user = "user"
                    }

                    seccompProfile {
                        type = SecurityProfileType.Localhost
                        localhostProfile = "test-profile"
                    }

                    windowsOptions {
                        gmsaCredentialSpec = "gmsaCredentialSpec"
                        gmsaCredentialSpecName = "gmsaCredentialSpecName"
                        runAsUserName = "runAsUserName"
                        hostProcess()
                    }
                }

                stdin()
                stdinOnce()
                tty()
            }

            scenario {
                id = "false booleans"
                description = "we can manually set the booleans to false"
                given(Container.Builder()) {
                    name = CONTAINER_NAME
                    stdin = false
                    stdinOnce = false
                    tty = false
                }
                expected = Container(
                    name = CONTAINER_NAME,
                    stdin = false,
                    stdinOnce = false,
                    tty = false
                )
            }
        }
    }
}