package io.violabs.picard.v2.resources.service.ingress.http


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressHttpIngressRuleValueTest :
    FailureBuildSim<HttpIngressRuleValue, HttpIngressRuleValueDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressHttpIngressRuleValueTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<HttpIngressRuleValue, HttpIngressRuleValueDslBuilder> {
                requireNotEmptyScenario("paths") {
                    given(HttpIngressRuleValueDslBuilder())
                }
            }
    }
}