package io.violabs.picard.domain

data class CrashLoopBackOff(
    val maxContainerRestartPeriod: String
)