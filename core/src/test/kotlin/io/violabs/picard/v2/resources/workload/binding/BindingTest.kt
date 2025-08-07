package io.violabs.picard.v2.resources.workload.binding

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.common.ObjectReference
import org.junit.jupiter.api.BeforeAll

class BindingTest : SuccessBuildSim<Binding, BindingDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            BindingTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Binding, BindingDslBuilder> {
            scenario {
                id = "minimum"
                given(BindingDslBuilder()) {
                    target {
                        kind = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
                expected = Binding(
                    target = ObjectReference(
                        kind = PLACEHOLDER,
                        name = PLACEHOLDER
                    )
                )
            }

            scenario {
                id = "full"
                given(BindingDslBuilder()) {
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
                expected = Binding(
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