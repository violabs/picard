package io.violabs.picard.domain.builder

import io.violabs.picard.domain.CrashLoopBackOff

class CrashLoopBackOffBuilder : Builder<CrashLoopBackOff> {
    var maxContainerRestartPeriod: String? = null

    override fun build(): CrashLoopBackOff {
        return CrashLoopBackOff(
            maxContainerRestartPeriod = requireNotNull(maxContainerRestartPeriod) { "Please provide a maxContainerRestartPeriod" }
        )
    }
}