package io.violabs.picard.v2.resources.policy.admission.validating


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class AuditAnnotationTest : FailureBuildSim<AuditAnnotation, AuditAnnotationDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            AuditAnnotationTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<AuditAnnotation, AuditAnnotationDslBuilder> {
            requireScenario("key") {
                given(AuditAnnotationDslBuilder())
            }

            requireScenario("valueExpression") {
                given(AuditAnnotationDslBuilder()) {
                    key = PLACEHOLDER
                }
            }
        }
    }
}