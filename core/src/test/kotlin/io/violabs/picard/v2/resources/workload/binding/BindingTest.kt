package io.violabs.picard.v2.resources.workload.binding

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.common.ObjectReference
import org.junit.jupiter.api.BeforeAll

class BindingTest : SuccessBuildSim<BindingV2, BindingV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            BindingTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<BindingV2, BindingV2DslBuilder> {
            scenario {
                id = "minimum"
                given(BindingV2DslBuilder()) {
                    target {
                        kind = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
                expected = BindingV2(
                    target = ObjectReference(
                        kind = PLACEHOLDER,
                        name = PLACEHOLDER
                    )
                )
            }

            scenario {
                id = "full"
                given(BindingV2DslBuilder()) {
                    metadata {
                        name = PLACEHOLDER  
                        namespace = PLACEHOLDER
                    }
                    target {
                        apiVersion = KAPIVersion.V1
                        kind = PLACEHOLDER
                        name = PLACEHOLDER
                        namespace = PLACEHOLDER
                        resourceVersion = PLACEHOLDER
                        uid = PLACEHOLDER
                    }
                }
                expected = BindingV2(
                    metadata = ObjectMeta(
                        name = PLACEHOLDER,
                        namespace = PLACEHOLDER
                    ),
                    target = ObjectReference(
                        apiVersion = KAPIVersion.V1,
                        kind = PLACEHOLDER,
                        name = PLACEHOLDER,
                        namespace = PLACEHOLDER,
                        resourceVersion = PLACEHOLDER,
                        uid = PLACEHOLDER
                    )
                )
            }
        }
    }
}