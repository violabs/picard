package io.violabs.picard.v2.resources.workload.resource.claim

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.Workload
import io.violabs.picard.Workload.Resource.sharedTerms
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.resource.NodeSelector
import io.violabs.picard.v2.resources.workload.resource.claim.device.AllocatedDeviceStatus
import io.violabs.picard.v2.resources.workload.resource.claim.device.Condition
import io.violabs.picard.v2.resources.workload.resource.claim.device.DeviceClaim
import io.violabs.picard.v2.resources.workload.resource.claim.device.DeviceConstraint
import io.violabs.picard.v2.resources.workload.resource.claim.device.DeviceToleration
import io.violabs.picard.v2.resources.workload.resource.claim.device.DeviceTolerationDslBuilder
import io.violabs.picard.v2.resources.workload.resource.claim.device.NetworkDeviceData
import io.violabs.picard.v2.resources.workload.resource.claim.device.config.DeviceAllocationConfiguration
import io.violabs.picard.v2.resources.workload.resource.claim.device.config.DeviceClaimConfiguration
import io.violabs.picard.v2.resources.workload.resource.claim.device.config.OpaqueDeviceConfiguration
import io.violabs.picard.v2.resources.workload.resource.claim.device.config.OpaqueDeviceConfigurationDslBuilder
import io.violabs.picard.v2.resources.workload.resource.claim.device.request.AllocationMode
import io.violabs.picard.v2.resources.workload.resource.claim.device.request.DeviceRequest
import io.violabs.picard.v2.resources.workload.resource.claim.device.request.DeviceSubRequest
import io.violabs.picard.v2.resources.workload.resource.claim.device.request.ExactDeviceRequest
import io.violabs.picard.v2.resources.workload.resource.claim.device.result.DeviceAllocationResult
import io.violabs.picard.v2.resources.workload.resource.claim.device.result.DeviceRequestAllocationResult
import io.violabs.picard.v2.resources.workload.resource.device.selector.CelDeviceSelector
import io.violabs.picard.v2.resources.workload.resource.device.selector.DeviceSelector
import io.violabs.picard.v2.resources.workload.resource.device.selector.DeviceSelectorDslBuilder
import org.junit.jupiter.api.BeforeAll

