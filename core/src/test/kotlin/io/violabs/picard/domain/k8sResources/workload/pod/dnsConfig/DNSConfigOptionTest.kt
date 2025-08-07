package io.violabs.picard.domain.k8sResources.workload.pod.dnsConfig


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DNSConfigOptionTest : FailureBuildSim<DNSConfigOption, DNSConfigOptionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DNSConfigOptionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<DNSConfigOption, DNSConfigOptionDslBuilder> {
    requireScenario("name") {
        given(DNSConfigOptionDslBuilder())
    }
}