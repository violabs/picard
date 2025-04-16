package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DSLBuilder
import java.time.LocalDateTime

data class ContainerState(
    val running: Running? = null,
    val terminated: Terminated? = null,
    val waiting: Waiting? = null
) {
    data class Running(val startedAt: LocalDateTime? = null) {
        class Builder : DSLBuilder<Running> {
            var startedAt: LocalDateTime? = null

            override fun build(): Running {
                return Running(startedAt)
            }
        }
    }

    data class Terminated(
        val containerID: String? = null,
        val exitCode: Int? = null,
        val finishedAt: LocalDateTime? = null,
        val message: String? = null,
        val reason: String? = null,
        val signal: Int? = null,
        val startedAt: LocalDateTime? = null
    ) {
        class Builder : DSLBuilder<Terminated> {
            var containerID: String? = null
            var exitCode: Int? = null
            var finishedAt: LocalDateTime? = null
            var message: String? = null
            var reason: String? = null
            var signal: Int? = null
            var startedAt: LocalDateTime? = null

            override fun build(): Terminated {
                return Terminated(
                    containerID,
                    exitCode,
                    finishedAt,
                    message,
                    reason,
                    signal,
                    startedAt
                )
            }
        }
    }

    data class Waiting(
        val message: String? = null,
        val reason: String? = null
    ) {
        class Builder : DSLBuilder<Waiting> {
            var message: String? = null
            var reason: String? = null

            override fun build(): Waiting {
                return Waiting(
                    message,
                    reason
                )
            }
        }
    }

    class Builder : DSLBuilder<ContainerState> {
        private var running: Running? = null
        private var terminated: Terminated? = null
        private var waiting: Waiting? = null

        fun running(scope: Running.Builder.() -> Unit) {
            running = Running.Builder().apply(scope).build()
        }

        fun terminated(scope: Terminated.Builder.() -> Unit) {
            terminated = Terminated.Builder().apply(scope).build()
        }

        fun waiting(scope: Waiting.Builder.() -> Unit) {
            waiting = Waiting.Builder().apply(scope).build()
        }

        override fun build(): ContainerState {
            return ContainerState(
                running,
                terminated,
                waiting
            )
        }
    }
}