class ResourceClaimTest : SuccessBuildSim<ResourceClaim, ResourceClaimDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceClaimTest::class,
            SUCCESS_POSSIBILITIES
        )

        private fun DeviceSelectorDslBuilder.Group.sharedSelectors() {
            deviceSelector {
                cel {
                    expression = PLACEHOLDER
                }
            }
        }

        private val DEVICE_SELECTORS = listOf(
            DeviceSelector(
                cel = CelDeviceSelector(
                    expression = PLACEHOLDER
                )
            )
        )

        private fun DeviceTolerationDslBuilder.Group.sharedTolerations() {
            deviceToleration {
                effect = DeviceToleration.Effect.NoSchedule
                key = PLACEHOLDER
                operator = DeviceToleration.Operator.Equal
                tolerationSeconds = 1
                value = PLACEHOLDER
            }
        }

        private val DEVICE_TOLERATIONS = listOf(
            DeviceToleration(
                effect = DeviceToleration.Effect.NoSchedule,
                key = PLACEHOLDER,
                operator = DeviceToleration.Operator.Equal,
                tolerationSeconds = 1,
                value = PLACEHOLDER
            )
        )

        private fun OpaqueDeviceConfigurationDslBuilder.sharedOpaque() {
            driver = PLACEHOLDER
            parameters(PLACEHOLDER to PLACEHOLDER)
        }

        private val OPAQUE_DEVICE_CONFIGURATION = OpaqueDeviceConfiguration(
            driver = PLACEHOLDER,
            parameters = mapOf(PLACEHOLDER to PLACEHOLDER)
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceClaim, ResourceClaimDslBuilder> {
            scenario {
                id = "minimum"
                given(ResourceClaimDslBuilder()) {
                    spec {
                        // Empty spec - DeviceClaim is optional
                    }
                }
                expected = ResourceClaim(
                    spec = ResourceClaimSpec()
                )
            }

            scenario {
                id = "full"
                given(ResourceClaimDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        devices {
                            config {
                                deviceClaimConfiguration {
                                    opaque {
                                        driver = PLACEHOLDER
                                        parameters(PLACEHOLDER to PLACEHOLDER)
                                    }

                                    requests(PLACEHOLDER)
                                }
                            }

                            constraints {
                                deviceConstraint {
                                    matchAttribute = PLACEHOLDER
                                    requests(PLACEHOLDER)
                                }
                            }

                            requests {
                                deviceRequest {
                                    name = PLACEHOLDER
                                    exactly {
                                        deviceClassName = PLACEHOLDER
                                        adminAccess()
                                        allocationMode = AllocationMode.All
                                        count = 1
                                        selectors {
                                            sharedSelectors()
                                        }

                                        tolerations {
                                            sharedTolerations()
                                        }
                                    }
                                    firstAvailable {
                                        deviceSubRequest {
                                            deviceClassName = PLACEHOLDER
                                            name = PLACEHOLDER
                                            allocationMode = AllocationMode.All
                                            count = 1
                                            selectors {
                                                sharedSelectors()
                                            }
                                            tolerations {
                                                sharedTolerations()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    status {
                        allocation {
                            devices {
                                config {
                                    deviceAllocationConfiguration {
                                        source = PLACEHOLDER
                                        opaque {
                                            sharedOpaque()
                                        }
                                        requests(PLACEHOLDER)
                                    }
                                }

                                results {
                                    deviceRequestAllocationResult {
                                        device = PLACEHOLDER
                                        driver = PLACEHOLDER
                                        pool = PLACEHOLDER
                                        request = PLACEHOLDER
                                        adminAccess()

                                        tolerations {
                                            sharedTolerations()
                                        }
                                    }
                                }
                            }

                            nodeSelector {
                                sharedTerms()
                            }
                        }

                        devices {
                            allocatedDeviceStatus {
                                device = PLACEHOLDER
                                driver = PLACEHOLDER
                                pool = PLACEHOLDER
                                conditions {
                                    condition {
                                        type = PLACEHOLDER
                                        status = ConditionStatus.True
                                        message = PLACEHOLDER
                                        reason = PLACEHOLDER
                                        lastTransitionTime = NOW
                                    }
                                }
                                data = PLACEHOLDER
                                networkData {
                                    hardwareAddress = PLACEHOLDER
                                    interfaceName = PLACEHOLDER
                                    ips(PLACEHOLDER)
                                }
                            }
                        }

                        reservedFor {
                            resourceClaimConsumerReference {
                                name = PLACEHOLDER
                                resource = PLACEHOLDER
                                uid = PLACEHOLDER
                                apiGroup = PLACEHOLDER
                            }
                        }
                    }
                }
                expected = ResourceClaim(
                    metadata = OBJECT_META,
                    spec = ResourceClaimSpec(
                        devices = DeviceClaim(
                            config = listOf(
                                DeviceClaimConfiguration(
                                    opaque = OPAQUE_DEVICE_CONFIGURATION,
                                    requests = listOf(PLACEHOLDER)
                                )
                            ),
                            constraints = listOf(
                                DeviceConstraint(
                                    matchAttribute = PLACEHOLDER,
                                    requests = listOf(PLACEHOLDER)
                                )
                            ),
                            requests = listOf(
                                DeviceRequest(
                                    name = PLACEHOLDER,
                                    exactly = ExactDeviceRequest(
                                        deviceClassName = PLACEHOLDER,
                                        adminAccess = true,
                                        allocationMode = AllocationMode.All,
                                        count = 1,
                                        selectors = DEVICE_SELECTORS,
                                        tolerations = DEVICE_TOLERATIONS
                                    ),
                                    firstAvailable = listOf(
                                        DeviceSubRequest(
                                            deviceClassName = PLACEHOLDER,
                                            name = PLACEHOLDER,
                                            allocationMode = AllocationMode.All,
                                            count = 1,
                                            selectors = DEVICE_SELECTORS,
                                            tolerations = DEVICE_TOLERATIONS
                                        )
                                    )
                                )
                            )
                        )
                    ),
                    status = ResourceClaimStatus(
                        allocation = AllocationResult(
                            devices = DeviceAllocationResult(
                                config = listOf(
                                    DeviceAllocationConfiguration(
                                        source = PLACEHOLDER,
                                        opaque = OPAQUE_DEVICE_CONFIGURATION,
                                        requests = listOf(PLACEHOLDER)
                                    )
                                ),
                                results = listOf(
                                    DeviceRequestAllocationResult(
                                        device = PLACEHOLDER,
                                        driver = PLACEHOLDER,
                                        pool = PLACEHOLDER,
                                        request = PLACEHOLDER,
                                        adminAccess = true,
                                        tolerations = DEVICE_TOLERATIONS
                                    )
                                )
                            ),
                            nodeSelector = NodeSelector(
                                nodeSelectorTerms = Workload.Resource.NODE_SELECTOR_TERMS
                            )
                        ),
                        devices = listOf(
                            AllocatedDeviceStatus(
                                device = PLACEHOLDER,
                                driver = PLACEHOLDER,
                                pool = PLACEHOLDER,
                                conditions = listOf(
                                    Condition(
                                        type = PLACEHOLDER,
                                        status = ConditionStatus.True,
                                        message = PLACEHOLDER,
                                        reason = PLACEHOLDER,
                                        lastTransitionTime = NOW
                                    )
                                ),
                                data = PLACEHOLDER,
                                networkData = NetworkDeviceData(
                                    hardwareAddress = PLACEHOLDER,
                                    interfaceName = PLACEHOLDER,
                                    ips = listOf(PLACEHOLDER
                                    )
                                )
                            )
                        ),
                        reservedFor = listOf(
                            ResourceClaimConsumerReference(
                                name = PLACEHOLDER,
                                resource = PLACEHOLDER,
                                uid = PLACEHOLDER,
                                apiGroup = PLACEHOLDER
                            )
                        )
                    )
                )
            }
        }
    }
}