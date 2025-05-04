package io.violabs.picard.domain.k8sResources.workload.pod.action

import io.violabs.picard.common.DSLBuilder

data class ExecAction(
    val command: List<String>? = null
) {
    class Builder : DSLBuilder<ExecAction> {
        private var command: List<String>? = null

        fun command(vararg commandParts: String) {
            command = commandParts.toList()
        }

        override fun build(): ExecAction {
            return ExecAction(command)
        }
    }
}