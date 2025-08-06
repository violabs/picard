package io.violabs.picard.v2.resources.workload.pod

import io.violabs.picard.Common
import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.AccessMode
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.LocalObjectReference
import io.violabs.picard.v2.common.NodeSelectorRequirement
import io.violabs.picard.v2.common.ObjectFieldSelector
import io.violabs.picard.v2.common.ResourceFieldSelector
import io.violabs.picard.v2.common.condition.Condition
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaimSpec
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaimTemplate
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.VolumeResourceRequirements
import io.violabs.picard.v2.resources.storage.volume.*
import io.violabs.picard.v2.resources.storage.volume.projection.*
import io.violabs.picard.v2.resources.storage.volume.source.*
import io.violabs.picard.v2.resources.workload.pod.affinity.*
import io.violabs.picard.v2.resources.workload.pod.container.*
import io.violabs.picard.v2.resources.workload.pod.container.action.*
import io.violabs.picard.v2.resources.workload.pod.container.config.ConfigMapEnvSource
import io.violabs.picard.v2.resources.workload.pod.container.config.ConfigMapKeySelector
import io.violabs.picard.v2.resources.workload.pod.container.env.EnvFromSource
import io.violabs.picard.v2.resources.workload.pod.container.env.EnvFromSourceDslBuilder
import io.violabs.picard.v2.resources.workload.pod.container.env.EnvVar
import io.violabs.picard.v2.resources.workload.pod.container.env.EnvVarDslBuilder
import io.violabs.picard.v2.resources.workload.pod.container.env.EnvVarSource
import io.violabs.picard.v2.resources.workload.pod.container.ephemeral.ContainerResizePolicy
import io.violabs.picard.v2.resources.workload.pod.container.ephemeral.EphemeralContainer
import io.violabs.picard.v2.resources.workload.pod.container.ephemeral.EphemeralContainerDslBuilder
import io.violabs.picard.v2.resources.workload.pod.container.lifecycle.Lifecycle
import io.violabs.picard.v2.resources.workload.pod.container.lifecycle.LifecycleHandler
import io.violabs.picard.v2.resources.workload.pod.container.lifecycle.LifecycleHandlerDslBuilder
import io.violabs.picard.v2.resources.workload.pod.container.options.SeLinuxOptions
import io.violabs.picard.v2.resources.workload.pod.container.options.SeLinuxOptionsDslBuilder
import io.violabs.picard.v2.resources.workload.pod.container.options.WindowsSecurityContextOptions
import io.violabs.picard.v2.resources.workload.pod.container.options.WindowsSecurityContextOptionsDslBuilder
import io.violabs.picard.v2.resources.workload.pod.container.resource.ResourceClaim
import io.violabs.picard.v2.resources.workload.pod.container.resource.ResourceRequirements
import io.violabs.picard.v2.resources.workload.pod.container.resource.ResourceRequirementsDslBuilder
import io.violabs.picard.v2.resources.workload.pod.container.secret.SecretEnvSource
import io.violabs.picard.v2.resources.workload.pod.container.secret.SecretKeySelector
import io.violabs.picard.v2.resources.workload.pod.container.security.*
import io.violabs.picard.v2.resources.workload.pod.container.state.*
import io.violabs.picard.v2.resources.workload.pod.container.state.user.ContainerUser
import io.violabs.picard.v2.resources.workload.pod.container.state.user.LinuxContainerUser
import io.violabs.picard.v2.resources.workload.pod.container.status.ContainerStatus
import io.violabs.picard.v2.resources.workload.pod.container.status.ContainerStatusDslBuilder
import io.violabs.picard.v2.resources.workload.pod.container.status.VolumeMountStatus
import io.violabs.picard.v2.resources.workload.pod.container.volume.VolumeDevice
import io.violabs.picard.v2.resources.workload.pod.container.volume.VolumeDeviceDslBuilder
import io.violabs.picard.v2.resources.workload.pod.container.volume.VolumeMount
import io.violabs.picard.v2.resources.workload.pod.container.volume.VolumeMountDslBuilder
import io.violabs.picard.v2.resources.workload.pod.dns.PodDnsConfig
import io.violabs.picard.v2.resources.workload.pod.dns.PodDnsConfigOption
import io.violabs.picard.v2.resources.workload.pod.gate.PodReadinessGate
import io.violabs.picard.v2.resources.workload.pod.gate.SchedulingGate
import io.violabs.picard.v2.resources.workload.pod.host.HostAlias
import io.violabs.picard.v2.resources.workload.pod.host.HostIp
import io.violabs.picard.v2.resources.workload.pod.resource.PodResourceClaim
import io.violabs.picard.v2.resources.workload.pod.resource.PodResourceClaimStatus
import io.violabs.picard.v2.resources.workload.pod.security.PodSecurityContext
import io.violabs.picard.v2.resources.workload.pod.security.Sysctl
import org.junit.jupiter.api.BeforeAll

