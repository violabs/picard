package io.violabs.picard.domain.k8sResources.service.ingress


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressHTTPIngressRuleValueTest :
    FailureBuildSim<IngressHTTPIngressRuleValue, IngressHTTPIngressRuleValue.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressHTTPIngressRuleValueTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<IngressHTTPIngressRuleValue, IngressHTTPIngressRuleValue.Builder> {
                requireNotEmptyScenario("paths") {
                    given(IngressHTTPIngressRuleValue.Builder())
                }
            }
    }
}