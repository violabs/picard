package io.violabs.picard.v2.resources.workload.resource.device.taint

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.resource.device.selector.CelDeviceSelector
import io.violabs.picard.v2.resources.workload.resource.device.selector.DeviceSelector
import io.violabs.picard.v2.resources.workload.resource.device.selector.DeviceTaintSelector
import org.junit.jupiter.api.BeforeAll

class DeviceTaintRuleTest : SuccessBuildSim<DeviceTaintRule, DeviceTaintRuleDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceTaintRuleTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<DeviceTaintRule, DeviceTaintRuleDslBuilder> {
            scenario {
                id = "minimum"
                given(DeviceTaintRuleDslBuilder()) {
                    spec {
                        taint {
                            effect = DeviceTaint.Effect.NoSchedule
                            key = PLACEHOLDER
                        }
                    }
                }
                expected = DeviceTaintRule(
                    spec = DeviceTaintRuleSpec(
                        taint = DeviceTaint(
                            effect = DeviceTaint.Effect.NoSchedule,
                            key = PLACEHOLDER
                        )
                    )
                )
            }

            scenario {
                id = "full"
                given(DeviceTaintRuleDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        taint {
                            effect = DeviceTaint.Effect.NoExecute
                            key = PLACEHOLDER
                            timeAdded = NOW
                            value = PLACEHOLDER
                        }
                        deviceSelector {
                            device = PLACEHOLDER
                            deviceClassName = PLACEHOLDER
                            driver = PLACEHOLDER
                            pool = PLACEHOLDER
                            selectors {
                                deviceSelector {
                                    cel {
                                        expression = PLACEHOLDER
                                    }
                                }
                            }
                        }
                    }
                }
                expected = DeviceTaintRule(
                    metadata = Common.OBJECT_META,
                    spec = DeviceTaintRuleSpec(
                        taint = DeviceTaint(
                            effect = DeviceTaint.Effect.NoExecute,
                            key = PLACEHOLDER,
                            timeAdded = NOW,
                            value = PLACEHOLDER
                        ),
                        deviceSelector = DeviceTaintSelector(
                            device = PLACEHOLDER,
                            deviceClassName = PLACEHOLDER,
                            driver = PLACEHOLDER,
                            pool = PLACEHOLDER,
                            selectors = listOf(
                                DeviceSelector(
                                    cel = CelDeviceSelector(
                                        expression = PLACEHOLDER
                                    )
                                )
                            )
                        )
                    )
                )
            }
        }
    }
}