class PodTest : SuccessBuildSim<PodV2, PodV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodTest::class,
            SUCCESS_POSSIBILITIES
        )

        private fun ResourceRequirementsDslBuilder.sharedResourceRequirements() {
            claims {
                resourceClaim {
                    name = PLACEHOLDER
                    request = PLACEHOLDER
                }
            }

            limits(QUANTITY_PAIR)
            requests(QUANTITY_PAIR)
        }

        private val RESOURCE_REQUIREMENTS = ResourceRequirements(
            claims = listOf(
                ResourceClaim(
                    name = PLACEHOLDER,
                    request = PLACEHOLDER
                )
            ),
            limits = mapOf(PLACEHOLDER to QUANTITY),
            requests = mapOf(PLACEHOLDER to QUANTITY)
        )

        private fun HttpGetActionDslBuilder.sharedHttpGet() {
            port = IntOrString(1)
            path = PLACEHOLDER
            host = PLACEHOLDER
            scheme = PLACEHOLDER
            httpHeaders {
                httpHeader {
                    name = PLACEHOLDER
                    value = PLACEHOLDER
                }
            }
        }

        private val HTTP_GET_ACTION = HttpGetAction(
            port = IntOrString(1),
            path = PLACEHOLDER,
            host = PLACEHOLDER,
            scheme = PLACEHOLDER,
            httpHeaders = listOf(
                HttpHeader(
                    name = PLACEHOLDER,
                    value = PLACEHOLDER
                )
            )
        )

        private fun TcpSocketActionDslBuilder.sharedTcpSocket() {
            port = IntOrString(1)
            host = PLACEHOLDER
        }

        private val TCP_SOCKET_ACTION = TcpSocketAction(
            port = IntOrString(1),
            host = PLACEHOLDER
        )

        private fun LifecycleHandlerDslBuilder.sharedLifecycle() {
            exec {
                command(PLACEHOLDER)
            }

            httpGet {
                sharedHttpGet()
            }

            sleep {
                seconds = 1
            }

            tcpSocket {
                sharedTcpSocket()
            }
        }

        private val LIFECYCLE_HANDLER = LifecycleHandler(
            exec = ExecAction(command = PLACEHOLDER_LIST),
            httpGet = HTTP_GET_ACTION,
            sleep = SleepAction(seconds = 1),
            tcpSocket = TCP_SOCKET_ACTION
        )

        private fun ProbeDslBuilder.sharedProbe() {
            exec {
                command(PLACEHOLDER)
            }

            httpGet {
                sharedHttpGet()
            }

            tcpSocket {
                sharedTcpSocket()
            }

            grpc {
                port = 1
                service = PLACEHOLDER
            }

            initialDelaySeconds = 1
            timeoutSeconds = 1
            periodSeconds = 1
            successThreshold = 1
            failureThreshold = 1
            terminationGracePeriodSeconds = 1
        }

        private val PROBE = Probe(
            exec = ExecAction(command = PLACEHOLDER_LIST),
            httpGet = HTTP_GET_ACTION,
            tcpSocket = TCP_SOCKET_ACTION,
            grpc = GrpcAction(
                port = 1,
                service = PLACEHOLDER
            ),
            initialDelaySeconds = 1,
            timeoutSeconds = 1,
            periodSeconds = 1,
            successThreshold = 1,
            failureThreshold = 1,
            terminationGracePeriodSeconds = 1
        )

        private fun EnvVarDslBuilder.Group.sharedEnvVar() {
            envVar {
                name = PLACEHOLDER
                value = PLACEHOLDER
                valueFrom {
                    fieldRef {
                        fieldPath = PLACEHOLDER
                        apiVersion = KAPIVersion.V1
                    }
                    resourceFieldRef {
                        containerName = PLACEHOLDER
                        divisor(PLACEHOLDER)
                        resource = PLACEHOLDER
                    }
                    configMapKeyRef {
                        key = PLACEHOLDER
                        name = PLACEHOLDER
                        optional()
                    }
                    secretKeyRef {
                        key = PLACEHOLDER
                        name = PLACEHOLDER
                        optional()
                    }
                }
            }
        }

        private val ENV_VAR = EnvVar(
            name = PLACEHOLDER,
            value = PLACEHOLDER,
            valueFrom = EnvVarSource(
                fieldRef = ObjectFieldSelector(
                    fieldPath = PLACEHOLDER,
                    apiVersion = KAPIVersion.V1
                ),
                configMapKeyRef = ConfigMapKeySelector(
                    key = PLACEHOLDER,
                    name = PLACEHOLDER,
                    optional = true
                ),
                resourceFieldRef = ResourceFieldSelector(
                    containerName = PLACEHOLDER,
                    divisor = QUANTITY,
                    resource = PLACEHOLDER
                ),
                secretKeyRef = SecretKeySelector(
                    key = PLACEHOLDER,
                    name = PLACEHOLDER,
                    optional = true
                )
            )
        )

        private fun EnvFromSourceDslBuilder.Group.sharedEnvFromSource() {
            envFromSource {
                configMapRef {
                    name = PLACEHOLDER
                    optional()
                }
                prefix = PLACEHOLDER
                secretRef {
                    name = PLACEHOLDER
                    optional()
                }
            }
        }

        private val ENV_FROM_SOURCE = EnvFromSource(
            configMapRef = ConfigMapEnvSource(
                name = PLACEHOLDER,
                optional = true
            ),
            prefix = PLACEHOLDER,
            secretRef = SecretEnvSource(
                name = PLACEHOLDER,
                optional = true
            )
        )

        private fun VolumeMountDslBuilder.Group.sharedVolumeMount() {
            volumeMount {
                mountPath = PLACEHOLDER
                name = PLACEHOLDER
                readOnly()
                subPath = PLACEHOLDER
                subPathExpr = PLACEHOLDER
                mountPropagation = VolumeMount.MountPropagationMode.HostToContainer
            }
        }

        private val VOLUME_MOUNT = VolumeMount(
            mountPath = PLACEHOLDER,
            name = PLACEHOLDER,
            readOnly = true,
            subPath = PLACEHOLDER,
            subPathExpr = PLACEHOLDER,
            mountPropagation = VolumeMount.MountPropagationMode.HostToContainer
        )

        private fun VolumeDeviceDslBuilder.Group.sharedVolumeDevice() {
            volumeDevice {
                name = PLACEHOLDER
                devicePath = PLACEHOLDER
            }
        }

        private val VOLUME_DEVICE = VolumeDevice(
            name = PLACEHOLDER,
            devicePath = PLACEHOLDER
        )

        private fun AppArmorProfileDslBuilder.sharedAppArmorProfile() {
            type = AppArmorProfile.Type.RuntimeDefault
            localhostProfile = PLACEHOLDER
        }

        private val APP_ARMOR_PROFILE = AppArmorProfile(
            type = AppArmorProfile.Type.RuntimeDefault,
            localhostProfile = PLACEHOLDER
        )

        private fun SeLinuxOptionsDslBuilder.sharedSeLinuxOptions() {
            level = PLACEHOLDER
            role = PLACEHOLDER
            type = PLACEHOLDER
            user = PLACEHOLDER
        }

        private val SE_LINUX_OPTIONS = SeLinuxOptions(
            level = PLACEHOLDER,
            role = PLACEHOLDER,
            type = PLACEHOLDER,
            user = PLACEHOLDER
        )

        private fun SeccompProfileDslBuilder.sharedSeccompProfile() {
            type = SeccompProfile.Type.RuntimeDefault
            localhostProfile = PLACEHOLDER
        }

        private val SECCOMP_PROFILE = SeccompProfile(
            type = SeccompProfile.Type.RuntimeDefault,
            localhostProfile = PLACEHOLDER
        )

        private fun WindowsSecurityContextOptionsDslBuilder.sharedWindowsSecurityContextOptions() {
            hostProcess()
            gmsaCredentialSpec = PLACEHOLDER
            gmsaCredentialUser = PLACEHOLDER
            runAsUserName = PLACEHOLDER
        }

        private val WINDOWS_SECURITY_CONTEXT_OPTIONS = WindowsSecurityContextOptions(
            hostProcess = true,
            gmsaCredentialSpec = PLACEHOLDER,
            gmsaCredentialUser = PLACEHOLDER,
            runAsUserName = PLACEHOLDER
        )

        private fun ContainerSecurityContextDslBuilder.sharedSecurityContext() {
            allowPrivilegeEscalation()
            appArmorProfile {
                sharedAppArmorProfile()
            }

            capabilities {
                add(PLACEHOLDER)
                drop(PLACEHOLDER)
            }

            privileged()
            procMount = PLACEHOLDER
            readOnlyRootFilesystem()
            runAsGroup = 1
            runAsNonRoot()
            runAsUser = 1

            seLinuxOptions { sharedSeLinuxOptions() }
            seccompProfile { sharedSeccompProfile() }
            windowsOptions { sharedWindowsSecurityContextOptions() }
        }

        private val CONTAINER_SECURITY_CONTEXT = ContainerSecurityContext(
            allowPrivilegeEscalation = true,
            appArmorProfile = AppArmorProfile(
                type = AppArmorProfile.Type.RuntimeDefault,
                localhostProfile = PLACEHOLDER
            ),
            capabilities = Capabilities(
                add = listOf(PLACEHOLDER),
                drop = listOf(PLACEHOLDER)
            ),
            privileged = true,
            procMount = PLACEHOLDER,
            readOnlyRootFilesystem = true,
            runAsGroup = 1,
            runAsNonRoot = true,
            runAsUser = 1,
            seLinuxOptions = SE_LINUX_OPTIONS,
            seccompProfile = SECCOMP_PROFILE,
            windowsOptions = WINDOWS_SECURITY_CONTEXT_OPTIONS,
        )

        private fun ContainerDslBuilder.Group.sharedContainer() {
            container {
                name = PLACEHOLDER
                image = PLACEHOLDER
                imagePullPolicy = Container.ImagePullPolicy.IfNotPresent
                command(PLACEHOLDER)
                args(PLACEHOLDER)
                workingDir = PLACEHOLDER

                env { sharedEnvVar() }
                envFrom { sharedEnvFromSource() }
                volumeMounts { sharedVolumeMount() }
                volumeDevices { sharedVolumeDevice() }
                resources { sharedResourceRequirements() }
                securityContext { sharedSecurityContext() }

                terminationMessagePath = PLACEHOLDER
                terminationMessagePolicy = Container.TerminationMessagePolicy.File

                stdin()
                stdinOnce()
                tty()

                ports {
                    containerPort {
                        containerPort = 1
                        hostIp = PLACEHOLDER
                        hostPort = 1
                        name = PLACEHOLDER
                        protocol = ContainerPort.Protocol.TCP
                    }
                }

                lifecycle {
                    postStart { sharedLifecycle() }
                    preStop { sharedLifecycle() }
                    stopSignal = PLACEHOLDER
                }

                livenessProbe { sharedProbe() }
                readinessProbe { sharedProbe() }
                startupProbe { sharedProbe() }
            }
        }

        private val CONTAINER = Container(
            name = PLACEHOLDER,
            image = PLACEHOLDER,
            imagePullPolicy = Container.ImagePullPolicy.IfNotPresent,
            command = PLACEHOLDER_LIST,
            args = PLACEHOLDER_LIST,
            workingDir = PLACEHOLDER,
            ports = listOf(
                ContainerPort(
                    containerPort = 1,
                    hostIp = PLACEHOLDER,
                    hostPort = 1,
                    name = PLACEHOLDER,
                    protocol = ContainerPort.Protocol.TCP
                )
            ),
            env = listOf(ENV_VAR),
            envFrom = listOf(ENV_FROM_SOURCE),
            volumeMounts = listOf(VOLUME_MOUNT),
            volumeDevices = listOf(VOLUME_DEVICE),
            resources = RESOURCE_REQUIREMENTS,
            lifecycle = Lifecycle(
                postStart = LIFECYCLE_HANDLER,
                preStop = LIFECYCLE_HANDLER,
                stopSignal = PLACEHOLDER
            ),
            terminationMessagePath = PLACEHOLDER,
            terminationMessagePolicy = Container.TerminationMessagePolicy.File,
            livenessProbe = PROBE,
            readinessProbe = PROBE,
            startupProbe = PROBE,
            securityContext = CONTAINER_SECURITY_CONTEXT,
            stdin = true,
            stdinOnce = true,
            tty = true
        )

        private fun EphemeralContainerDslBuilder.Group.sharedEphemeralContainer() {
            ephemeralContainer {
                name = PLACEHOLDER
                image = PLACEHOLDER
                imagePullPolicy = EphemeralContainer.ImagePullPolicy.IfNotPresent
                command(PLACEHOLDER)
                args(PLACEHOLDER)
                workingDir = PLACEHOLDER

                env { sharedEnvVar() }
                envFrom { sharedEnvFromSource() }
                volumeMounts { sharedVolumeMount() }
                volumeDevices { sharedVolumeDevice() }
                securityContext { sharedSecurityContext() }

                targetContainerName = PLACEHOLDER
                terminationMessagePath = PLACEHOLDER
                terminationMessagePolicy = EphemeralContainer.TerminationMessagePolicy.File

                resizePolicy {
                    containerResizePolicy {
                        resourceName = ContainerResizePolicy.ResourceName.Cpu
                        restartPolicy = PLACEHOLDER
                    }
                }
            }
        }

        private val EPHEMERAL_CONTAINER = EphemeralContainer(
            name = PLACEHOLDER,
            image = PLACEHOLDER,
            imagePullPolicy = EphemeralContainer.ImagePullPolicy.IfNotPresent,
            command = PLACEHOLDER_LIST,
            args = PLACEHOLDER_LIST,
            workingDir = PLACEHOLDER,
            env = listOf(ENV_VAR),
            envFrom = listOf(ENV_FROM_SOURCE),
            volumeMounts = listOf(VOLUME_MOUNT),
            volumeDevices = listOf(VOLUME_DEVICE),
            securityContext = CONTAINER_SECURITY_CONTEXT,
            targetContainerName = PLACEHOLDER,
            terminationMessagePath = PLACEHOLDER,
            terminationMessagePolicy = EphemeralContainer.TerminationMessagePolicy.File,
            resizePolicy = listOf(
                ContainerResizePolicy(
                    resourceName = ContainerResizePolicy.ResourceName.Cpu,
                    restartPolicy = PLACEHOLDER
                )
            )
        )

        private fun KeyToPathDslBuilder.Group.sharedKeyToPath() {
            keyToPath {
                key = PLACEHOLDER
                path = PLACEHOLDER
                mode = 1
            }
        }

        private val KEY_TO_PATH = KeyToPath(
            key = PLACEHOLDER,
            path = PLACEHOLDER,
            mode = 1
        )

        private fun DownwardApiVolumeFileDslBuilder.Group.sharedDownwardApiVolumeFile() {
            downwardApiVolumeFile {
                path = PLACEHOLDER
                fieldRef {
                    fieldPath = PLACEHOLDER
                    apiVersion = KAPIVersion.V1
                }
                mode = 1
                resourceFieldRef {
                    containerName = PLACEHOLDER
                    divisor(PLACEHOLDER)
                    resource = PLACEHOLDER
                }
            }
        }

        private val DOWNWARD_API_VOLUME_FILE = DownwardApiVolumeFile(
            path = PLACEHOLDER,
            fieldRef = ObjectFieldSelector(
                fieldPath = PLACEHOLDER,
                apiVersion = KAPIVersion.V1
            ),
            mode = 1,
            resourceFieldRef = ResourceFieldSelector(
                containerName = PLACEHOLDER,
                divisor = QUANTITY,
                resource = PLACEHOLDER
            )
        )

        private fun NodeSelectorTermDslBuilder.sharedNodeSelectorTerm() {
            matchExpressions {
                nodeSelectorRequirement {
                    key = PLACEHOLDER
                    operator = NodeSelectorRequirement.Operator.In
                    values(PLACEHOLDER)
                }
            }
            matchFields {
                nodeSelectorRequirement {
                    key = PLACEHOLDER
                    operator = NodeSelectorRequirement.Operator.In
                    values(PLACEHOLDER)
                }
            }
        }

        private val NODE_SELECTOR_TERM = NodeSelectorTerm(
            matchExpressions = listOf(
                NodeSelectorRequirement(
                    key = PLACEHOLDER,
                    operator = NodeSelectorRequirement.Operator.In,
                    values = listOf(PLACEHOLDER)
                )
            ),
            matchFields = listOf(
                NodeSelectorRequirement(
                    key = PLACEHOLDER,
                    operator = NodeSelectorRequirement.Operator.In,
                    values = listOf(PLACEHOLDER)
                )
            )
        )

        private fun PodAffinityTermDslBuilder.sharedPodAffinityTerm() {
            labelSelector { sharedSelector() }
            namespaceSelector { sharedSelector() }
            namespaces(PLACEHOLDER)
            topologyKey = PLACEHOLDER
            matchLabelKeys(PLACEHOLDER)
            mismatchLabelKeys(PLACEHOLDER)
        }

        private val POD_AFFINITY_TERM = PodAffinityTerm(
            labelSelector = Common.LABEL_SELECTOR,
            namespaceSelector = Common.LABEL_SELECTOR,
            namespaces = listOf(PLACEHOLDER),
            topologyKey = PLACEHOLDER,
            matchLabelKeys = listOf(PLACEHOLDER),
            mismatchLabelKeys = listOf(PLACEHOLDER)
        )

        private fun ContainerStateDslBuilder.sharedState() {
            running {
                startedAt = NOW
            }

            terminated {
                exitCode = 1
                reason = PLACEHOLDER
                message = PLACEHOLDER
                startedAt = NOW
                finishedAt = NOW
            }

            waiting {
                reason = PLACEHOLDER
                message = PLACEHOLDER
            }
        }

        private val CONTAINER_STATE = ContainerState(
            running = ContainerStateRunning(
                startedAt = NOW
            ),
            terminated = ContainerStateTerminated(
                exitCode = 1,
                reason = PLACEHOLDER,
                message = PLACEHOLDER,
                startedAt = NOW,
                finishedAt = NOW
            ),
            waiting = ContainerStateWaiting(
                reason = PLACEHOLDER,
                message = PLACEHOLDER
            )
        )

        private fun ContainerStatusDslBuilder.Group.sharedContainerStatus() {
            containerStatus {
                name = PLACEHOLDER
                ready()
                restartCount = 1
                image = PLACEHOLDER
                imageId = PLACEHOLDER
                containerId = PLACEHOLDER
                started()

                state { sharedState() }
                lastState { sharedState() }

                allocatedResources(QUANTITY_PAIR)
                resources { sharedResourceRequirements() }

                stopSignal = PLACEHOLDER
                user {
                    linux {
                        gid = 1
                        uid = 1
                        supplementalGroups(1)
                    }
                }

                volumeMounts {
                    volumeMountStatus {
                        mountPath = PLACEHOLDER
                        name = PLACEHOLDER
                        readOnly()
                        recursiveReadOnly = PLACEHOLDER
                    }
                }
            }
        }

        private val CONTAINER_STATUS = ContainerStatus(
            name = PLACEHOLDER,
            ready = true,
            restartCount = 1,
            image = PLACEHOLDER,
            imageId = PLACEHOLDER,
            containerId = PLACEHOLDER,
            started = true,
            state = CONTAINER_STATE,
            lastState = CONTAINER_STATE,
            allocatedResources = mapOf(PLACEHOLDER to QUANTITY),
            resources = ResourceRequirements(
                claims = listOf(
                    ResourceClaim(
                        name = PLACEHOLDER,
                        request = PLACEHOLDER
                    )
                ),
                limits = mapOf(PLACEHOLDER to QUANTITY),
                requests = mapOf(PLACEHOLDER to QUANTITY)
            ),
            stopSignal = PLACEHOLDER,
            user = ContainerUser(
                linux = LinuxContainerUser(
                    gid = 1,
                    uid = 1,
                    supplementalGroups = listOf(1)
                )
            ),
            volumeMounts = listOf(
                VolumeMountStatus(
                    mountPath = PLACEHOLDER,
                    name = PLACEHOLDER,
                    readOnly = true,
                    recursiveReadOnly = PLACEHOLDER
                )
            )
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PodV2, PodV2DslBuilder> {
            scenario {
                id = "minimum"
                given(PodV2DslBuilder())
                expected = PodV2()
            }

            scenario {
                id = "full"
                given(PodV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        containers { sharedContainer() }
                        initContainers { sharedContainer() }
                        ephemeralContainers { sharedEphemeralContainer() }
                        imagePullSecrets {
                            localObjectReference {
                                name = PLACEHOLDER
                            }
                        }
                        enableServiceLinks()
                        os {
                            name = PLACEHOLDER
                        }
                        volumes {
                            volume {
                                name = PLACEHOLDER
                                persistentVolumeClaim {
                                    claimName = PLACEHOLDER
                                    readOnly()
                                }

                                configMap {
                                    name = PLACEHOLDER
                                    items { sharedKeyToPath() }
                                    defaultMode = 1
                                    optional()
                                }

                                secret {
                                    secretName = PLACEHOLDER
                                    items { sharedKeyToPath() }
                                    defaultMode = 1
                                    optional()
                                }

                                downwardApi {
                                    defaultMode = 1
                                    items { sharedDownwardApiVolumeFile() }
                                }

                                projected {
                                    defaultMode = 1
                                    sources {
                                        volumeProjection {
                                            clusterTrustBundle {
                                                path = PLACEHOLDER
                                                labelSelector {
                                                    sharedSelector()
                                                }
                                                name = PLACEHOLDER
                                                optional()
                                                signerName = PLACEHOLDER
                                            }

                                            configMap {
                                                name = PLACEHOLDER
                                                optional()
                                                items { sharedKeyToPath() }
                                            }

                                            downwardApi {
                                                items { sharedDownwardApiVolumeFile() }
                                            }

                                            secret {
                                                name = PLACEHOLDER
                                                optional()
                                                items { sharedKeyToPath() }
                                            }

                                            serviceAccountToken {
                                                path = PLACEHOLDER
                                                audience = PLACEHOLDER
                                                expirationSeconds = 1
                                            }
                                        }
                                    }
                                }

                                emptyDir {
                                    medium = PLACEHOLDER
                                    sizeLimit(PLACEHOLDER)
                                }

                                hostPath {
                                    path = PLACEHOLDER
                                    type = PLACEHOLDER
                                }

                                csi {
                                    driver = PLACEHOLDER
                                    readOnly()
                                    nodePublishSecretRef {
                                        name = PLACEHOLDER
                                    }
                                    fsType = PLACEHOLDER
                                    volumeAttributes(PLACEHOLDER to PLACEHOLDER)
                                }

                                ephemeral {
                                    volumeClaimTemplate {
                                        spec {
                                            accessModes(AccessMode.ReadWriteOnce)
                                            selector { sharedSelector() }
                                            resources {
                                                limits(QUANTITY_PAIR)
                                                requests(QUANTITY_PAIR)
                                            }
                                            storageClassName = PLACEHOLDER
                                            volumeMode = PLACEHOLDER
                                            volumeName = PLACEHOLDER
                                        }

                                        metadata {
                                            sharedObjectMeta()
                                        }
                                    }
                                }

                                fc {
                                    fsType = PLACEHOLDER
                                    lun = 1
                                    readOnly()
                                    targetWwns(PLACEHOLDER)
                                    wwids(PLACEHOLDER)
                                }

                                iscsi {
                                    iqn = PLACEHOLDER
                                    lun = 1
                                    targetPortal = PLACEHOLDER
                                    chapAuthDiscovery()
                                    chapAuthSession()
                                    fsType = PLACEHOLDER
                                    initiatorName = PLACEHOLDER
                                    iscsiInterface = PLACEHOLDER
                                    portals(PLACEHOLDER)
                                    readOnly()
                                    secretRef {
                                        name = PLACEHOLDER
                                    }
                                }

                                image {
                                    imagePullPolicy = ImageVolumeSource.ImagePullPolicy.Always
                                    reference = PLACEHOLDER
                                }

                                nfs {
                                    path = PLACEHOLDER
                                    server = PLACEHOLDER
                                    readOnly()
                                }
                            }
                        }

                        nodeSelector(PLACEHOLDER to PLACEHOLDER)
                        nodeName = PLACEHOLDER

                        affinity {
                            nodeAffinity {
                                requiredDuringSchedulingIgnoredDuringExecution {
                                    nodeSelectorTerms {
                                        nodeSelectorTerm {
                                            sharedNodeSelectorTerm()
                                        }
                                    }
                                }

                                preferredDuringSchedulingIgnoredDuringExecution {
                                    preferredSchedulingTerm {
                                        weight = 1
                                        preference {
                                            sharedNodeSelectorTerm()
                                        }
                                    }
                                }
                            }

                            podAffinity {
                                requiredDuringSchedulingIgnoredDuringExecution {
                                    podAffinityTerm {
                                        sharedPodAffinityTerm()
                                    }
                                }

                                preferredDuringSchedulingIgnoredDuringExecution {
                                    weightedPodAffinityTerm {
                                        weight = 1
                                        podAffinityTerm {
                                            sharedPodAffinityTerm()
                                        }
                                    }
                                }
                            }

                            podAntiAffinity {
                                requiredDuringSchedulingIgnoredDuringExecution {
                                    podAffinityTerm {
                                        sharedPodAffinityTerm()
                                    }
                                }

                                preferredDuringSchedulingIgnoredDuringExecution {
                                    weightedPodAffinityTerm {
                                        weight = 1
                                        podAffinityTerm {
                                            sharedPodAffinityTerm()
                                        }
                                    }
                                }
                            }
                        }

                        tolerations {
                            toleration {
                                key = PLACEHOLDER
                                operator = Toleration.Operator.Exists
                                value = PLACEHOLDER
                                effect = Toleration.Effect.NoExecute
                                tolerationSeconds = 1
                            }
                        }

                        schedulerName = PLACEHOLDER
                        runtimeClassName = PLACEHOLDER
                        priorityClassName = PLACEHOLDER
                        priority = 1
                        preemptionPolicy = PodSpec.PreemptionPolicy.PreemptLowerPriority

                        topologySpreadConstraints {
                            topologySpreadConstraint {
                                maxSkew = 1
                                topologyKey = PLACEHOLDER
                                whenUnsatisfiable = PLACEHOLDER
                                labelSelector { sharedSelector() }
                                matchLabelKeys(PLACEHOLDER)
                                minDomains = 1
                                nodeAffinityPolicy = PLACEHOLDER
                                nodeTaintsPolicy = PLACEHOLDER
                            }
                        }

                        overhead(QUANTITY_PAIR)
                        restartPolicy = PodSpec.RestartPolicy.Never
                        terminationGracePeriodSeconds = 1
                        activeDeadlineSeconds = 1

                        readinessGates {
                            podReadinessGate {
                                conditionType = PLACEHOLDER
                            }
                        }

                        hostname = PLACEHOLDER
                        setHostnameAsFqdn()
                        subdomain = PLACEHOLDER

                        hostAliases {
                            hostAlias {
                                ip = PLACEHOLDER
                                hostnames(PLACEHOLDER)
                            }
                        }

                        dnsConfig {
                            nameServers(PLACEHOLDER)
                            options {
                                podDnsConfigOption {
                                    name = PLACEHOLDER
                                    value = PLACEHOLDER
                                }
                            }
                            searches(PLACEHOLDER)
                        }

                        dnsPolicy = PodSpec.DnsPolicy.ClusterFirst

                        hostNetwork()
                        hostPid()
                        hostIpc()
                        shareProcessNamespace()
                        serviceAccountName = PLACEHOLDER
                        automountServiceAccountToken()
                        securityContext {
                            appArmorProfile { sharedAppArmorProfile() }
                            fsGroup = 1
                            fsGroupChangePolicy = PodSecurityContext.FsGroupChangePolicy.OnRootMismatch
                            runAsGroup = 1
                            runAsNonRoot()
                            runAsUser = 1
                            seLinuxChangePolicy = PodSecurityContext.SeLinuxChangePolicy.MountOption
                            seLinuxOptions { sharedSeLinuxOptions() }
                            seccompProfile { sharedSeccompProfile() }
                            windowsOptions { sharedWindowsSecurityContextOptions() }
                            supplementalGroups(1)
                            supplementalGroupsPolicy = PodSecurityContext.SupplementalGroupsPolicy.Merge
                            sysctls {
                                sysctl {
                                    name = PLACEHOLDER
                                    value = PLACEHOLDER
                                }
                            }
                        }
                        hostUsers()
                        resources { sharedResourceRequirements() }
                        resourceClaims {
                            podResourceClaim {
                                name = PLACEHOLDER
                                resourceClaimName = PLACEHOLDER
                                resourceClaimTemplateName = PLACEHOLDER
                            }
                        }
                        schedulingGates {
                            schedulingGate {
                                name = PLACEHOLDER
                            }
                        }
                    }

                    status {
                        nominatedNodeName = PLACEHOLDER
                        hostIp = PLACEHOLDER
                        hostIps {
                            hostIp {
                                ip = PLACEHOLDER
                            }
                        }
                        startTime = NOW
                        phase = PodStatus.Phase.Succeeded
                        message = PLACEHOLDER
                        reason = PLACEHOLDER
                        podIp = PLACEHOLDER
                        podIps {
                            podIp {
                                ip = PLACEHOLDER
                            }
                        }

                        conditions {
                            condition {
                                type = PLACEHOLDER
                                status = BooleanType.True
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                            }
                        }

                        qosClass = PLACEHOLDER

                        initContainerStatuses { sharedContainerStatus() }
                        containerStatuses { sharedContainerStatus() }
                        ephemeralContainerStatuses { sharedContainerStatus() }

                        resourceClaimStatuses {
                            podResourceClaimStatus {
                                name = PLACEHOLDER
                                resourceClaimName = PLACEHOLDER
                            }
                        }

                        resize = PLACEHOLDER
                        observedGeneration = 1
                    }
                }
                expected = PodV2(
                    metadata = OBJECT_META,
                    spec = PodSpec(
                        containers = listOf(CONTAINER),
                        initContainers = listOf(CONTAINER),
                        ephemeralContainers = listOf(EPHEMERAL_CONTAINER),
                        imagePullSecrets = listOf(
                            LocalObjectReference(
                                name = PLACEHOLDER
                            )
                        ),
                        enableServiceLinks = true,
                        os = PodOs(name = PLACEHOLDER),
                        volumes = listOf(
                            Volume(
                                name = PLACEHOLDER,
                                persistentVolumeClaim = PersistentVolumeClaimVolumeSource(
                                    claimName = PLACEHOLDER,
                                    readOnly = true
                                ),
                                configMap = ConfigMapVolumeSource(
                                    name = PLACEHOLDER,
                                    items = listOf(KEY_TO_PATH),
                                    defaultMode = 1,
                                    optional = true
                                ),
                                secret = SecretVolumeSource(
                                    secretName = PLACEHOLDER,
                                    items = listOf(KEY_TO_PATH),
                                    defaultMode = 1,
                                    optional = true
                                ),
                                downwardApi = DownwardApiVolumeSource(
                                    defaultMode = 1,
                                    items = listOf(DOWNWARD_API_VOLUME_FILE)
                                ),
                                projected = ProjectedVolumeSource(
                                    defaultMode = 1,
                                    sources = listOf(
                                        VolumeProjection(
                                            clusterTrustBundle = ClusterTrustBundleProjection(
                                                path = PLACEHOLDER,
                                                labelSelector = Common.LABEL_SELECTOR,
                                                name = PLACEHOLDER,
                                                optional = true,
                                                signerName = PLACEHOLDER
                                            ),
                                            configMap = ConfigMapProjection(
                                                name = PLACEHOLDER,
                                                optional = true,
                                                items = listOf(KEY_TO_PATH)
                                            ),
                                            downwardApi = DownwardApiProjection(
                                                items = listOf(DOWNWARD_API_VOLUME_FILE)
                                            ),
                                            secret = SecretProjection(
                                                name = PLACEHOLDER,
                                                optional = true,
                                                items = listOf(KEY_TO_PATH)
                                            ),
                                            serviceAccountToken = ServiceAccountTokenProjection(
                                                path = PLACEHOLDER,
                                                audience = PLACEHOLDER,
                                                expirationSeconds = 1
                                            )
                                        )
                                    )
                                ),
                                emptyDir = EmptyDirVolumeSource(
                                    medium = PLACEHOLDER,
                                    sizeLimit = QUANTITY
                                ),
                                hostPath = HostPathVolumeSource(
                                    path = PLACEHOLDER,
                                    type = PLACEHOLDER
                                ),
                                csi = CsiVolumeSource(
                                    driver = PLACEHOLDER,
                                    readOnly = true,
                                    nodePublishSecretRef = LocalObjectReference(
                                        name = PLACEHOLDER
                                    ),
                                    fsType = PLACEHOLDER,
                                    volumeAttributes = mapOf(PLACEHOLDER to PLACEHOLDER)
                                ),
                                ephemeral = EphemeralVolumeSource(
                                    volumeClaimTemplate = PersistentVolumeClaimTemplate(
                                        spec = PersistentVolumeClaimSpec(
                                            accessModes = listOf(AccessMode.ReadWriteOnce),
                                            selector = Common.LABEL_SELECTOR,
                                            resources = VolumeResourceRequirements(
                                                limits = mapOf(PLACEHOLDER to QUANTITY),
                                                requests = mapOf(PLACEHOLDER to QUANTITY)
                                            ),
                                            storageClassName = PLACEHOLDER,
                                            volumeMode = PLACEHOLDER,
                                            volumeName = PLACEHOLDER
                                        ),
                                        metadata = OBJECT_META
                                    )
                                ),
                                fc = FcVolumeSource(
                                    fsType = PLACEHOLDER,
                                    lun = 1,
                                    readOnly = true,
                                    targetWwns = listOf(PLACEHOLDER),
                                    wwids = listOf(PLACEHOLDER)
                                ),
                                iscsi = IscsiVolumeSource(
                                    iqn = PLACEHOLDER,
                                    lun = 1,
                                    targetPortal = PLACEHOLDER,
                                    chapAuthDiscovery = true,
                                    chapAuthSession = true,
                                    fsType = PLACEHOLDER,
                                    initiatorName = PLACEHOLDER,
                                    iscsiInterface = PLACEHOLDER,
                                    portals = listOf(PLACEHOLDER),
                                    readOnly = true,
                                    secretRef = LocalObjectReference(name = PLACEHOLDER)
                                ),
                                image = ImageVolumeSource(
                                    imagePullPolicy = ImageVolumeSource.ImagePullPolicy.Always,
                                    reference = PLACEHOLDER
                                ),
                                nfs = NfsVolumeSource(
                                    path = PLACEHOLDER,
                                    server = PLACEHOLDER,
                                    readOnly = true
                                )
                            )
                        ),
                        nodeSelector = mapOf(PLACEHOLDER to PLACEHOLDER),
                        nodeName = PLACEHOLDER,
                        affinity = Affinity(
                            nodeAffinity = NodeAffinity(
                                requiredDuringSchedulingIgnoredDuringExecution = NodeSelector(
                                    nodeSelectorTerms = listOf(NODE_SELECTOR_TERM)
                                ),
                                preferredDuringSchedulingIgnoredDuringExecution = listOf(
                                    PreferredSchedulingTerm(
                                        weight = 1,
                                        preference = NODE_SELECTOR_TERM
                                    )
                                )
                            ),
                            podAffinity = PodAffinity(
                                requiredDuringSchedulingIgnoredDuringExecution = listOf(
                                    POD_AFFINITY_TERM
                                ),
                                preferredDuringSchedulingIgnoredDuringExecution = listOf(
                                    WeightedPodAffinityTerm(
                                        weight = 1,
                                        podAffinityTerm = POD_AFFINITY_TERM
                                    )
                                )
                            ),
                            podAntiAffinity = PodAntiAffinity(
                                requiredDuringSchedulingIgnoredDuringExecution = listOf(
                                    POD_AFFINITY_TERM
                                ),
                                preferredDuringSchedulingIgnoredDuringExecution = listOf(
                                    WeightedPodAffinityTerm(
                                        weight = 1,
                                        podAffinityTerm = POD_AFFINITY_TERM
                                    )
                                )
                            )
                        ),
                        tolerations = listOf(
                            Toleration(
                                key = PLACEHOLDER,
                                operator = Toleration.Operator.Exists,
                                value = PLACEHOLDER,
                                effect = Toleration.Effect.NoExecute,
                                tolerationSeconds = 1
                            )
                        ),
                        schedulerName = PLACEHOLDER,
                        runtimeClassName = PLACEHOLDER,
                        priorityClassName = PLACEHOLDER,
                        priority = 1,
                        preemptionPolicy = PodSpec.PreemptionPolicy.PreemptLowerPriority,
                        topologySpreadConstraints = listOf(
                            TopologySpreadConstraint(
                                maxSkew = 1,
                                topologyKey = PLACEHOLDER,
                                whenUnsatisfiable = PLACEHOLDER,
                                labelSelector = Common.LABEL_SELECTOR,
                                matchLabelKeys = listOf(PLACEHOLDER),
                                minDomains = 1,
                                nodeAffinityPolicy = PLACEHOLDER,
                                nodeTaintsPolicy = PLACEHOLDER
                            )
                        ),
                        overhead = mapOf(PLACEHOLDER to QUANTITY),
                        restartPolicy = PodSpec.RestartPolicy.Never,
                        terminationGracePeriodSeconds = 1,
                        activeDeadlineSeconds = 1,
                        readinessGates = listOf(
                            PodReadinessGate(conditionType = PLACEHOLDER)
                        ),
                        hostname = PLACEHOLDER,
                        setHostnameAsFqdn = true,
                        subdomain = PLACEHOLDER,
                        hostAliases = listOf(
                            HostAlias(
                                ip = PLACEHOLDER,
                                hostnames = listOf(PLACEHOLDER)
                            )
                        ),
                        dnsConfig = PodDnsConfig(
                            nameServers = listOf(PLACEHOLDER),
                            options = listOf(
                                PodDnsConfigOption(
                                    name = PLACEHOLDER,
                                    value = PLACEHOLDER
                                )
                            ),
                            searches = listOf(PLACEHOLDER)
                        ),
                        dnsPolicy = PodSpec.DnsPolicy.ClusterFirst,
                        hostNetwork = true,
                        hostPid = true,
                        hostIpc = true,
                        shareProcessNamespace = true,
                        serviceAccountName = PLACEHOLDER,
                        automountServiceAccountToken = true,
                        securityContext = PodSecurityContext(
                            appArmorProfile = APP_ARMOR_PROFILE,
                            fsGroup = 1,
                            fsGroupChangePolicy = PodSecurityContext.FsGroupChangePolicy.OnRootMismatch,
                            runAsGroup = 1,
                            runAsNonRoot = true,
                            runAsUser = 1,
                            seLinuxChangePolicy = PodSecurityContext.SeLinuxChangePolicy.MountOption,
                            seLinuxOptions = SE_LINUX_OPTIONS,
                            seccompProfile = SECCOMP_PROFILE,
                            windowsOptions = WINDOWS_SECURITY_CONTEXT_OPTIONS,
                            supplementalGroups = listOf(1),
                            supplementalGroupsPolicy = PodSecurityContext.SupplementalGroupsPolicy.Merge,
                            sysctls = listOf(
                                Sysctl(
                                    name = PLACEHOLDER,
                                    value = PLACEHOLDER
                                )
                            )
                        ),
                        hostUsers = true,
                        resources = RESOURCE_REQUIREMENTS,
                        resourceClaims = listOf(
                            PodResourceClaim(
                                name = PLACEHOLDER,
                                resourceClaimName = PLACEHOLDER,
                                resourceClaimTemplateName = PLACEHOLDER
                            )
                        ),
                        schedulingGates = listOf(
                            SchedulingGate(
                                name = PLACEHOLDER
                            )
                        )
                    ),
                    status = PodStatus(
                        nominatedNodeName = PLACEHOLDER,
                        hostIp = PLACEHOLDER,
                        hostIps = listOf(
                            HostIp(
                                ip = PLACEHOLDER
                            )
                        ),
                        startTime = NOW,
                        phase = PodStatus.Phase.Succeeded,
                        message = PLACEHOLDER,
                        reason = PLACEHOLDER,
                        podIp = PLACEHOLDER,
                        podIps = listOf(
                            PodIp(
                                ip = PLACEHOLDER
                            )
                        ),
                        conditions = listOf(
                            Condition(
                                type = PLACEHOLDER,
                                status = BooleanType.True,
                                lastTransitionTime = NOW,
                                message = PLACEHOLDER,
                                reason = PLACEHOLDER
                            )
                        ),
                        qosClass = PLACEHOLDER,
                        initContainerStatuses = listOf(CONTAINER_STATUS),
                        containerStatuses = listOf(CONTAINER_STATUS),
                        ephemeralContainerStatuses = listOf(CONTAINER_STATUS),
                        resourceClaimStatuses = listOf(
                            PodResourceClaimStatus(
                                name = PLACEHOLDER,
                                resourceClaimName = PLACEHOLDER
                            )
                        ),
                        resize = PLACEHOLDER,
                        observedGeneration = 1
                    )
                )
            }
        }
    }
}