package io.violabs.picard.domain.k8sResources.workload.pod


//import io.violabs.picard.domain.k8sResources.storage.volume.Volume
import io.violabs.picard.FullBuildSim
import io.violabs.picard.Volumes
import io.violabs.picard.domain.LocalObjectReference
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.Toleration
import io.violabs.picard.domain.k8sResources.workload.pod.affinity.*
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import io.violabs.picard.domain.k8sResources.workload.pod.container.EphemeralContainer
import io.violabs.picard.domain.k8sResources.workload.pod.dnsConfig.DNSConfig
import io.violabs.picard.domain.k8sResources.workload.pod.dnsConfig.DNSConfigOption
import io.violabs.picard.domain.k8sResources.workload.pod.gate.ReadinessGate
import io.violabs.picard.domain.k8sResources.workload.pod.gate.SchedulingGate
import io.violabs.picard.domain.k8sResources.workload.pod.hostAlias.HostAlias
import io.violabs.picard.domain.k8sResources.workload.pod.resource.PodResourceClaim
import io.violabs.picard.domain.k8sResources.workload.pod.security.*
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled

@Disabled
class PodSpecTest : FullBuildSim<Pod.Spec, Pod.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodSpecTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val NODE_AFFINITY = NodeAffinity(
            preferredDuringSchedulingIgnoredDuringExecution = listOf(
                NodeAffinityPreferredSchedulingTerm(
                    weight = 1,
                    preference = NODE_SELECTOR_TERM
                )
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
            preferredDuringSchedulingIgnoredDuringExecution = listOf(WEIGHTED_POD_AFFINITY_TERM),
            requiredDuringSchedulingIgnoredDuringExecution = listOf(POD_AFFINITY_TERM)
        )

        private val AFFINITY = Affinity(
            nodeAffinity = NODE_AFFINITY,
            podAffinity = POD_AFFINITY,
            podAntiAffinity = POD_ANTI_AFFINITY
        )

        private val TOLERATION = Toleration(
            key = "toleration1",
            operator = "In",
            value = "toleration_value",
            effect = "example",
            tolerationSeconds = 1
        )

        private val TOPOLOGY_SPREAD_CONSTRAINT = TopologySpreadConstraint(
            maxSkew = 2,
            topologyKey = "top",
            whenUnsatisfiable = "example",
            labelSelector = LABEL_SELECTOR,
            matchLabelKeys = listOf("key1", "key2"),
            minDomains = 2,
            nodeAffinityPolicy = "tester",
            nodeTaintsPolicy = "tainted"
        )

        private val READINESS_GATE = ReadinessGate(conditionType = "ready")

        private val HOST_ALIAS = HostAlias(
            ip = "0.0.0.0",
            hostnames = listOf("localhost")
        )

        private val DNS_CONFIG = DNSConfig(
            nameservers = listOf("ns1"),
            options = listOf(
                DNSConfigOption("test", "test_value")
            ),
            searches = listOf("search"),
            dnsPolicy = "policy"
        )

        private val SECURITY_CONTEXT = PodSecurityContext(
            appArmorProfile = AppArmorProfile(
                type = SecurityProfileType.Localhost,
                localHostProfile = "test-profile"
            ),
            fsGroup = 100,
            fsGroupChangePolicy = "fsGroup",
            runAsUser = 1000,
            runAsNonRoot = true,
            runAsGroup = 1000,
            seccompProfile = SeccompProfile(
                localhostProfile = "test-profile",
                type = SecurityProfileType.Localhost
            ),
            seLinuxOptions = SELinuxOptions(
                level = "s0",
                role = "role",
                type = "type",
                user = "user"
            ),
            supplementalGroups = listOf(100, 200),
            supplementalGroupsPolicy = "groupPolicy",
            sysctls = listOf(Sysctl("test", "sysctl")),
            windowsOptions = WindowsSecurityContextOptions(
                gmsaCredentialSpec = "gmsaCredentialSpec",
                gmsaCredentialSpecName = "gmsaCredentialSpecName",
                runAsUserName = "runAsUserName",
                hostProcess = true
            )
        )

        private val RESOURCE_CLAIM = PodResourceClaim(
            name = "test_resource",
            resourceClaimName = "test_resource_claim_name",
            resourceClaimTemplateName = "test_resource_claim_template_name",
        )

        private val SCHEDULING_GATE = SchedulingGate(
            name = "test_scheduling"
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
            volumes = listOf(Volumes.VOLUME),
            nodeSelector = mapOf("nodeSelectorKey" to "nodeSelectorValue"),
            nodeName = "test_node_name",
            affinity = AFFINITY,
            tolerations = listOf(TOLERATION),
            schedulerName = "test_scheduler_name",
            runtimeClassName = "test_runtime_class_name",
            priorityClassName = "test_priority_class_name",
            priority = 1,
            preemptionPolicy = "test_preemption_policy",
            topologySpreadConstraints = listOf(TOPOLOGY_SPREAD_CONSTRAINT),
            overhead = mapOf("top" to Quantity("100")),
            restartPolicy = RestartPolicy.Always,
            terminationGracePeriodSeconds = 1,
            activeDeadlineSeconds = 1,
            readinessGates = listOf(READINESS_GATE),
            hostname = "test_hostname",
            setHostnameAsFQDN = true,
            subdomain = "test_subdomain",
            hostAliases = listOf(HOST_ALIAS),
            dnsConfig = DNS_CONFIG,
            hostNetwork = true,
            hostPID = true,
            hostIPC = true,
            shareProcessNamespace = true,
            serviceAccountName = "test_service_account_name",
            automountServiceAccountToken = true,
            securityContext = SECURITY_CONTEXT,
            hostUsers = true,
            resourceClaims = listOf(RESOURCE_CLAIM),
            schedulingGates = listOf(SCHEDULING_GATE)
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Pod.Spec, Pod.Spec.Builder> {
            scenario {
                id = "minimum"
                given(Pod.Spec.Builder()) {
                    containers {
                        container {
                            name = PLACEHOLDER
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
                            name = PLACEHOLDER
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
                            name = PLACEHOLDER
                            persistentVolumeClaim {
                                claimName = PLACEHOLDER
                                readOnly()
                            }

                            configMap {
                                name = PLACEHOLDER
                                items {
                                    keyToPath {
                                        key = PLACEHOLDER
                                        path = PLACEHOLDER
                                    }
                                }
                                defaultMode = 1
                                optional()
                            }

                            secret {
                                secretName = PLACEHOLDER
                                optional()
                                defaultMode = 1
                                items {
                                    keyToPath {
                                        key = PLACEHOLDER
                                        path = PLACEHOLDER
                                    }
                                }
                            }

                            downwardApi {
                                defaultMode = 1
                                items {
                                    downwardApiVolumeFile {
                                        path = PLACEHOLDER
                                        fieldRef {
                                            fieldPath = PLACEHOLDER
                                            apiVersion = KAPIVersion.V1
                                        }
                                        mode = 1
                                        resourceFieldRef {
                                            resource = PLACEHOLDER
                                            containerName = PLACEHOLDER
                                            divisor("1")
                                        }
                                    }
                                }
                            }

                            projected {
                                defaultMode = 1
                                sources {
                                    volumeProjection {
                                        clusterTrustBundle {
                                            name = PLACEHOLDER
                                            path = PLACEHOLDER
                                            labelSelector {
                                                matchExpressions {
                                                    labelSelectorRequirement {
                                                        key = PLACEHOLDER
                                                        operator = PLACEHOLDER
                                                        values(PLACEHOLDER)
                                                    }
                                                }
                                                matchLabels(PLACEHOLDER to PLACEHOLDER)
                                            }
                                            signerName = PLACEHOLDER
                                            optional()
                                        }

                                        configMap {
                                            name = PLACEHOLDER
                                            optional()
                                            items {
                                                keyToPath {
                                                    key = PLACEHOLDER
                                                    path = PLACEHOLDER
                                                }
                                            }
                                        }

                                        downwardApi {
                                            items {
                                                downwardApiVolumeFile {
                                                    path = PLACEHOLDER
                                                    fieldRef {
                                                        fieldPath = PLACEHOLDER
                                                        apiVersion = KAPIVersion.V1
                                                    }
                                                    mode = 1
                                                    resourceFieldRef {
                                                        resource = PLACEHOLDER
                                                        containerName = PLACEHOLDER
                                                        divisor("1")
                                                    }
                                                }
                                            }
                                        }

                                        secret {
                                            name = PLACEHOLDER
                                            optional()
                                            items {
                                                keyToPath {
                                                    key = PLACEHOLDER
                                                    path = PLACEHOLDER
                                                }
                                            }
                                        }

                                        serviceAccountToken {
                                            audience = PLACEHOLDER
                                            expirationSeconds = 1L
                                            path = PLACEHOLDER
                                        }
                                    }
                                }
                            }

                            emptyDir {
                                medium = PLACEHOLDER
                                sizeLimit("1")
                            }

                            hostPath {
                                path = PLACEHOLDER
                                type = PLACEHOLDER
                            }

                            csi {
                                driver = PLACEHOLDER
                                fsType = PLACEHOLDER
                                readOnly()
                                nodePublishSecretRef {
                                    name = PLACEHOLDER
                                }
                                volumeAttributes(PLACEHOLDER to PLACEHOLDER)
                            }

                            ephemeral {
                                volumeClaimTemplate {
                                    spec { }
                                    metadata { }
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
                                imagePullPolicy = PLACEHOLDER
                                reference = PLACEHOLDER
                            }

                            nfs {
                                server = PLACEHOLDER
                                path = PLACEHOLDER
                                readOnly()
                            }
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
                                        sharedNodeSelectorTerm()
                                    }
                                }
                            }
                        }

                        podAffinity {
                            preferredDuringSchedulingIgnoredDuringExecution {
                                addWeightedPodAffinityTerm {
                                    weight = 1
                                    podAffinityTerm { sharedTerm() }
                                }
                            }

                            requiredDuringSchedulingIgnoredDuringExecution {
                                addPodAffinityTerm { sharedTerm() }
                            }
                        }

                        podAntiAffinity {
                            preferredDuringSchedulingIgnoredDuringExecution {
                                addWeightedPodAffinityTerm {
                                    weight = 1
                                    podAffinityTerm { sharedTerm() }
                                }
                            }

                            requiredDuringSchedulingIgnoredDuringExecution {
                                addPodAffinityTerm { sharedTerm() }
                            }
                        }
                    }

                    tolerations {
                        toleration {
                            key = "toleration1"
                            operator = "In"
                            value = "toleration_value"
                            effect = "example"
                            tolerationSeconds = 1
                        }
                    }

                    schedulerName = "test_scheduler_name"
                    runtimeClassName = "test_runtime_class_name"
                    priorityClassName = "test_priority_class_name"
                    priority = 1
                    preemptionPolicy = "test_preemption_policy"

                    topologySpreadConstraints {
                        constraint {
                            maxSkew = 2
                            topologyKey = "top"
                            whenUnsatisfiable = "example"
                            labelSelector { sharedSelector() }
                            matchLabelKeys("key1", "key2")
                            minDomains = 2
                            nodeAffinityPolicy = "tester"
                            nodeTaintsPolicy = "tainted"
                        }
                    }

                    overhead {
                        put("top", Quantity("100"))
                    }

                    restartPolicy = RestartPolicy.Always
                    terminationGracePeriodSeconds = 1L
                    activeDeadlineSeconds = 1L

                    readinessGates {
                        addReadinessGate {
                            conditionType = "ready"
                        }
                    }

                    hostname = "test_hostname"

                    setHostnameAsFQDN()

                    subdomain = "test_subdomain"

                    hostAliases {
                        addHostAlias {
                            ip = "0.0.0.0"
                            hostnames("localhost")
                        }
                    }

                    dnsConfig {
                        nameservers("ns1")
                        options {
                            addDNSConfigOption {
                                name = "test"
                                value = "test_value"
                            }
                        }
                        searches("search")
                        dnsPolicy = "policy"
                    }

                    hostNetwork()
                    hostPID()
                    hostIPC()
                    shareProcessNamespace()

                    serviceAccountName = "test_service_account_name"

                    automountServiceAccountToken()

                    securityContext {
                        appArmorProfile {
                            type = SecurityProfileType.Localhost
                            localHostProfile = "test-profile"
                        }
                        fsGroup = 100
                        fsGroupChangePolicy = "fsGroup"
                        runAsUser = 1000
                        runAsNonRoot()
                        runAsGroup = 1000
                        seccompProfile {
                            localhostProfile = "test-profile"
                            type = SecurityProfileType.Localhost
                        }
                        seLinuxOptions {
                            level = "s0"
                            role = "role"
                            type = "type"
                            user = "user"
                        }
                        supplementalGroups(100, 200)
                        supplementalGroupsPolicy = "groupPolicy"
                        sysctls {
                            addSysctl {
                                name = "test"
                                value = "sysctl"
                            }
                        }
                        windowsOptions {
                            gmsaCredentialSpec = "gmsaCredentialSpec"
                            gmsaCredentialSpecName = "gmsaCredentialSpecName"
                            runAsUserName = "runAsUserName"
                            hostProcess()
                        }
                    }

                    hostUsers()

                    resourceClaims {
                        addPodResourceClaim {
                            name = "test_resource"
                            resourceClaimName = "test_resource_claim_name"
                            resourceClaimTemplateName = "test_resource_claim_template_name"
                        }
                    }

                    schedulingGates {
                        addSchedulingGate {
                            name = "test_scheduling"
                        }
                    }
                }
                expected = SPEC
            }

            scenario {
                idForFalseBooleanValues()
                given(Pod.Spec.Builder()) {
                    containers {
                        container {
                            name = PLACEHOLDER
                        }
                    }
                    enableServiceLinks(false)
                }
                expected = Pod.Spec(
                    containers = listOf(STANDARD_CONTAINER),
                    enableServiceLinks = false
                )
            }
        }

        fun PodAffinityTerm.Builder.sharedTerm() {
            topologyKey = "top"
            labelSelector {
                sharedSelector()
            }
            matchLabelKeys("key1", "key2")
            mismatchLabelKeys("key3", "key4")
            namespaceSelector {
                sharedSelector()
            }
            namespaces("namespace1")
        }


        private val FAILURE_POSSIBILITIES = possibilities<Pod.Spec, Pod.Spec.Builder> {
            requireNotEmptyScenario("containers") {
                given(Pod.Spec.Builder())
            }
        }
    }
}