package io.violabs.picard.domain.k8sResources.workload.pod.action

import io.violabs.picard.domain.DslBuilder

data class ExecAction(
    val command: List<String>? = null
) {
    class Builder : DslBuilder<ExecAction> {
        private var command: List<String>? = null

        fun command(vararg commandParts: String) {
            command = commandParts.toList()
        }

        override fun build(): ExecAction {
            return ExecAction(command)
        }
    }
}