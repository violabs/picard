package io.violabs.picard.domain.k8sResources.workload.pod.container.common

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.TestScenarioSet
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapEnvSource
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapKeySelector
import io.violabs.picard.domain.k8sResources.config.secret.SecretEnvSource
import io.violabs.picard.domain.k8sResources.config.secret.SecretKeySelector
import io.violabs.picard.domain.k8sResources.workload.pod.container.LifecycleHandler
import io.violabs.picard.domain.k8sResources.workload.pod.container.Probe
import io.violabs.picard.domain.k8sResources.workload.pod.action.*
import io.violabs.picard.domain.k8sResources.workload.pod.container.*
import io.violabs.picard.domain.k8sResources.workload.pod.security.*
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeDevice
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount
import io.violabs.picard.verifyRequiredField
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass

abstract class ContainerSim<T : BaseContainer, B : DSLBuilder<T>>(
    private val exceptionBuilder: B
) : SuccessBuildSim<T, B>() {
    @Test
    fun `build minimum exception`() = verifyRequiredField(
        "name",
        exceptionBuilder,
    )

    companion object {
        fun <S : ContainerSim<T, B>, T : BaseContainer, B : DSLBuilder<T>> containerSetup(
            klass: KClass<S>, scenariosSet: TestScenarioSet<T, B>
        ) {
            buildSetup(klass, scenariosSet)
        }

        fun <T : BaseContainer, BUILDER : BaseContainer.Builder<T>> fullScenario(
            set: TestScenarioSet<T, BUILDER>,
            builder: BUILDER,
            expectedContainer: T,
            builderAddedScope: BUILDER.() -> Unit = {}
        ) {
            set.scenario {
                id = "full"
                given(builder) {
                    name = "test"
                    image = "test/image:latest"
                    imagePullPolicy = ImagePullPolicy.Always
                    command("echo", "hello")
                    args("hello", "universe")
                    workingDir = "/home/test"

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

                    terminationMessagePath = "/dev/termination-log"
                    terminationMessagePolicy = "FallbackToLogsOnError"

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

                    builder.apply(builderAddedScope)
                }
                expected = expectedContainer
            }
        }

        const val CONTAINER_NAME = "test"

        @JvmStatic
        protected val CONTAINER_PORT = ContainerPort(
            name = "http",
            protocol = Protocol.TCP,
            containerPort = 8080,
            hostIp = "0.0.0.0",
            hostPort = 8080
        )

        protected val CONFIG_MAP_KEY_SELECTOR = ConfigMapKeySelector(
            key = "key",
            name = "config-map-key",
            optional = true
        )
        protected val OBJECT_FIELD_SELECTOR = ObjectFieldSelector(
            fieldPath = "spec.nodeName",
            apiVersion = KAPIVersion.V1
        )
        protected val SECRET_KEY_SELECTOR = SecretKeySelector(
            name = "secret-name",
            key = "key",
            optional = true
        )
        protected val RESOURCE_FIELD_SELECTOR = ResourceFieldSelector(
            resource = "limits.cpu",
            containerName = "container-name",
            divisor = Quantity("10")
        )

        @JvmStatic
        protected val ENV_VAR = EnvVar(
            name = "ENV_VAR_1",
            value = "value1",
            valueFrom = EnvVarSource(
                configMapKeyRef = CONFIG_MAP_KEY_SELECTOR,
                fieldRef = OBJECT_FIELD_SELECTOR,
                secretKeyRef = SECRET_KEY_SELECTOR,
                resourceFieldRef = RESOURCE_FIELD_SELECTOR
            )
        )

        @JvmStatic
        protected val ENV_FROM_SOURCE = EnvFromSource(
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

        @JvmStatic
        protected val VOLUME_MOUNT = VolumeMount(
            name = "test-volume",
            mountPath = "/test-volume",
            mountPropagation = "None",
            readOnly = true,
            recursiveReadOnly = "Disabled",
            subPath = "sub-path",
            subPathExpr = "sub-path-expr"
        )

        @JvmStatic
        protected val VOLUME_DEVICE = VolumeDevice(
            name = "test-volume",
            devicePath = "/dev/xvda"
        )

        @JvmStatic
        protected val VOLUME_RESOURCE_REQUIREMENTS = ContainerResourceRequirements(
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

        protected val EXEC_ACTION = ExecAction(command = listOf("echo", "exec"))

        protected val HTTP_GET_ACTION = HTTPGetAction(
            port = IntOrString(8080),
            host = "localhost",
            httpHeaders = listOf(
                HttpHeader(
                    name = "name",
                    value = "value"
                )
            ),
            path = "/path",
            scheme = "HTTP"
        )


        protected val SLEEP_ACTION = SleepAction(10)

        protected val TCP_SOCKET_ACTION = TCPSocketAction(
            port = IntOrString(8080),
            host = "localhost"
        )

        protected val GRPC_ACTION = GRPCAction(
            port = 8080,
            service = "service"
        )

        @JvmStatic
        protected val LIFECYCLE = Lifecycle(
            postStart = LifecycleHandler(
                exec = EXEC_ACTION,
                httpGet = HTTP_GET_ACTION,
                sleep = SLEEP_ACTION,
                tcpSocket = TCP_SOCKET_ACTION
            ),
            preStop = LifecycleHandler(
                exec = EXEC_ACTION,
                httpGet = HTTP_GET_ACTION,
                sleep = SLEEP_ACTION,
                tcpSocket = TCP_SOCKET_ACTION
            )
        )

        @JvmStatic
        protected val PROBE = Probe(
            exec = EXEC_ACTION,
            httpGet = HTTP_GET_ACTION,
            tcpSocket = TCP_SOCKET_ACTION,
            grpcAction = GRPC_ACTION,
            initialDelaySeconds = 10,
            terminationGracePeriodSeconds = 10,
            periodSeconds = 10,
            timeoutSeconds = 1,
            successThreshold = 1,
            failureThreshold = 3
        )

        @JvmStatic
        protected val SECURITY_CONTEXT = ContainerSecurityContext(
            allowPrivilegeEscalation = true,
            appArmorProfile = AppArmorProfile(
                type = SecurityProfileType.Localhost,
                localHostProfile = "test-profile"
            ),
            capabilities = ContainerSecurityContext.Capabilities(
                add = listOf("NET_ADMIN"),
                drop = listOf("ALL")
            ),
            procMount = "procMount",
            privileged = true,
            readOnlyRootFilesystem = true,
            runAsUser = 1000,
            runAsGroup = 1000,
            runAsNonRoot = true,
            seLinuxOptions = SELinuxOptions(
                level = "s0",
                role = "role",
                type = "type",
                user = "user"
            ),
            seccompProfile = SeccompProfile(
                localhostProfile = "test-profile",
                type = SecurityProfileType.Localhost
            ),
            windowsOptions = WindowsSecurityContextOptions(
                gmsaCredentialSpec = "gmsaCredentialSpec",
                gmsaCredentialSpecName = "gmsaCredentialSpecName",
                runAsUserName = "runAsUserName",
                hostProcess = true
            )
        )
    }
}