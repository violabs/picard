package io.violabs.picard.domain.k8sResources.workload.resourceClaim


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.DeviceSelector
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceClaimTest : FullBuildSim<ResourceClaim, ResourceClaimDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceClaimTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val DEVICE_CLAIM_CONFIGURATION = DeviceClaimConfiguration(
            opaque = OPAQUE_DEVICE_CONFIG,
            requests = PLACEHOLDER_LIST
        )

        private val DEVICE_CONSTRAINT = DeviceConstraint(
            matchAttribute = PLACEHOLDER,
            requests = PLACEHOLDER_LIST
        )

        private val DEVICE_REQUEST = DeviceRequest(
            deviceClassName = PLACEHOLDER,
            name = PLACEHOLDER,
            adminAccess = true,
            allocationMode = AllocationMode.All,
            count = 1,
            selectors = listOf(
                DeviceSelector(
                    cel = DeviceSelector.CEL(expression = PLACEHOLDER)
                )
            )
        )

        private val RESOURCE_CLAIM_ALLOCATION_RESULT = AllocationResult(
            devices = DeviceAllocationResult(
                config = listOf(
                    DeviceAllocationConfiguration(
                        source = PLACEHOLDER,
                        opaque = OPAQUE_DEVICE_CONFIG,
                        request = PLACEHOLDER_LIST
                    )
                ),
                results = listOf(
                    DeviceRequestAllocationResult(
                        device = PLACEHOLDER,
                        driver = PLACEHOLDER,
                        pool = PLACEHOLDER,
                        request = PLACEHOLDER
                    )
                )
            ),
            nodeSelector = NODE_SELECTOR
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceClaim, ResourceClaimDslBuilder> {
            scenario {
                id = "minimum"
                given(ResourceClaimDslBuilder()) {
                    spec {}
                }
                expected = ResourceClaim(
                    spec = ResourceClaimSpec()
                )
            }

            scenario {
                id = "full"
                given(ResourceClaimDslBuilder()) {
                    sharedMetadata()
                    spec {
                        controller = PLACEHOLDER
                        devices {
                            config {
                                opaque {
                                    driver = PLACEHOLDER
                                    parameters = PLACEHOLDER
                                }
                                requests(PLACEHOLDER)
                            }
                            constraints {
                                addDeviceConstraint {
                                    matchAttribute = PLACEHOLDER
                                    requests(PLACEHOLDER)
                                }
                            }
                            requests {
                                addDeviceRequest {
                                    deviceClassName = PLACEHOLDER
                                    name = PLACEHOLDER
                                    adminAccess()
                                    allocationMode = AllocationMode.All
                                    count = 1
                                    selectors {
                                        addDeviceSelector {
                                            cel(PLACEHOLDER)
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
                                    addDeviceAllocationConfiguration {
                                        source = PLACEHOLDER
                                        opaque {
                                            driver = PLACEHOLDER
                                            parameters = PLACEHOLDER
                                        }
                                        requests(PLACEHOLDER)
                                    }
                                }
                                results {
                                    addDeviceRequestAllocationResult {
                                        device = PLACEHOLDER
                                        driver = PLACEHOLDER
                                        pool = PLACEHOLDER
                                        request = PLACEHOLDER
                                    }
                                }
                            }

                            nodeSelector {
                                terms {
                                    term {
                                        sharedNodeSelectorTerm()
                                    }
                                }
                            }
                        }

                        deallocationRequested()

                        reservedFor {
                            addConsumerReference {
                                name = PLACEHOLDER
                                resource = PLACEHOLDER
                                uid = PLACEHOLDER
                                apiGroup = PLACEHOLDER
                            }
                        }
                    }
                }
                expected = ResourceClaim(
                    metadata = METADATA,
                    spec = ResourceClaimSpec(
                        controller = PLACEHOLDER,
                        devices = DeviceClaim(
                            config = DEVICE_CLAIM_CONFIGURATION,
                            constraints = listOf(DEVICE_CONSTRAINT),
                            requests = listOf(DEVICE_REQUEST)
                        )
                    ),
                    status = ResourceClaimStatus(
                        allocation = RESOURCE_CLAIM_ALLOCATION_RESULT,
                        deallocationRequested = true,
                        reservedFor = listOf(
                            ConsumerReference(
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

        private val FAILURE_POSSIBILITIES = possibilities<ResourceClaim, ResourceClaimDslBuilder> {
            requireScenario("spec") {
                given(ResourceClaimDslBuilder())
            }
        }
    }
}